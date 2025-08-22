/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.MapInterface;
import dao.Master;
import entity.Consultation;
import entity.Staff;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import utility.Input;
import static utility.MessageUI.RED;
import static utility.MessageUI.RESET;

/**
 *
 * @author Tan Kok Hong
 */
public class ConsultationUI {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    //Menu
    public int consultationMenu() {

        int choice;

        System.out.println("\t\t =================================================");
        System.out.println("\t\t =              Consultation Menu                =");
        System.out.println("\t\t =================================================");
        System.out.println("\t\t =         1. View Appointment                   =");
        System.out.println("\t\t =         2. search Appointment                 =");
        System.out.println("\t\t =         3. Add Appointment                    =");
        System.out.println("\t\t =         4. Update Appoinment                  =");
        System.out.println("\t\t =         5. Delete Appoinment                  =");
        System.out.println("\t\t =         6. View Consultation schedule         =");
        System.out.println("\t\t =         7. view Consultation Flw Up Report    =");
        System.out.println("\t\t =         8. view Consultation Valume Report    =");
        System.out.println("\t\t =         9. Undo                               =");
        System.out.println("\t\t =        10. Exit                               =");
        System.out.println("\t\t =================================================");

        choice = Input.getIntegerInput("\t\t Enter your choice > ");
        return choice;
    }

    public int addAppointmentMenu() {

        int choice = 0;

        System.out.println("\t\t =================================================");
        System.out.println("\t\t =              Add Appointment Menu             =");
        System.out.println("\t\t =================================================");
        System.out.println("\t\t =         1. Online                             =");
        System.out.println("\t\t =         2. Walk In                            =");
        System.out.println("\t\t =         3. back                               =");
        System.out.println("\t\t =================================================");

        choice = Input.getIntegerInput("\t\t Enter your choice > ");
        return choice;
    }

    public int searchAppointmentMenu() {

        int choice = 0;

        System.out.println("\t\t =================================================");
        System.out.println("\t\t =              Add Appointment Menu             =");
        System.out.println("\t\t =================================================");
        System.out.println("\t\t =         1. Consultation ID                    =");
        System.out.println("\t\t =         2. Patient ID                         =");
        System.out.println("\t\t =         3. Booking Date                       =");
        System.out.println("\t\t =         4. back                               =");
        System.out.println("\t\t =================================================");

        choice = Input.getIntegerInput("\t\t Enter your choice > ");
        return choice;
    }

    public int consultationFlwUpMenu() {
        int choice = 0;
        System.out.println("\t\t =================================================");
        System.out.println("\t\t =              Report Options                   =");
        System.out.println("\t\t =================================================");
        System.out.println("\t\t =         1. Year                               =");
        System.out.println("\t\t =         2. Month                              =");
        System.out.println("\t\t =         3. Day                                =");
        System.out.println("\t\t =         4. back                               =");
        System.out.println("\t\t =================================================");

        choice = Input.getIntegerInput("\t\t Enter your choice > ");
        return choice;
    }

    public int editAppoinmentOptionMenu() {
        int choice = 0;
        System.out.println("\t\t =================================================");
        System.out.println("\t\t |              Update Options                   |");
        System.out.println("\t\t =================================================");
        System.out.println("\t\t |         1. Consultation Date                  |");
        System.out.println("\t\t |         2. Consultation Start Time            |");
        System.out.println("\t\t |         3. Consultation End Time              |");
        System.out.println("\t\t |         4. Staff ID                           |");
        System.out.println("\t\t |         5. back                               |");
        System.out.println("\t\t =================================================");

        choice = Input.getIntegerInput("\t\t Enter your choice > ");
        return choice;

    }

    public void displayExitMsg() {
        System.out.println("\n\t\tExiting Consultation Module.\n");
    }

    public void displayBackMsg() {
        System.out.println("\n\t\tBack Consultation Menu.\n");
    }

    public void displayCurrentUserMsg() {
        System.out.println("\t\t No user is currently logged in.");
    }

