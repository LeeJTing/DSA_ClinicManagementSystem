/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedHashMap;
import adt.MapInterface;
import boundary.ConsultationUI;
import dao.Master;
import entity.Consultation;
import entity.Staff;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.function.Function;
import utility.IDGenerator;
import utility.Input;
import utility.MessageUI;

/**
 *
 * @author Tan Kok Hong
 */
public class ConsultationManagement implements CRUD {

    private static MapInterface<String, Consultation> consultationMap = new LinkedHashMap<>();
    private static MapInterface<String, Staff> staffMap = new LinkedHashMap<>();
    private static MapInterface<Integer, String> doctorAMap = new LinkedHashMap<>();
    private static MapInterface<Integer, String> doctorBMap = new LinkedHashMap<>();
    private static MapInterface<String, String> timeSlotMap = new LinkedHashMap<>();

    private static final ConsultationUI consultUI = new ConsultationUI();
//    private static final String currentPatientId = Master.getCurrentPatientId();
    private static final String currentStaffId = Master.getCurrentStaffId();
    private static MapInterface<String, Consultation> consultationRecordHistory = new LinkedHashMap<>();
    private static MapInterface<String, String> actionHistory = new LinkedHashMap<>();

    private static final String currentPatientId = "P000001";
//    private static final String currentStaffId = "S000001";

    public static void main(String[] args) {
        Master.initializer();
        consultationMap = Master.getConsultationMap();
        staffMap = Master.getStaffMap();
        doctorAMap = Master.getDoctorAMap();
        doctorBMap = Master.getDoctorBMap();
        timeSlotMap = Master.getTimeSlotMap();

        ConsultationManagement c = new ConsultationManagement();
        c.consultationMenu();
    }

//    public ConsultationManagement() {
//        getAllMap();
//    }
//    private void getAllMap() {
//        Master.initializer();
//        consulationMap = Master.getConsultationMap();
//    }
    public void consultationMenu() {
//        String currentPatientId = Master.getCurrentPatientId();
//        String currentStaffId = Master.getCurrentStaffId();
        int choice = 0;

        do {
            choice = consultUI.consultationMenu();
            switch (choice) {
                case 1 -> // view Appointment (Patient and docktor)
                    readInstance();
                case 2 -> { // search appointment (patient)
                    searchAppoinment();
//                    if (currentPatientId != null && !currentPatientId.isEmpty()) {
//                        System.out.println("\t\t The Patient not avaliable search appoinment");
//                    } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
//                        searchAppoinment();
//                    } else {
//            consultUI.displayCurrentUserMsg();
//                    }
                }
                case 3 -> // add appoinment (walk in / online)
                    createNewInstance();
                case 4 -> {// update Appointment (patient)
                    updateAppoinment();
//                    if (currentPatientId != null && !currentPatientId.isEmpty()) {
//                        updateAppoinment();
//                    } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
//                        System.out.println("\t\t The Staff not avaliable update appoinment");
//                    } else {
//            consultUI.displayCurrentUserMsg();

//                    }
                }
                case 5 -> { // update Appointment (patient)
                    deleteInstance();
//                    if (currentPatientId != null && !currentPatientId.isEmpty()) {
//                        deleteInstance();
//                    } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
//                        System.out.println("\t\t The Staff not avaliable update appoinment");
//                    } else {
//            consultUI.displayCurrentUserMsg();

//                    }
                }
                case 6 -> // view consultation schedule (Patient)
                    viewConsultationSchedule();
                case 7 -> {// view Consultation Flw Up Report 
                    viewConsultationFlwUpReport();
//                    if (currentPatientId != null && !currentPatientId.isEmpty()) {
//                        System.out.println("\t\t The patient not avaliable view report");
//                    } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
//                        viewConsultationFlwUpReport();
//                    } else {
//            consultUI.displayCurrentUserMsg();

//                    }
                }
                case 8 -> { // view Consultation Valume Report
                    viewConsultationValumeReport();
//                    if (currentPatientId != null && !currentPatientId.isEmpty()) {
//                        System.out.println("\t\t The patient not avaliable view report");
//                    } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
//                        viewConsultationValumeReport();
//                    } else {
//                    consultUI.displayCurrentUserMsg();
//                    }
                }
                case 9 -> {        // undo
                    undo();
                }
                case 10 -> {        // back
                    consultUI.displayExitMsg();
                }
                default ->
                    consultUI.displayInvalidOptionMsg();
            }
        } while (choice != 8);
    }

