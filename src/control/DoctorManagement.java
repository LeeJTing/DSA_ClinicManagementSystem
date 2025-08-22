package control;

import adt.LinkedHashMap;
import adt.MapInterface;
import boundary.StaffUI;
import dao.Initializer;
import dao.Master;
import entity.Consultation;
import entity.Medicine;
import entity.Payment;
import entity.Prescription;
import entity.Staff;
import entity.Treatment;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.util.Iterator;
import java.util.function.Function;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Wong Wei Xin
 */
public class DoctorManagement implements CRUD {

    private static MapInterface<String, Medicine> medicineMap = new LinkedHashMap<>();
    private static MapInterface<String, Payment> paymentMap = new LinkedHashMap<>();
    private static MapInterface<String, Treatment> treatmentMap = new LinkedHashMap<>();
    private static MapInterface<String, Consultation> consultationMap = new LinkedHashMap<>();
    private static MapInterface<String, Prescription> prescriptionMap = new LinkedHashMap<>();
    public static MapInterface<String, Staff> staffMap = new LinkedHashMap<>();
    public static MapInterface<LocalDate, MapInterface<Integer, String>> dutyScheduleMap = new LinkedHashMap<>();
    private static MapInterface<String, Staff> doctorReportMap = new LinkedHashMap<>();
    private static MapInterface<Integer, Staff> staffRecordHistory = new LinkedHashMap<>();
    private static MapInterface<Integer, String> actionHistory = new LinkedHashMap<>();

    public static StaffUI staffmenu = new StaffUI();
    public static final Master Master = new Master();
    private static Staff staffFound = new Staff();
    private static int historyKey = 1;

    public DoctorManagement() {
        getAllMap();
    }

    private void getAllMap() {
        staffMap = Master.getStaffMap();
        dutyScheduleMap = Master.getDutyScheduleMap();
        medicineMap = Master.getMedicineMap();
        paymentMap = Master.getPaymentMap();
        treatmentMap = Master.getTreatmentMap();
        consultationMap = Master.getConsultationMap();
        prescriptionMap = Master.getPrescriptionMap();
    }

    public void doctorManagementOuter() {
        updateDutyStatus();

        if (login()) {
            int staffOption;
            do {
                staffOption = staffmenu.doctorManagementMenu(staffFound);
                switchDoctorMenu(staffOption);
            } while (staffOption != 10);
        }
    }

