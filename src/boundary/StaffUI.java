/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Staff;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class StaffUI {

    Scanner scanner = new Scanner(System.in);

    public int doctorManagementMenu(Staff loggedInStaff) {
        int doctorManagementChoice = -1;

        while (true) {

            System.out.println("        =================================================");
            System.out.println("        =              Doctor Management Menu           =");
            System.out.println("        =================================================");
            System.out.println("        =         1. Display Profile                    =");
            System.out.println("        =         2. Edit Profile                       =");
            System.out.println("        =         3. Archive Account                    =");
            System.out.println("        =         4. Display Duty Schedule              =");
            System.out.println("        =         5. Leave Application                  =");
            System.out.println("        =         6. Display Doctors                    =");
            System.out.println("        =         7. Performance Summary Report         =");
            System.out.println("        =         8. Experience Level Report            =");
            System.out.println("        =         9. Undo Operation                     =");
            System.out.println("        =         10.Logout                             =");
            System.out.println("        =================================================");
            System.out.print("\n\tEnter yout choice > ");
            doctorManagementChoice = scanner.nextInt();
            scanner.nextLine();
            return doctorManagementChoice;
        }
    }

    public void displayDoctorsHeader() {
        System.out.println("\t--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\t| %-15s | %-20s | %-10s | %-15s | %-25s | %-12s | %-20s | %-25s | %-12s |\n",
                "Staff ID", "Name", "Position", "Contact", "Email", "Duty Status", "Service Duration", "Education Level", "Joined Date");
        System.out.println("\t--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayDoctorsUI(Staff staff, String level) {
        System.out.println(staff.allDoctorToString(level));

    }

    public void displayDoctorsFooter() {
        System.out.println("\t--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayProfileUI(Staff staff, String level) {
        System.out.print(staff.customizedToString(level));
    }

    public String promptStaffID() {
        System.out.print("\tEnter Staff ID: ");
        String staffId = scanner.nextLine().trim();
        return staffId;
    }

    public String promptPassword() {
        System.out.print("\tEnter Password: ");
        String password = scanner.nextLine().trim();
        return password;
    }

    public String promptStaffName() {
        System.out.print("\tEnter Staff Name: ");
        String staffName = scanner.nextLine().trim();
        return staffName;
    }

    public String promptStaffStatus() {
        System.out.print("\tEnter Duty Status (Work/Leave): ");
        String status = scanner.nextLine().trim();
        return status;
    }

    public String promptEditMsg(String field) {
        System.out.print("\tEnter new " + field + ":");
        String fieldType = scanner.nextLine().trim();
        return fieldType;
    }

    public void promptLeavSuccess(LocalDate leaveDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("\tCongratulations. Your leave application success recorded for " + leaveDate.format(formatter));
    }

    public int leaveApplicationUI() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("        =================================================");
        System.out.println("        =              Duty Schedule Option             =");
        System.out.println("        =================================================");

        // Print 7 date options 
        for (int i = 1; i <= 7; i++) {
            LocalDate optionDate = today.plusDays(i + 2);
            System.out.printf("        =   %d. %-40s =\n", i, optionDate.format(formatter));
        }

        // Add Back option as 8th option
        System.out.println("        =   8. Back                                    =");
        System.out.println("        =================================================");

        System.out.print("\n\tEnter your choice > ");
        int option = scanner.nextInt();
        return option;
    }

    public char exitConfirmationUI() {
        char choice;
        while (true) {
            System.out.print("\tDo you want to exit? (Y-Yes, N-No): ");
            String input = scanner.next().trim().toUpperCase();

            if (!input.isEmpty()) {
                choice = input.charAt(0);
                if (choice == 'Y' || choice == 'N') {
                    return choice;
                }
            }
            System.out.println("\tInvalid input! Please enter Y or N.");
        }
    }

    public int editProfileMenu() {
        int choice = 0;

        while (true) {

            System.out.println("        =================================================");
            System.out.println("        =              Profile Edit Option              =");
            System.out.println("        =================================================");
            System.out.println("        =         1. Change Password                    =");
            System.out.println("        =         2. Change Contact Number              =");
            System.out.println("        =         3. Change Email                       =");
            System.out.println("        =         4. Change Education level             =");
            System.out.println("        =         5. Back                               =");
            System.out.println("        =================================================");
            System.out.print("\n\tEnter option > ");
            choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        }
    }

    public int promptDoctorSearch() {
        int choice = 0;

        while (true) {

            System.out.println("\n\n        =================================================");
            System.out.println("        =              Search Doctor Option             =");
            System.out.println("        =================================================");
            System.out.println("        =         1. Search by Doctor ID                =");
            System.out.println("        =         2. Search by Doctor Name              =");
            System.out.println("        =         3. Search by Duty Status              =");
            System.out.println("        =         4. Back                               =");
            System.out.println("        =================================================");
            System.out.print("\n\tEnter option > ");
            choice = scanner.nextInt();
            scanner.nextLine();
            return choice;

        }
    }

    public int changeEducationalLevelMenu() {
        int level = -1;

        while (true) {

            System.out.println("        =================================================");
            System.out.println("        =              Profile Edit Option              =");
            System.out.println("        =================================================");
            System.out.println("        =         1. Bachelor of Medicine               =");
            System.out.println("        =         2. Doctor of Medicine                 =");
            System.out.println("        =         3. Doctor of Philosophy               =");
            System.out.println("        =         4. Fellowship                         =");
            System.out.println("        =         5. Back                               =");
            System.out.println("        =================================================");
            System.out.print("\n\tEnter option > ");
            level = scanner.nextInt();
            scanner.nextLine();
            return level;
        }
    }

    public boolean confirmDeleteAccountUI() {
        System.out.print("\tAre you sure you want to archive your account? (Y-Yes, N-No): ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("Y");
    }

    public boolean confirmUndoUI() {
        System.out.print("\tDo you want to undo the last delete? (Y/N): ");
        String choice = scanner.nextLine().trim().toUpperCase();
        return choice.equals("Y");
    }

    public void deleteSuccessMsg() {
        System.out.println("\tAccount deleted successfully.");
    }

    public void logOutMsg() {
        System.out.println("\tLogging out...");
    }

    public int viewDutyScheduleMenu() {

        int choice = -1;

        while (true) {

            System.out.println("        =================================================");
            System.out.println("        =              Duty Schedule Option             =");
            System.out.println("        =================================================");
            System.out.println("        =         1. 1 August - 7 August                =");
            System.out.println("        =         2. 8 August - 14 August               =");
            System.out.println("        =         3. 15 August - 21 August              =");
            System.out.println("        =         4. 22 August - 28 August              ="); //remember change back to sept 30
            System.out.println("        =         5. 29 August - 31 August              ="); //remember change back to sept 30
            System.out.println("        =         6. Back                               =");
            System.out.println("        =================================================");
            System.out.print("\n\tEnter option > ");
            choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        }
    }

    public void printExitMsg() {
        System.out.println("\tExiting to Doctor Management Menu...");
    }

    public void printInvalidInput() {
        System.out.println("\tInvalid input");
    }

    public void printWelcomeMsg(String staffName) {
        System.out.println("\n\n\tWelcome! " + staffName);
    }

    public void printUpdateSuccessMsg() {
        System.out.println("\tUpdate successfully.");
    }

    public void displayDoctorNotFound() {
        System.out.println("\n\tStaff Not Found");
    }

    public void printScheduleHeader() {
        System.out.println("\t-----------------------------------------------------------------------------------------------");
        System.out.println("\t| Day              | Doctor on Duty                                                           |");
        System.out.println("\t-----------------------------------------------------------------------------------------------");
    }

    public void printLine() {
        System.out.println("\t-----------------------------------------------------------------------");
    }

    public void printDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        System.out.printf("\t| %-16s |", date.format(formatter));

    }

    public void printDoctorOnDuty(String staffName) {
        System.out.print(String.format("\t%-20s  |", staffName));
    }

    public void printSceduleFooter() {
        System.out.println("\t-----------------------------------------------------------------------------------------------");
    }

    public int displayPerformanceReportMenu() {
        System.out.println("        =================================================");
        System.out.println("        =          Peformance Report Option             =");
        System.out.println("        =================================================");
        System.out.println("        =         1. High Consultation Period           =");
        System.out.println("        =         2. High Number of Patient Handle      =");
        System.out.println("        =================================================");
        System.out.print("\tEnter your choice > ");
        int menuOption = scanner.nextInt();
        return menuOption;
    }

    public void printExperienceReportHeader() {
        System.out.println("\t--------------------------------------------------------------------------------------------------------");
        System.out.printf("\t| %-10s | %-20s | %-13s | %-13s | %-14s | %-15s |\n",
                "Staff ID", "Name", "Clinic Years", "Industry Yrs", "Education", "Overall Score");
        System.out.println("\t--------------------------------------------------------------------------------------------------------");
    }

    public void printExperienceLine() {
        System.out.println("\t--------------------------------------------------------------------------------------------------------");
    }

    public int promptTopInput() {
        System.out.print("\tEnter top value that you want to view > ");
        int topOption = scanner.nextInt();
        return topOption;
    }

//    public void experienceReportUI(String staffId, String staffName, int clinicYrs, int industryYrs, int education, int score) {
//        System.out.printf("| %-10s | %-20s | %-13d | %-13d | %-14d | %-15d |%n",
//                staffId, staffName, clinicYrs, industryYrs, education, score);
//    }
    public void experienceReportUI(Staff staff) {
        System.out.println(staff.toString());
    }

    public void printHighDurationReportHeader() {
        System.out.println("\t-----------------------------------------------------------------------");
        System.out.printf("\t| %-20s | %-20s | %-20s  |\n", "Doctor ID", "Doctor Name", "Total Duration (min)");
        System.out.println("\t-----------------------------------------------------------------------");
    }

    public void printHighPatientsReportHeader() {
        System.out.println("\t-----------------------------------------------------------------------");
        System.out.printf("\t| %-20s | %-20s | %-20s  |\n", "Doctor ID", "Doctor Name", "Patients Handled");
        System.out.println("\t-----------------------------------------------------------------------");
    }

    public void performanceReportUI(Staff staff, String mode) {
        switch (mode.toLowerCase()) {
            case "patients":
                System.out.println(staff.patientPerformanceToString());
                break;
            case "duration":
                System.out.println(staff.consultationPerformanceToString());
                break;
        }
    }

    public void printTopDoctor() {
        System.out.println("\n\t\t\t\t\tTop 3 Most Experience Doctors\n");
    }

    public void printExperinceTitle() {
        System.out.println("\n\t\t\t\t\t\tMost Experience Doctors Overview\n");
    }

    public void printHigestConsultTime() {
        System.out.println("\n\t\t\tDoctor With Higest Consultation Time\n");
    }

    public void printHigestPatientCount() {
        System.out.println("\n\t\t\tDoctor with Higest Patient Count\n");
    }

    public void printUndoMsg(String mode) {
        switch (mode) {
            case "update":
                System.out.println("\n\tYour profile edition undo successfully");
                break;
            case "delete":
                System.out.println("\n\tYour account deletion undo successfully");
                break;
            case "leave":
                System.out.println("\n\tYour leave application undo successfully");
                break;
        }

    }

    public void displayNotificationMsg(String shift, Staff staff) {
        switch (shift) {
            case "first":
                System.out.println("\n\tHello, Dr " + staff.getStaffName() + ", you are the FIRST(Slot 1) doctor on duty today");
                break;
            case "middle":
                System.out.println("\n\tHello, Dr " + staff.getStaffName() + ", you are the MIDDLE(Slot 2) doctor on duty today");
                break;
            case "last":
                System.out.println("\n\tHello, Dr " + staff.getStaffName() + ", you are the LAST(Slot 3) doctor on duty today");
                break;

        }
    }

}