    public static void addAppointmentMenu() {
        int choice = 0;
        do {
            choice = consultUI.addAppointmentMenu();
            switch (choice) {
                case 1 -> // online
                    addOnlineAppoinment();
                case 2 -> // walk in
                    addWalkInAppoinment();
                case 3 -> {
                    consultUI.displayBackMsg();
                }
                default ->
                    consultUI.displayInvalidOptionMsg();
            }
            // back
        } while (choice != 3);
    }

    @Override
    public void createNewInstance() {
        if (currentPatientId != null && !currentPatientId.isEmpty()) {
            addAppointmentMenu();
        } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
            addFlwUpAppoinment();
        } else {
            consultUI.displayCurrentUserMsg();
        }
    }

    @Override
    public void readInstance() {
        Consultation.setCompare("appointmentStatus");
        consultationMap.sorting();
        Object[] consulations = consultationMap.getAllValues();
        if (currentPatientId != null && !currentPatientId.isEmpty()) {
            viewPatientAppoinment(consulations, currentPatientId);
        } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
            viewTodayAppoinment(consulations, currentStaffId);
        } else {
            consultUI.displayCurrentUserMsg();
        }
    }

    @Override
    public void updateInstance() {
        updateAppoinment();
    }

    @Override
    public void deleteInstance() {
        boolean confirm;
        consultUI.deleteAppoinmentUI();
        String consultationID = consultUI.promptConsultationID();
        Consultation consultationFound = consultationMap.getValue(consultationID);

        if (consultationMap.size() != 0) {
            consultUI.patientViewAppoinmentUI();
            consultUI.displayAppoinment(consultationFound);
            confirm = consultUI.promptDeteleMsg();
            if (confirm) {
                consultationRecordHistory.put(consultationID, consultationFound);
                actionHistory.put(consultationID, "Delete");
                consultationMap.remove(consultationID);
                consultUI.displaySuccessfullyMsg(consultationID, "deleted");
            } else {
                consultUI.displayConsultationOperationMsg(consultationID, "Delete", "failed");
            }
        } else {
            consultUI.displayConsultationNotFound(consultationID);
        }

    }

    public static void viewPatientAppoinment(Object[] consulations, String patientID) { // current Patient (today appoinment)
        if (!consultationMap.isEmpty()) {
            Consultation.setCompare("appointmentStatus");
            consultationMap.sorting();
            consultUI.patientViewAppoinmentUI();
            for (Object c : consulations) {
                Consultation consultation = (Consultation) c;
                if (consultation.getPatient_Id().equals(patientID)) {
                    consultUI.displayAppoinment(consultation);
                }
            }
        } else {
            consultUI.displayConsultationNotFound();
        }
    }

    public static void viewTodayAppoinment(Object[] consulations, String staffID) { // current staff (how many patient staff need to see)
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // format only date part
        String todayStr = sdf.format(currentDate);
        if (!consultationMap.isEmpty()) {
            Consultation.setCompare("appointmentStatus");
            consultationMap.sorting();
            consultUI.staffViewAppoinmentUI();
            consultUI.appointmentFieldUI();
            for (Object c : consulations) {
                Consultation consultation = (Consultation) c;
                if (consultation.getStaff_Id().equals(staffID) && consultation.getConsultation_date().equals(todayStr)) {
                    consultUI.displayConsultationToString(consultation);
                }
            }
        } else {
            consultUI.displayConsultationNotFound();
        }
    }

    public static void viewTodayAppoinment(Object[] consulations) { // how many patient staff need to see
        if (!consultationMap.isEmpty()) {
            Consultation.setCompare("appointmentStatus");
            consultationMap.sorting();
            consultUI.appointmentFieldUI();
            for (Object c : consulations) {
                Consultation consultation = (Consultation) c;
                consultUI.displayConsultationToString(consultation);
            }
        } else {
            consultUI.displayConsultationNotFound();
        }
    }

    public void searchAppoinment() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        int choice = 0;

        do {
            choice = consultUI.searchAppointmentMenu();
            switch (choice) {
                case 1 -> {
                    String consultationId = consultUI.promptConsultationID();
                    findConsultation(consultationId, Consultation::getConsultation_Id);

//                    Consultation consultationFound = consultationMap.getValue(consultationId);
//                    if (consultationFound != null) {
//                        consultUI.staffSearchAppointmentUI();
//                        consultUI.displayAppoinment(consultationFound);
//                    } else {
//                        consultUI.displayConsultationNotFound(consultationId);
//                    }
                }
                case 2 -> {
                    String patientId = consultUI.promptPatientID();
                    consultUI.staffSearchAppointmentUI();
                    findConsultation(patientId, Consultation::getPatient_Id);
//                    Object[] values = consultationMap.getAllValues();
//                    for (Object obj : values) {
//                        Consultation c = (Consultation) obj;
//                        if (c.getPatient_Id().equalsIgnoreCase(patientId)) {
//                            consultUI.displayAppoinment(c);
//                        }
//                    }
                }
                case 3 -> {
                    Date consultationDate = consultUI.promptConsultationDate();
                    Consultation.setCompare("consultation_date");
                    consultationMap.sorting();
                    Object[] values = consultationMap.getAllValues();
                    consultUI.staffSearchAppointmentUI();
                    consultUI.appointmentFieldUI2();
                    findConsultation(dateFormat.format(consultationDate), c -> dateFormat.format(c.getConsultation_date()));
//                    for (Object obj : values) {
//                        Consultation c = (Consultation) obj;
//                        if (dateFormat.format(c.getConsultation_date()).equals(dateFormat.format(consultationDate))) {
//                            consultUI.displayConsultationToString(c);
//                        }
//                    }
                }
                case 4 -> {
                    consultUI.displayBackMsg();
                }
                default ->
                    consultUI.displayInvalidOptionMsg();
            }
        } while (choice != 4);
    }

    private void findConsultation(String value, Function<Consultation, String> getter) {
        Iterator<Consultation> iterator = consultationMap.getIterator();
        while (iterator.hasNext()) {
            Consultation consult = iterator.next();
            if (getter.apply(consult).equals(value)) {
                consultUI.displayAppoinment(consult);
            }

        }
    }

    //*
    public static void addOnlineAppoinment() {
        viewConsultationSchedule();
        Date currentDate = new Date();
        boolean valid = false;
        Date newConsultStartTime = null;
        Date newConsultEndTime = null;
        String staffID = "";

        String newConsultationID = generateNextConsultationId();
        Date newAppoinmentDate = currentDate;
        Date newConsultationDate = consultUI.promptConsultationDate();

        do {
            newConsultStartTime = consultUI.promptConsultationStartTime();
            newConsultEndTime = consultUI.promptConsultationEndTime();
            if (newConsultStartTime.after(newConsultEndTime)) {
                consultUI.displayFailedMsg("End time");
            } else {
                valid = true;
            }
        } while (!valid);

        do {
            staffID = consultUI.promptStaffID();
            Object staff = staffMap.getValue(staffID);
            Staff s = (Staff) staff;
            if (s.getDutyStatus().equalsIgnoreCase("Leave")) {
                consultUI.displayStaffLeave();
            } else {
                valid = true;
            }
        } while (!valid);

        Consultation newConsultation = new Consultation(newConsultationID, newAppoinmentDate, newConsultationDate, newConsultStartTime, newConsultEndTime, "Pending", "Online", currentPatientId, staffID);
        if (newConsultation != null) {
            consultationMap.put(newConsultationID, newConsultation);
            consultationRecordHistory.put(newConsultationID, newConsultation);
            actionHistory.put(newConsultationID, "Create");
            consultUI.displayConsultationOperationMsg(newConsultationID, "Create online", "successfully");
        } else {
            consultUI.displayFailedMsg("Create Appoinment ");
        }
    }

    public static void addWalkInAppoinment() {
        viewConsultationSchedule();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String num = Master.getCurrentTicket();
        Object[] staff = staffMap.getAllValues();

        Date newAppoinmentDate = null;
        Date newConsultationDate = null;
        Date newConsultStartTime = null;

        String newConsultationID = generateNextConsultationId();
        try {
            newAppoinmentDate = dateFormat.parse(dateFormat.format(currentDate));
            newConsultationDate = dateFormat.parse(dateFormat.format(currentDate));
            newConsultStartTime = timeFormat.parse(timeFormat.format(currentDate));
        } catch (Exception e) {
            System.out.println(e);
        }
        String currentPatientID = consultUI.promptPatientID();
        String staffID = consultUI.promptStaffID();
        for (Object obj : staff) {
            Staff s = (Staff) obj;
            if (s.getDutyStatus().equalsIgnoreCase("Leave")) {
                consultUI.displayStaffLeave();
            }
        }

        Consultation newConsultation = new Consultation(newConsultationID, newAppoinmentDate, newConsultationDate, newConsultStartTime, null, "Pending", "Walk-in", currentPatientID, staffID);

        if (newConsultation != null) {
            consultationMap.put(newConsultationID, newConsultation);
            consultationRecordHistory.put(newConsultationID, newConsultation);
            actionHistory.put(newConsultationID, "Create");
            consultUI.displayConsultationOperationMsg(newConsultationID, "Create Walk-in", "successfully");
            consultUI.displayConsultationWalkInNumber(num);
        } else {
            consultUI.displayFailedMsg("Create Appoinment ");
        }
    }

    public static void addFlwUpAppoinment() {
        Date currentDate = new Date();
        boolean valid = false;
        Date newConsultStartTime = null;
        Date newConsultEndTime = null;
        String staffID = "";

        String newConsultationID = generateNextConsultationId();
        Date newAppoinmentDate = currentDate;
        Date newConsultationDate = consultUI.promptConsultationDate();

        do {
            newConsultStartTime = consultUI.promptConsultationStartTime();
            newConsultEndTime = consultUI.promptConsultationEndTime();
            if (newConsultStartTime.after(newConsultEndTime)) {
                consultUI.displayFailedMsg("End time");
            } else {
                valid = true;
            }
        } while (!valid);

        String currentStaffID = Master.getCurrentStaffId();
        staffID = currentStaffID;
        String patientID = consultUI.promptPatientID();

        Consultation newConsultation = new Consultation(newConsultationID, newAppoinmentDate, newConsultationDate, newConsultStartTime, newConsultEndTime, "Pending", "Flw-up", patientID, staffID);

        if (newConsultation != null) {
            consultationMap.put(newConsultationID, newConsultation);
            consultationRecordHistory.put(newConsultationID, newConsultation);
            actionHistory.put(newConsultationID, "Create");
            consultUI.displayConsultationOperationMsg(newConsultationID, "Create follow up", "successfully");
            consultUI.displayNextConsultationDate(newConsultationDate, newConsultStartTime, newConsultEndTime);
        } else {
            consultUI.displayFailedMsg("Create Appoinment ");
        }
    }

    public static void updateAppoinment() {
        String consultationID = consultUI.promptConsultationID();
        int choice = 0;

        Consultation consultationFound = consultationMap.getValue(consultationID);
        if (!consultationFound.getAppointmentStatus().equalsIgnoreCase("Pending")) {
            consultUI.displayConsultationNotFound();
            return;
        }
        consultUI.editAppoinmentUI();
        consultUI.displayAppoinment(consultationFound);
        Consultation consultBackup = new Consultation(consultationFound.getConsultation_Id(), consultationFound.getAppointment_date(), consultationFound.getConsultation_date(), consultationFound.getConsultation_start_time(), consultationFound.getConsultation_end_time(), consultationFound.getAppointmentStatus(), consultationFound.getType(), consultationFound.getPatient_Id(), consultationFound.getStaff_Id());

        do {
            choice = consultUI.editAppoinmentOptionMenu();
            switch (choice) {
                case 1 -> {
                    Date consultationNewDate = consultUI.promptConsultationDate();
                    consultationFound.setConsultation_date(consultationNewDate);
                }
                case 2 -> {
                    Date consultationNewStartTime = consultUI.promptConsultationStartTime();
                    consultationFound.setConsultation_start_time(consultationNewStartTime);
                }
                case 3 -> {
                    Date consultationNewEndTime = consultUI.promptConsultationEndTime();
                    consultationFound.setConsultation_end_time(consultationNewEndTime);
                }
                case 4 -> {
                    String newStaffID = consultUI.promptStaffID();
                    consultationFound.setStaff_Id(newStaffID);
                }
                case 5 -> {

                }
                default ->
                    consultUI.displayInvalidOptionMsg();
            }

            consultationMap.put(consultationID, consultationFound);
            consultationRecordHistory.put(consultationID, consultBackup);
            actionHistory.put(consultationID, "Update");

            consultUI.displayConsultationOperationMsg(consultationID, "Update", "successfully");
            consultUI.displayAppoinment(consultationFound);
        } while (choice != 5);
    }

    public static void viewConsultationSchedule() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        consultationMap.sorting();

        consultUI.consultationScheduleUI();

        for (int i = 0; i < 3; i++) {
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Odd day = Doctor A, Even day = Doctor B
            MapInterface<Integer, String> doctorMap = (day % 2 != 0) ? doctorAMap : doctorBMap;

            consultUI.displayConsultationSchedulefield(day, month, year, c);
            consultUI.consultationScheduleUI(timeSlotMap);

            Object[] keys = doctorMap.getAllKeys();
            for (Object key : keys) {
                Integer id = (Integer) key;
                String staffID = doctorMap.getValue(id);

                if (staffMap.containsKey(staffID)) {
                    Staff staffData = staffMap.getValue(staffID);
                    consultUI.displayConsultationScheduleStaff(staffID, staffData);
                }

                Object[] timeSlots = timeSlotMap.getAllKeys();
                for (Object object : timeSlots) {
                    String slot = object.toString();
                    boolean booked = false;

                    Object[] consultations = consultationMap.getAllValues();
                    for (Object obj : consultations) {
                        Consultation consult = (Consultation) obj;

                        Calendar consultDate = Calendar.getInstance();
                        consultDate.setTime(consult.getConsultation_date());

                        if (consultDate.get(Calendar.YEAR) == year && (consultDate.get(Calendar.MONTH) + 1) == month && consultDate.get(Calendar.DAY_OF_MONTH) == day && consult.getStaff_Id().equals(staffID)) {
                            try {
                                Date slotTime = timeFormat.parse(slot);
                                Date start = consult.getConsultation_start_time();
                                Date end = consult.getConsultation_end_time();

                                if (!slotTime.before(start) && slotTime.before(end) && "Pending".equalsIgnoreCase(consult.getAppointmentStatus())) {
                                    booked = true;
                                    break;
                                }
                            } catch (Exception e) {
                                consultUI.displayCatchError(e);
                            }
                        }
                    }
                    consultUI.displayConsultationScheduleX(booked);
                }
                consultUI.displayLine();
                // move to the next day
            }
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public static void viewConsultationFlwUpReport() {
        Object[] consultations = consultationMap.getAllValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        consultUI.staffReportAppoinmentFlwUI();
        consultUI.appointmentFieldUI3();
        for (int i = 0; i < consultations.length; i++) {
            Consultation c1 = (Consultation) consultations[i];

            if ("Flw-up".equals(c1.getType())) {
                int count = 0;
                String date = dateFormat.format(c1.getConsultation_date());
                String staffID = c1.getStaff_Id();

                // Count how many same date + same staff
                for (Object consultation : consultations) {
                    Consultation c2 = (Consultation) consultation;
                    if ("Flw-up".equals(c2.getType()) && dateFormat.format(c2.getConsultation_date()).equals(date) && c2.getStaff_Id().equals(staffID)) {
                        count++;
                    }
                }

                // To avoid printing duplicate lines,
                boolean print = false;
                for (int k = 0; k < i; k++) {
                    Consultation c3 = (Consultation) consultations[k];
                    if ("Flw-up".equals(c3.getType()) && dateFormat.format(c3.getConsultation_date()).equals(date) && c3.getStaff_Id().equals(staffID)) {
                        print = true;
                        break;
                    }
                }

                if (!print) {
                    consultUI.displayFlwUpReport(date, staffID, count);
                }

            }
        }

    }

    public static void viewConsultationValumeReport() {
        Object[] consultations = consultationMap.getAllValues();

        boolean running = true;
        while (running) {
            consultUI.staffReportAppoinmentUI();
            viewTodayAppoinment(consultations);

            int choice = consultUI.consultationFlwUpMenu();
            do {
                switch (choice) {
                    case 1 ->
                        filterConsultation(consultations, "year");
                    case 2 ->
                        filterConsultation(consultations, "month");
                    case 3 ->
                        filterConsultation(consultations, "day");
                    case 4 -> {
                        consultUI.displayBackMsg();
                        running = false;
                    }
                    default -> {

                    }
                }
            } while (choice != 4);
        }
    }

    public static void filterConsultation(Object[] consultations, String type) {
        SimpleDateFormat sdf;
        String currentDate = "";
        int count = 0;
        switch (type) {
            case "year" -> {
                sdf = new SimpleDateFormat("yyyy");
                currentDate = sdf.format(new Date());
                consultUI.displayFilterConsultation(type, currentDate);
                consultUI.staffReportYearAppoinmentUI();
            }
            case "month" -> {
                sdf = new SimpleDateFormat("yyyy-MM");
                currentDate = sdf.format(new Date());
                consultUI.displayFilterConsultation(type, currentDate);
                consultUI.staffReportMonthAppoinmentUI();
            }
            case "day" -> {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                currentDate = sdf.format(new Date());
                consultUI.displayFilterConsultation(type, currentDate);
                consultUI.staffReportDayAppoinmentUI();
            }
            default -> {
                return;
            }
        }

        consultUI.appointmentFieldUI();
        for (Object obj : consultations) {
            Consultation c = (Consultation) obj;
            String date = sdf.format(c.getConsultation_date());

            if (date.equals(currentDate)) {
                System.out.println(c.toStaffString());
                count++;
            }
        }
        if (count > 0) {
            consultUI.displayTotalVolume(count);
        } else {
            consultUI.displayNotFoundCurrentDate(currentDate);
        }

    }

    public static String generateNextConsultationId() {
        String lastID = consultationMap.getLastKey();

        if (lastID == null || lastID.isEmpty()) {
            lastID = "C000000";
        }
        return IDGenerator.generateNextID(lastID);
    }

    private void undo() {
        if (actionHistory.isEmpty()) {
            consultUI.displayFailedMsg("No history to undo");
            return;
        }
        String lastAction = actionHistory.removeLast();
        String lastId = consultationRecordHistory.getLastKey();
        Consultation lastConsult = consultationRecordHistory.removeLast();

        switch (lastAction) {
            case "Create" -> {
                consultationMap.remove(lastId);
                consultUI.displaySuccessfullyMsg(lastId, " Undo Create");
            }
            case "Update" -> {
                consultationMap.put(lastId, lastConsult);
                consultUI.displaySuccessfullyMsg(lastId, " Undo Update");
            }
            case "Delete" -> {
                consultationMap.put(lastId, lastConsult);
                consultUI.displaySuccessfullyMsg(lastId, " Undo Delete");
            }
        }
    }

}