    public void displaySuccessfullyMsg(String id, String type) {
        System.out.println("\t\t Consultation with ID " + id + type + " successfully.");
    }

    public void displayFailedMsg(String type) {
        System.out.println("\t\t " + type + "Failed.");
    }

    public void displayConsultationNotFound() {
        System.out.printf("\t\t%s%s%s\n", RED, "No Consultation data found.", RESET);
    }

    public void displayConsultationNotFound(String consultationId) {
        System.out.printf("\t\t%s%s%s%s\n", RED, "No consultation found with ID: ", consultationId, RESET);
    }

    public void displayConsultationToString(Consultation consult) {
        System.out.println(consult.toStaffString());
    }

    public void displayConsultationOperationMsg(String id, String type1, String type2) {
        System.out.println("\t\t " + type1 + " Appointment " + type2 + ": " + id);
    }

    public void displayConsultationWalkInNumber(String num) {
        System.out.println("\t\t This is your Walk-in number: " + num);

    }

    public void displayNextConsultationDate(Date newConsultationDate, Date newConsultStartTime, Date newConsultEndTime) {
        System.err.println("\t\t Next Consultation Date is " + dateFormat.format(newConsultationDate) + " Time is " + timeFormat.format(newConsultStartTime) + "-" + timeFormat.format(newConsultEndTime));
    }

    public void displayConsultationSchedulefield(int day, int month, int year, Calendar c) {
        System.out.printf("\n\t\t Date: %02d-%02d-%04d (%s)\n", day, month, year, c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
    }

    public void displayConsultationScheduleStaff(String staffID, Staff staffData) {
        System.out.printf("\n\t\t %s %-6s %s %-12s %s", "|", staffID, "|", staffData.getStaffName(), "|");
    }

    public void displayConsultationScheduleX(boolean booked) {
        System.out.printf("%-6s %s", booked ? "   X" : "", "|");
    }

    public void displayCatchError(Exception e) {
        System.out.println(e);
    }

    public void displayFilterConsultation(String type, String currentDate) {
        System.out.println("\n\t\t Consultation for current" + type + " (" + currentDate + "):");
    }

    public void displayTotalVolume(int count) {
        System.out.println("\t\t Total Consultation Volume is: " + count);
    }

    public void displayNotFoundCurrentDate(String currentDate) {
        System.out.println("\t\t No Consultation found for " + currentDate);
    }

    public void displayInvalidOptionMsg() {
        System.out.println("\t\tInvalid choice, please try again.\n");
    }

    public void displayStaffLeave() {
        System.out.println("\t\t Today Staff have been leave. Please choice other staff\n");
    }

    public void displayLine() {
        System.out.print("\n\t\t ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // UI
    public void staffViewAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);
        System.out.println("\t\t " + border);
        System.out.println("\t\t |                                                 Today Appoinment                                           |");
        System.out.println("\t\t " + border);
    }

    public void patientViewAppoinmentUI() {
        int tableWidth = 51; // total width including borders
        String border = "=".repeat(tableWidth);
        System.out.println("\t\t " + border);
        System.out.println("\t\t |               Appoinment Details                |");
        System.out.println("\t\t " + border);
    }

    public void staffReportAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t " + border);
        System.out.println("\t\t |                                                 All Appoinment                                             |");
        System.out.println("\t\t " + border);
    }