    public void updateDutyStatus() {
        LocalDate today = LocalDate.now();
        var doctorsOnDuty = dutyScheduleMap.getValue(today); //get today record in schedule map

        Iterator<Staff> iterator = staffMap.getIterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();

            if ("Doctor".equalsIgnoreCase(staff.getStaffPosition())) {
                boolean isWorking = false;

                //do compare if the staff does not exit in the record mean leave
                if (doctorsOnDuty != null) {
                    isWorking = doctorsOnDuty.containsValue(staff.getStaffID());
                }

                staff.setDutyStatus(isWorking ? "Work" : "Leave");
                staffMap.put(staff.getStaffID(), staff);
            }
        }
        Master.setStaffMap(staffMap);
    }

    public boolean login() {
        boolean authenticated = false;

        while (!authenticated) {
            String staffId = staffmenu.promptStaffID();
            String password = staffmenu.promptPassword();

            // Check if staff ID exists
            if (staffMap.containsKey(staffId)) {
                staffFound = staffMap.getValue(staffId);

                // Check password
                if (staffFound.getStaffPassword().equals(password)) {
                    Master.setCurrentStaffId(staffId);
                    staffmenu.printWelcomeMsg(staffFound.getStaffName());
                    authenticated = true;
                } else {
                    staffmenu.printInvalidInput();
                }
            } else {
                staffmenu.displayDoctorNotFound();
            }
        }

        return true;
    }

    public void switchDoctorMenu(int doctorManagementChoice) {
        switch (doctorManagementChoice) {
            case 1:
                displayProfile();
                break;
            case 2:
                updateInstance();
                break;
            case 3:
                deleteInstance();
                break;
            case 4:
                viewDutySchedule();
                break;
            case 5:
                createNewInstance();
                break;
            case 6:
                readInstance();
                break;
            case 7:
                displayPerformanceReport();
                break;
            case 8:
                displayExperienceReport();
                break;
            case 9:
                undo();
            case 10:
                //call main menu()
                break;
            default:
                staffmenu.printInvalidInput();
                break;
        }

    }

    @Override
    public void updateInstance() {
        staffmenu.displayProfileUI(staffFound, convertEducationLevel(staffFound.getEducationalLevel()));
        int choice = staffmenu.editProfileMenu();
        switch (choice) {
            case 1:
                updateProfile("password");
                break;
            case 2:
                updateProfile("contact");
                break;
            case 3:
                updateProfile("email");
                break;
            case 4:
                changeEducationLevel();
                break;
            case 5:
                return;
            default:
                staffmenu.printInvalidInput();
        }
    }

    public void saveHistory(Staff staff, String action, Integer slotNumber, LocalDate leaveDate) {
        staffRecordHistory.put(historyKey, new Staff(staff));

        if (slotNumber != null && action.equals("Leave")) {
            actionHistory.put(historyKey, action + ":" + slotNumber + ":" + leaveDate);
        } else {
            actionHistory.put(historyKey, action);
        }

        historyKey++;
    }

    public void updateProfile(String fieldType) {

        saveHistory(staffFound, "Update", null, null);

        String newValue = staffmenu.promptEditMsg(fieldType);
        switch (fieldType) {
            case "password":
                staffFound.setStaffPassword(newValue);
                break;
            case "contact":
                staffFound.setStaffContact(newValue);
                break;
            case "email":
                staffFound.setStaffEmail(newValue);
                break;
        }

        staffMap.put(staffFound.getStaffID(), staffFound);
        Master.setStaffMap(staffMap);
        staffmenu.printUpdateSuccessMsg();
        displayProfile();
    }

    public void changeEducationLevel() {
        int educationalLevel = staffmenu.changeEducationalLevelMenu();
        if (educationalLevel >= 1 && educationalLevel <= 4) {
            staffFound.setEducationalLevel(educationalLevel);
            staffMap.put(staffFound.getStaffID(), staffFound);
            staffmenu.printUpdateSuccessMsg();
            displayProfile();
        } else {
            staffmenu.printInvalidInput();
        }

    }

    public void displayProfile() {
        staffmenu.displayProfileUI(staffFound, convertEducationLevel(staffFound.getEducationalLevel()));
        if (exitConfirmation()) {
            return;
        }
    }

    @Override
    public void createNewInstance() {
        int choice = staffmenu.leaveApplicationUI();
        if (choice == 8) {
            return;
        } else {
            LocalDate today = LocalDate.now();
            LocalDate leaveDate = today.plusDays(choice + 2);

            // Find which slot this staff occupies on that leaveDate
            MapInterface<Integer, String> doctorsOnDuty = dutyScheduleMap.getValue(leaveDate);
            Integer slotNumber = doctorsOnDuty.getKey(staffFound.getStaffID());

            if (slotNumber != null) {
                saveHistory(staffFound, "Leave", slotNumber, leaveDate);
            }

            staffmenu.promptLeavSuccess(leaveDate);
            updateLeaveDate(staffFound, leaveDate);
            if (exitConfirmation()) {
                return;
            }
        }
    }

    public void updateLeaveDate(Staff loggedInStaff, LocalDate leaveDate) {
        MapInterface<Integer, String> doctorsOnDuty = dutyScheduleMap.getValue(leaveDate);
        Integer keyToRemove = doctorsOnDuty.getKey(loggedInStaff.getStaffID());

        if (keyToRemove != null) {
            doctorsOnDuty.remove(keyToRemove);
            Master.setDutyScheduleMap(dutyScheduleMap);
            dutyScheduleMap = Master.getDutyScheduleMap();
        }
    }

    public boolean exitConfirmation() {
        while (true) {
            char exitOption = staffmenu.exitConfirmationUI();

            switch (exitOption) {
                case 'Y':
                    staffmenu.printExitMsg();
                    return true;
                case 'N':
                    return false;
                default:
                    staffmenu.printInvalidInput();
                    break;
            }
        }
    }

    @Override
    public void deleteInstance() {
        Staff lastDeletedStaff;
        if (staffmenu.confirmDeleteAccountUI()) {
            // Keep reference for possible undo
            lastDeletedStaff = staffFound;

            // Perform deletion
            staffMap.remove(staffFound.getStaffID());
            staffmenu.deleteSuccessMsg();
            staffFound = null;
            Master.setStaffMap(staffMap);
            // undo option
            if (staffmenu.confirmUndoUI()) {
                undoDeletion(lastDeletedStaff);
            }
            staffmenu.logOutMsg();

        } else {
            staffmenu.doctorManagementMenu(staffFound);
        }
    }

    public void viewDutySchedule() {
        int weekNumber = staffmenu.viewDutyScheduleMenu();

        if (weekNumber == 6) {
            return;
        } else {

            YearMonth ym = YearMonth.of(2025, 8);
            LocalDate firstDayOfMonth = ym.atDay(1);

            // Compute week start correctly
            LocalDate weekStart = firstDayOfMonth.plusDays((weekNumber - 1) * 7);

            staffmenu.printScheduleHeader();

            for (int i = 0; i < 7; i++) {
                LocalDate date = weekStart.plusDays(i);

                // Stop if date goes past end of month
                if (date.getMonth() != Month.AUGUST) {
                    break;
                }

                staffmenu.printDate(date);

                MapInterface<Integer, String> group = dutyScheduleMap.getValue(date);
                if (group != null) {
                    Object[] staffIds = (Object[]) group.getAllValues();
                    for (Object staffIdObj : staffIds) {
                        String staffId = String.valueOf(staffIdObj);
                        Staff staff = staffMap.getValue(staffId);
                        if (staff != null) {
                            staffmenu.printDoctorOnDuty(staff.getStaffName());
                        }
                    }
                }
                System.out.println();
            }

            staffmenu.printSceduleFooter();

            if (exitConfirmation()) {
                return;
            }
        }
    }

    public String convertEducationLevel(int educationLevel) {
        switch (educationLevel) {
            case 1:
                return "Bachelor of Medicine";
            case 2:
                return "Doctor of Medicine";
            case 3:
                return "Doctor of Philosophy";
            case 4:
                return "Fellowship";
            default:
                return "Unknown";
        }
    }

    @Override
    public void readInstance() {
        Staff doctorFilter = new Staff();
        doctorFilter.setCompare("position");
        doctorFilter.setStaffPosition("Doctor");

        MapInterface<String, Staff> doctorsMap = staffMap.groupBy(doctorFilter); //using temporary staff object that want doctor position into grouping

        staffmenu.displayDoctorsHeader();
        Iterator<Staff> iterator = doctorsMap.getIterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            staffmenu.displayDoctorsUI(staff, convertEducationLevel(staff.getEducationalLevel()));
        }
        staffmenu.displayDoctorsFooter();
        searchDoctor();
    }

    public void searchDoctor() {
        int searchOption = staffmenu.promptDoctorSearch();
        switch (searchOption) {
            case 1:
                String id = staffmenu.promptStaffID();
                findDoctor(id, Staff::getStaffID);
                break;
            case 2:
                String name = staffmenu.promptStaffName();
                findDoctor(name, Staff::getStaffName);
                break;
            case 3:
                String dutyStatus = staffmenu.promptStaffStatus();
                findDoctor(dutyStatus, Staff::getDutyStatus);
                break;
            case 4:
                return;
            default:
                staffmenu.printInvalidInput();
                break;
        }
    }

    public void findDoctor(String searchValue, Function<Staff, String> getter) {
        boolean found = false;
        Iterator<Staff> iterator = staffMap.getIterator();

        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if ("Doctor".equalsIgnoreCase(staff.getStaffPosition())
                    && getter.apply(staff).equalsIgnoreCase(searchValue)) {

                if (!found) {
                    staffmenu.displayDoctorsHeader();
                }
                staffmenu.displayDoctorsUI(staff, convertEducationLevel(staff.getEducationalLevel()));
                found = true;
            }
        }
        if (found) {
            staffmenu.displayDoctorsFooter();
        } else {
            staffmenu.displayDoctorNotFound();
        }
        searchDoctor();
    }

    public void displayPerformanceReport() {
        int option = staffmenu.displayPerformanceReportMenu();
        switch (option) {
            case 1:
                displayStaffDurationReport();
                break;
            case 2:
                displayStaffPatientCountReport();
                break;
            default:
                break;
        }
    }

    public void displayStaffDurationReport() {
        staffmenu.printHigestConsultTime();
        staffmenu.printHighDurationReportHeader();

        Iterator<Staff> staffIterator = staffMap.getIterator();
        while (staffIterator.hasNext()) {
            Staff staff = staffIterator.next();

            if ("Doctor".equalsIgnoreCase(staff.getStaffPosition())) {
                int totalDuration = 0;

                Iterator<Consultation> consultIterator = consultationMap.getIterator();
                while (consultIterator.hasNext()) {
                    Consultation consult = consultIterator.next();

                    if (consult.getStaff_Id().equalsIgnoreCase(staff.getStaffID())
                            && "completed".equalsIgnoreCase(consult.getAppointmentStatus())) {
                        totalDuration += consult.calculationDurationTimeInMinutes();
                    }
                }

                Staff newStaff = new Staff(staff, 0, totalDuration, "patients");
                newStaff.setCompare("duration");
                doctorReportMap.put(staff.getStaffID(), newStaff);
            }
        }

        doctorReportMap.sorting();
        Iterator<Staff> exIterator = doctorReportMap.getIterator();
        while (exIterator.hasNext()) {
            Staff staff = exIterator.next();
            staffmenu.performanceReportUI(staff, "duration");
        }
        staffmenu.printLine();
        if (exitConfirmation()) {
            return;
        }
    }

    public void displayStaffPatientCountReport() {
        staffmenu.printHigestPatientCount();
        staffmenu.printHighPatientsReportHeader();

        Iterator<Staff> staffIterator = staffMap.getIterator();
        while (staffIterator.hasNext()) {
            Staff staff = staffIterator.next();

            if ("Doctor".equalsIgnoreCase(staff.getStaffPosition())) {
                int totalPatients = 0;

                Iterator<Consultation> consultIterator = consultationMap.getIterator();
                while (consultIterator.hasNext()) {
                    Consultation consult = consultIterator.next();

                    if (consult.getStaff_Id().equalsIgnoreCase(staff.getStaffID())
                            && "completed".equalsIgnoreCase(consult.getAppointmentStatus())) {
                        totalPatients++;
                    }
                }
                Staff newStaff = new Staff(staff, totalPatients, 0, "patients");
                newStaff.setCompare("patients");
                doctorReportMap.put(staff.getStaffID(), newStaff);
            }
        }
        doctorReportMap.sorting();

        Iterator<Staff> exIterator = doctorReportMap.getIterator();
        while (exIterator.hasNext()) {
            Staff staff = exIterator.next();
            staffmenu.performanceReportUI(staff, "patients");
        }
        staffmenu.printLine();
        if (exitConfirmation()) {
            return;
        }
    }

    public void displayExperienceReport() {
        staffmenu.printExperinceTitle();
        staffmenu.printExperienceReportHeader();

        Iterator<Staff> staffIterator = staffMap.getIterator();
        while (staffIterator.hasNext()) {
            Staff staff = staffIterator.next();

            if ("Doctor".equalsIgnoreCase(staff.getStaffPosition())) {

                int clinicYears = Period.between(staff.getJoinedDate(), LocalDate.now()).getYears();

                int industryYears = staff.getServiceDuration();

                int educationPoints = staff.getEducationalLevel();

                int score = (clinicYears * 2) + industryYears + educationPoints;
                Staff newStaff = new Staff(staff, clinicYears, score, "score");
                newStaff.setCompare("score");
                doctorReportMap.put(staff.getStaffID(), newStaff);
            }
        }

        doctorReportMap.sorting();
        Iterator<Staff> exIterator = doctorReportMap.getIterator();
        while (exIterator.hasNext()) {
            Staff staff = exIterator.next();
            staffmenu.experienceReportUI(staff);
        }
        staffmenu.printExperienceLine();
        filterExprienceReport();
    }

    public void filterExprienceReport() {
        int option = staffmenu.promptTopInput();
        printTopPerformers(option);
    }

    public void printTopPerformers(int option) {
        staffmenu.printTopDoctor();
        staffmenu.printExperienceReportHeader();

        int i = 0;
        Staff staff;

        // Keep removing doctors from the data structure and displaying them
        while (i < option && (staff = doctorReportMap.removeFirst()) != null) {
            staffmenu.experienceReportUI(staff);
            i++;
        }

        staffmenu.printExperienceLine();

        if (exitConfirmation()) {
            return;
        }
    }

    public void undo() {
        if (actionHistory.isEmpty()) {
            staffmenu.printInvalidInput();
            return;
        }

        String lastAction = actionHistory.removeLast();
        Staff prevStaff = staffRecordHistory.removeLast();

        if (lastAction.startsWith("Leave")) {
            String[] parts = lastAction.split(":");
            int slotNumber = Integer.parseInt(parts[1]);
            LocalDate leaveDate = LocalDate.parse(parts[2]);

            MapInterface<Integer, String> doctorsOnDuty = dutyScheduleMap.getValue(leaveDate);

            if (doctorsOnDuty != null) {
                doctorsOnDuty.put(slotNumber, prevStaff.getStaffID());
                staffFound = prevStaff;
                staffmenu.printUndoMsg("leave");
            }
            viewDutySchedule();

        } else if (lastAction.equals("Update")) {
            staffMap.put(prevStaff.getStaffID(), prevStaff);
            staffFound = prevStaff;
            staffmenu.printUndoMsg("update");
            displayProfile();
        } else {
            staffmenu.printInvalidInput();
        }
    }

    public void undoDeletion(Staff lastDeletedStaff) {
        if (lastDeletedStaff != null) {
            staffMap.put(lastDeletedStaff.getStaffID(), lastDeletedStaff);
            staffFound = lastDeletedStaff;

            staffmenu.printUndoMsg("delete");
            staffmenu.doctorManagementMenu(staffFound);
            lastDeletedStaff = null;
        } else {
            staffmenu.printInvalidInput();
        }
    }
}
