/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.MapInterface;
import boundary.ConsultationUI;
import dao.Master;
import entity.Consultation;
import entity.Staff;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import utility.IDGenerator;
import utility.Input;

/**
 *
 * @author Tan Kok Hong
 */
public class ConsultationManagement implements CRUD {

    public static void main(String[] args) {
        Master.initializer();
        ConsultationManagement c = new ConsultationManagement();
        c.mainMenu();
    }

    public void mainMenu() {
        Master.initializer();
        String currentPatientId = Master.getCurrentPatientId();
        String currentStaffId = Master.getCurrentStaffId();
        int choice = 0;

        do {
            choice = ConsultationUI.consultationMenu();
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
//                        System.out.println("\t\t No user is currently logged in.");
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
//                        System.out.println("\t\t No user is currently logged in.");
//                    }
                }
                case 5 -> { // update Appointment (patient)
                    deleteInstance();
//                    if (currentPatientId != null && !currentPatientId.isEmpty()) {
//                        deleteInstance();
//                    } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
//                        System.out.println("\t\t The Staff not avaliable update appoinment");
//                    } else {
//                        System.out.println("\t\t No user is currently logged in.");
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
//                        System.out.println("\t\t No user is currently logged in.");
//                    }
                }
                case 8 -> { // view Consultation Valume Report
                    viewConsultationValumeReport();
//                    if (currentPatientId != null && !currentPatientId.isEmpty()) {
//                        System.out.println("\t\t The patient not avaliable view report");
//                    } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
//                        viewConsultationValumeReport();
//                    } else {
//                        System.out.println("\t\t No user is currently logged in.");
//                    }
                }
                case 9 -> {        // back

                }
                default ->
                    throw new AssertionError();
            }
        } while (choice != 8);
    }

    public static void addAppointmentMenu() {
        int choice = 0;

        do {
            choice = ConsultationUI.addAppointmentMenu();
            switch (choice) {
                case 1 -> // online
                    addOnlineAppoinment();
                case 2 -> // walk in
                    addWalkInAppoinment();
                case 3 -> {
                }
                default ->
                    throw new AssertionError();
            }
            // back
        } while (choice != 3);
    }

    @Override
    public void createNewInstance() {
//        String currentPatientId = "P000001";
        String currentStaffId = "S000001";
        String currentPatientId = Master.getCurrentPatientId();
//        String currentStaffId = Master.getCurrentStaffId();
        MapInterface<String, Consultation> consulationMap = Master.getConsultationMap();
        Object[] consulations = consulationMap.getAllValues();

        if (currentPatientId != null && !currentPatientId.isEmpty()) {
            addAppointmentMenu();
        } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
            addFlwUpAppoinment();
        } else {
            System.out.println("\t\t No user is currently logged in.");
        }
    }

    @Override
    public void readInstance() {
        String currentPatientId = "P000001";
//        String currentStaffId = "S000001";
//        String currentPatientId = Master.getCurrentPatientId();
        String currentStaffId = Master.getCurrentStaffId();
        MapInterface<String, Consultation> consulationMap = Master.getConsultationMap();
        Object[] consulations = consulationMap.getAllValues();

        if (currentPatientId != null && !currentPatientId.isEmpty()) {
            viewPatientAppoinment(consulations, currentPatientId);
        } else if (currentStaffId != null && !currentStaffId.isEmpty()) {
            viewTodayAppoinment(consulations, currentStaffId);
        } else {
            System.out.println("\t\t No user is currently logged in.");
        }

    }

    @Override
    public void updateInstance() {
        updateAppoinment();
    }

    @Override
    public void deleteInstance() {
        boolean confirm;
        MapInterface<String, Consultation> consulationMap = Master.getConsultationMap();
        ConsultationUI.deleteAppoinmentUI();
        String consultationID = ConsultationUI.promptConsultationID();
        Consultation consultationFound = consulationMap.getValue(consultationID);

        ConsultationUI.patientViewAppoinmentUI();
        ConsultationUI.displayAppoinment(consultationFound);
        confirm = Input.getBooleanInput("\t\t Are you sure you want to delete this consultation? ");
        if (confirm) {
            consulationMap.remove(consultationID);
            System.out.println("\t\t Consultation with ID " + consultationID + " deleted successfully.");
        }
//        else{
//            
//        }

    }

    public static void viewPatientAppoinment(Object[] consulations, String patientID) { // current Patient (today appoinment)
        ConsultationUI.patientViewAppoinmentUI();
        for (Object c : consulations) {
            Consultation consultation = (Consultation) c;
            if (consultation.getPatient_Id().equals(patientID)) {
                ConsultationUI.displayAppoinment(consultation);
            }
        }
    }

    public static void viewTodayAppoinment(Object[] consulations, String staffID) { // current staff (how many patient staff need to see)

        ConsultationUI.staffViewAppoinmentUI();
        ConsultationUI.appointmentFieldUI();
        for (Object c : consulations) {
            Consultation consultation = (Consultation) c;
            if (consultation.getStaff_Id().equals(staffID)) {
                System.out.println(consultation.toStaffString());
            }
        }

    }

    public static void viewTodayAppoinment(Object[] consulations) { // current staff (how many patient staff need to see)
        ConsultationUI.appointmentFieldUI();
        for (Object c : consulations) {
            Consultation consultation = (Consultation) c;
            System.out.println(consultation.toStaffString());
        }
    }

    public static void searchAppoinment() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        MapInterface<String, Consultation> consultationMap = Master.getConsultationMap();
        int choice = ConsultationUI.searchAppointmentMenu();
        switch (choice) {
            case 1 -> {
                String consultationId = ConsultationUI.promptConsultationID();
                Consultation consultationFound = consultationMap.getValue(consultationId);
                if (consultationFound != null) {
                    ConsultationUI.staffSearchAppointmentUI();
                    ConsultationUI.displayAppoinment(consultationFound);
                } else {
                    System.out.println("\t\t No consultation found with ID: " + consultationId);
                }
            }
            case 2 -> {
                String patientId = ConsultationUI.promptPatientID();
                ConsultationUI.staffSearchAppointmentUI();

                Object[] values = consultationMap.getAllValues();
                for (Object obj : values) {
                    Consultation c = (Consultation) obj;
                    if (c.getPatient_Id().equalsIgnoreCase(patientId)) {
                        ConsultationUI.displayAppoinment(c);
                    }
                }
            }
            case 3 -> {
                Date consultationDate = ConsultationUI.promptConsultationDate();
                consultationMap.sorting();
                Object[] values = consultationMap.getAllValues();
                ConsultationUI.staffSearchAppointmentUI();
                ConsultationUI.appointmentFieldUI2();
                for (Object obj : values) {
                    Consultation c = (Consultation) obj;
                    if (dateFormat.format(c.getConsultation_date()).equals(dateFormat.format(consultationDate))) {
                        System.out.println(c.toAllString());
                    }
                }
            }
            default ->
                throw new AssertionError();
        }
    }

    public static void addOnlineAppoinment() {
        Date currentDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        MapInterface<String, Consultation> consultationMap = Master.getConsultationMap();

        String newConsultationID = generateNextConsultationId();
        Date newAppoinmentDate = currentDate;
        Date newConsultationDate = ConsultationUI.promptConsultationDate();
        Date newConsultStartTime = ConsultationUI.promptConsultationStartTime();
        Date newConsultEndTime = ConsultationUI.promptConsultationEndTime();
        String staffID = ConsultationUI.promptStaffID();
//        String currentPatientID = Master.getCurrentPatientId();
        String currentPatientID = "P000001";

        Consultation newConsultation = new Consultation(newConsultationID, newAppoinmentDate, newConsultationDate, newConsultStartTime, newConsultEndTime, "Pending", "Online", currentPatientID, staffID);
        consultationMap.put(newConsultationID, newConsultation);
        System.out.println("\t\t Online Appointment created successfully: " + newConsultationID);
    }

    public static void addWalkInAppoinment() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        MapInterface<String, Consultation> consultationMap = Master.getConsultationMap();
        String num = Master.getCurrentTicket();

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
        String currentPatientID = ConsultationUI.promptPatientID();
        String staffID = ConsultationUI.promptStaffID();

        Consultation newConsultation = new Consultation(newConsultationID, newAppoinmentDate, newConsultationDate, newConsultStartTime, null, "Pending", "Walk-in", currentPatientID, staffID);
        consultationMap.put(newConsultationID, newConsultation);
        System.out.println("\t\t Walk-in Appointment created successfully: " + newConsultationID);
        System.out.println("\t\t This is your Walk-in number: " + num);
    }

    public static void addFlwUpAppoinment() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        MapInterface<String, Consultation> consultationMap = Master.getConsultationMap();

        String newConsultationID = generateNextConsultationId();
        Date newAppoinmentDate = currentDate;
        Date newConsultationDate = ConsultationUI.promptConsultationDate();
        Date newConsultStartTime = ConsultationUI.promptConsultationStartTime();
        Date newConsultEndTime = ConsultationUI.promptConsultationEndTime();
        String currentStaffID = Master.getCurrentStaffId();
        String staffID = currentStaffID;
        String patientID = ConsultationUI.promptPatientID();

        Consultation newConsultation = new Consultation(newConsultationID, newAppoinmentDate, newConsultationDate, newConsultStartTime, newConsultEndTime, "Pending", "Flw-up", patientID, staffID);
        consultationMap.put(newConsultationID, newConsultation);
        System.out.println("\t\t Follow Up Appointment created successfully: " + newConsultationID);
        System.err.println("\t\t Next Consultation Date is" + dateFormat.format(newConsultationDate) + "Time is" + timeFormat.format(newConsultStartTime) + "-" + timeFormat.format(newConsultEndTime));
    }

    public static void updateAppoinment() {
        String consultationID = ConsultationUI.promptConsultationID();
        MapInterface<String, Consultation> consultationMap = Master.getConsultationMap();
        Consultation consultationFound = consultationMap.getValue(consultationID);
        ConsultationUI.editAppoinmentUI();
        ConsultationUI.displayAppoinment(consultationFound);

        int choice = ConsultationUI.editAppoinmentOptionMenu();
        switch (choice) {
            case 1 -> {
                Date consultationNewDate = ConsultationUI.promptConsultationDate();
                consultationFound.setConsultation_date(consultationNewDate);
            }
            case 2 -> {
                Date consultationNewStartTime = ConsultationUI.promptConsultationStartTime();
                consultationFound.setConsultation_start_time(consultationNewStartTime);
            }
            case 3 -> {
                Date consultationNewEndTime = ConsultationUI.promptConsultationEndTime();
                consultationFound.setConsultation_end_time(consultationNewEndTime);
            }
            case 4 -> {
                String newStaffID = ConsultationUI.promptStaffID();
                consultationFound.setStaff_Id(newStaffID);
            }
            default ->
                throw new AssertionError();
        }
        consultationMap.put(consultationID, consultationFound);
        System.out.println("Update Successfully");
    }

    public static void viewConsultationSchedule() {
        MapInterface<String, Consultation> consultationMap = Master.getConsultationMap();
        MapInterface<String, Staff> staffMap = Master.getStaffMap();
        MapInterface<Integer, String> doctorAMap = Master.getDoctorAMap();
        MapInterface<Integer, String> doctorBMap = Master.getDoctorBMap();
        MapInterface<String, String> timeSlotMap = Master.getTimeSlotMap();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        consultationMap.sorting();

        ConsultationUI.consultationScheduleUI();

        for (int i = 0; i < 3; i++) {
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Odd day = Doctor A, Even day = Doctor B
            MapInterface<Integer, String> doctorMap = (day % 2 != 0) ? doctorAMap : doctorBMap;

            System.out.printf("\n\t\t Date: %02d-%02d-%04d (%s)\n", day, month, year, c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
            ConsultationUI.consultationScheduleUI(timeSlotMap);

            Object[] keys = doctorMap.getAllKeys();
            for (Object key : keys) {
                Integer id = (Integer) key;
                String staffID = doctorMap.getValue(id);

                if (staffMap.containsKey(staffID)) {
                    Staff staffData = staffMap.getValue(staffID);
                    System.out.printf("\n\t\t %s %-6s %s %-12s %s", "|", staffID, "|", staffData.getStaffName(), "|");
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

                                if (!slotTime.before(start) && slotTime.before(end)) {
                                    booked = true;
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }

                        }
                    }
                    System.out.printf("%-6s %s", booked ? "   X" : "", "|");

                }
            }
            System.out.println("\n\t\t ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // move to the next day
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public static void viewConsultationFlwUpReport() {
        MapInterface<String, Consultation> consulationMap = Master.getConsultationMap();
        Object[] consultations = consulationMap.getAllValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        ConsultationUI.staffReportAppoinmentFlwUI();
        ConsultationUI.appointmentFieldUI3();
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

                    ConsultationUI.displayFlwUpReport(date, staffID, count);
                }

            }
        }

    }

    public static void viewConsultationValumeReport() {
        MapInterface<String, Consultation> consulationMap = Master.getConsultationMap();
        Object[] consultations = consulationMap.getAllValues();

        boolean running = true;
        while (running) {
            ConsultationUI.staffReportAppoinmentUI();
            viewTodayAppoinment(consultations);

            int choice = ConsultationUI.consultationFlwUpMenu();
            switch (choice) {
                case 1 ->
                    filterConsultation(consultations, "year");
                case 2 ->
                    filterConsultation(consultations, "month");
                case 3 ->
                    filterConsultation(consultations, "day");
                case 4 -> {
                    System.out.println("\t\t Returning to main menu...");
                    running = false;
                }
                default -> {
                    System.out.println("\t\t Invalid option. Returning to menu...");
                }

            }
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
                System.out.println("\n\t\t Consultation for current" + type + " (" + currentDate + "):");
                ConsultationUI.staffReportYearAppoinmentUI();
            }
            case "month" -> {
                sdf = new SimpleDateFormat("yyyy-MM");
                currentDate = sdf.format(new Date());
                System.out.println("\n\t\t Consultation for current" + type + " (" + currentDate + "):");
                ConsultationUI.staffReportMonthAppoinmentUI();
            }
            case "day" -> {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                currentDate = sdf.format(new Date());
                System.out.println("\n\t\t Consultation for current" + type + " (" + currentDate + "):");
                ConsultationUI.staffReportDayAppoinmentUI();
            }
            default -> {
                return;
            }
        }

        ConsultationUI.appointmentFieldUI();
        for (Object obj : consultations) {
            Consultation c = (Consultation) obj;
            String date = sdf.format(c.getConsultation_date());

            if (date.equals(currentDate)) {
                System.out.println(c.toStaffString());
                count++;
            }
        }
        if (count > 0) {
            System.out.println("\t\t Total Consultation Volume is: " + count);
        } else {
            System.out.println("\t\t No Consultation found for " + currentDate);
        }

    }

    public static String generateNextConsultationId() {
        MapInterface<String, Consultation> consulationMap = Master.getConsultationMap();
        String lastID = consulationMap.getLastKey();

        if (lastID == null || lastID.isEmpty()) {
            lastID = "C000000";
        }
        return IDGenerator.generateNextID(lastID);
    }

}