    public void staffReportYearAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t " + border);
        System.out.println("\t\t |                                                Year Appoinment                                             |");
        System.out.println("\t\t " + border);
    }

    public void staffReportMonthAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t " + border);
        System.out.println("\t\t |                                               Month Appoinment                                             |");
        System.out.println("\t\t " + border);
    }

    public void staffReportDayAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t " + border);
        System.out.println("\t\t |                                                Day Appoinment                                              |");
        System.out.println("\t\t " + border);
    }

    public void staffReportAppoinmentFlwUI() {
        System.out.println("\t\t =================================================");
        System.out.println("\t\t |              All Follow Up Report             |");
        System.out.println("\t\t =================================================");
    }

    public void staffSearchAppointmentUI() {
        System.out.println("\t\t =================================================");
        System.out.println("\t\t |              Search Appoinment                |");
        System.out.println("\t\t =================================================");
    }

    public void editAppoinmentUI() {
        System.out.println("\t\t =================================================");
        System.out.println("\t\t |              Edit Appoinment                  |");
        System.out.println("\t\t =================================================");
    }

    public void deleteAppoinmentUI() {
        System.out.println("\t\t =================================================");
        System.out.println("\t\t |              Delete Appoinment                |");
        System.out.println("\t\t =================================================");
    }

    public void consultationScheduleUI() {
        int tableWidth = 178; // total width including borders
        String border = "=".repeat(tableWidth);
        System.out.println("\t\t " + border);
        System.out.println("\t\t |                                                                      Consultation Schedule (Next 3 Days)                                                                       |");
        System.out.println("\t\t " + border);
    }

    public void appointmentFieldUI() {
        int tableWidth = 110; // total width including borders
        String border = "-".repeat(tableWidth);

//        System.out.println("\t\t " + border);
        System.out.printf("\t\t | %-14s | %-15s | %-17s | %-17s | %-10s | %-7s | %-7s |\n", "ConsultationID", "Appointment Date", "Consultation Date", "Consultation Time", "Status", "Type", "StaffID");
        System.out.println("\t\t " + border);
    }

    public void appointmentFieldUI2() {
        int tableWidth = 122; // total width including borders
        String border = "-".repeat(tableWidth);

        System.out.println("\t\t " + border);
        System.out.printf("\t\t | %-14s | %-15s | %-17s | %-17s | %-10s | %-7s | %-7s | %-7s |\n", "ConsultationID", "Appointment Date", "Consultation Date", "Consultation Time", "Status", "Type", "StaffID", "PatientID");
        System.out.println("\t\t " + border);
    }

    public void appointmentFieldUI3() {
        int tableWidth = 57; // total width including borders
        String border = "-".repeat(tableWidth);

        System.out.println("\t\t " + border);
        System.out.printf("\t\t | %-17s | %-10s | %-20s |\n", "Consultation Date", "Staff ID", "Number of Follow-up");
        System.out.println("\t\t " + border);
    }

    public void consultationScheduleUI(MapInterface<String, String> timeSlotMap) {
        int tableWidth = 178; // total width including borders
        String border = "-".repeat(tableWidth);
        System.out.println("\t\t " + border);

        System.out.printf("\t\t %-21s", "| Staff ID / Staff Name  |");
        Object[] timeSlot = timeSlotMap.getAllKeys();
        for (Object slot : timeSlot) {
            System.out.printf("%6s %s", slot, "|");
        }
        System.out.print("\n\t\t " + border);
    }

    public void displayFlwUpReport(String date, String staffID, int count) {
        int tableWidth = 57; // total width including borders
        String border = "-".repeat(tableWidth);
        System.out.printf("\t\t | %-17s | %-10s | %-20d |\n", date, staffID, count);
        System.out.println("\t\t " + border);
    }

    public void displayAppoinment(Consultation consultation) {
        int tableWidth = 51; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t " + border);
        System.out.printf("\t\t | %-25s | %-19s |\n", "Consultation ID", consultation.getConsultation_Id());
        System.out.printf("\t\t | %-25s | %-19s |\n", "Appointment Date", dateFormat.format(consultation.getAppointment_date()));
        System.out.printf("\t\t | %-25s | %-19s |\n", "Consultation Date", dateFormat.format(consultation.getConsultation_date()));
        System.out.printf("\t\t | %-25s | %-19s |\n", "Consultation Start Time", timeFormat.format(consultation.getConsultation_start_time()));
        System.out.printf("\t\t | %-25s | %-19s |\n", "Consultation End Time", timeFormat.format(consultation.getConsultation_end_time()));
        System.out.printf("\t\t | %-25s | %-19s |\n", "Status", consultation.getAppointmentStatus());
        System.out.printf("\t\t | %-25s | %-19s |\n", "Type", consultation.getType());
        System.out.printf("\t\t | %-25s | %-19s |\n", "Staff ID", consultation.getStaff_Id());
        System.out.println("\t\t " + border);
    }
    
    public void displayspace(){
        System.out.println("");
    }

    // promptUI
    public String promptConsultationID() {
        boolean valid = false;
        String consultationID = "";
        while (!valid) {
            consultationID = Input.getStringInput("\t\t Enter the Consultation ID: ");
            if (Master.getConsultationMap().containsKey(consultationID)) {
                valid = true;
            } else {
                System.out.println("\t\t Invalid Consultation Id. Please try agian.");
            }
        }
        return consultationID;
    }

    public Date promptConsultationDate() {
        Date consultatiDate = null;
        boolean valid = false;
        while (!valid) {
            String newConsultationDate = Input.getStringInput("\t\t Enter your Booking Date (dd-MM-yyyy):");
            try {
                consultatiDate = dateFormat.parse(newConsultationDate);

                long mills = consultatiDate.getTime() - new Date().getTime();
                long days = mills / (1000 * 60 * 60 * 24);
                if (days >= 0 && days <= 2) {
                    valid = true;
                } else {
                    System.out.println("\t\t Booking date must be after 3 days from today.");
                }

            } catch (ParseException e) {
                System.out.println("\t\t Invalid date format. Please use dd-MM-yyyy.");
            }
        }
        return consultatiDate;
    }

    public Date promptConsultationStartTime() {
        Date consultationStartTime = null;
        boolean valid = false;
        while (!valid) {
            String newConsultStartTime = Input.getStringInput("\t\t Enter your Booking Start Time (HH:mm):");
            try {
                consultationStartTime = timeFormat.parse(newConsultStartTime);

                Calendar c = Calendar.getInstance();
                c.setTime(consultationStartTime);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                if (hour >= 9 || hour <= 18) {
                    valid = true;
                } else {
                    System.out.println("\t\t Start time must be between 9:00 AM and 6:00 PM.");
                }

            } catch (ParseException ex) {
                System.out.println("\t\t Invalid Time format. Please use HH:mm.");
            }
        }

        return consultationStartTime;
    }

    public Date promptConsultationEndTime() {
        Date consultationEndTime = null;
        boolean valid = false;
        while (!valid) {
            String newConsultEndTime = Input.getStringInput("\t\t Enter your Booking Start Time (HH:mm):");
            try {
                consultationEndTime = timeFormat.parse(newConsultEndTime);

                Calendar c = Calendar.getInstance();
                c.setTime(consultationEndTime);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                if (hour >= 9 || hour <= 18) {
                    valid = true;
                } else {
                    System.out.println("\t\t End time must be between 9:00 AM and 6:00 PM");
                }
            } catch (ParseException ex) {
                System.out.println("\t\t Invalid Time format. Please use HH:mm.");
            }
        }

        return consultationEndTime;
    }

    public String promptPatientID() {

        boolean valid = false;
        String patientID = "";
        while (!valid) {
            patientID = Input.getStringInput("\t\t Enter Patient Id:");
            if (Master.getPatientMap().containsKey(patientID)) {
                valid = true;
            } else {
                System.out.println("\t\t Invalid Patient ID. Please try again.");
            }
        }
        return patientID;
    }

    public String promptStaffID() {
        boolean valid = false;
        String staffID = "";
        while (!valid) {
            staffID = Input.getStringInput("\t\t Enter Staff Id:");
            if (Master.getStaffMap().containsKey(staffID)) {
                valid = true;
            } else {
                System.out.println("\t\t Invalid Staff ID. Please try again.");
            }
        }
        return staffID;
    }

    public Boolean promptDeteleMsg() {
        boolean confirm = Input.getBooleanInput("\t\t Are you sure you want to delete this consultation? ");
        return confirm;
    }

}
