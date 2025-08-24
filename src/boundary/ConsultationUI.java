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

        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t=              Consultation Menu                =");
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t=         1. View Appointment                   =");
        System.out.println("\t\t\t\t=         2. search Appointment                 =");
        System.out.println("\t\t\t\t=         3. Add Appointment                    =");
        System.out.println("\t\t\t\t=         4. Update Appoinment                  =");
        System.out.println("\t\t\t\t=         5. Delete Appoinment                  =");
        System.out.println("\t\t\t\t=         6. View Consultation schedule         =");
        System.out.println("\t\t\t\t=         7. view Consultation Flw Up Report    =");
        System.out.println("\t\t\t\t=         8. view Consultation Valume Report    =");
        System.out.println("\t\t\t\t=         9. Undo                               =");
        System.out.println("\t\t\t\t=        10. Exit                               =");
        System.out.println("\t\t\t\t=================================================");

        choice = Input.getIntegerInput("\t\t\t\tEnter your choice > ");
        return choice;
    }

    public int addAppointmentMenu() {

        int choice = 0;

        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t=              Add Appointment Menu             =");
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t=         1. Online                             =");
        System.out.println("\t\t\t\t=         2. Walk In                            =");
        System.out.println("\t\t\t\t=         3. back                               =");
        System.out.println("\t\t\t\t=================================================");

        choice = Input.getIntegerInput("\t\t\t\tEnter your choice > ");
        return choice;
    }

    public int searchAppointmentMenu() {

        int choice = 0;

        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t=              Search Appointment Menu          =");
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t=         1. Consultation ID                    =");
        System.out.println("\t\t\t\t=         2. Patient ID                         =");
        System.out.println("\t\t\t\t=         3. Booking Date                       =");
        System.out.println("\t\t\t\t=         4. back                               =");
        System.out.println("\t\t\t\t=================================================");

        choice = Input.getIntegerInput("\t\t\t\tEnter your choice > ");
        return choice;
    }

    public int consultationFlwUpMenu() {
        int choice = 0;
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t=              Report Options                   =");
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t=         1. Year                               =");
        System.out.println("\t\t\t\t=         2. Month                              =");
        System.out.println("\t\t\t\t=         3. Day                                =");
        System.out.println("\t\t\t\t=         4. back                               =");
        System.out.println("\t\t\t\t=================================================");

        choice = Input.getIntegerInput("\t\t\t\tEnter your choice > ");
        return choice;
    }

    public int editAppoinmentOptionMenu() {
        int choice = 0;
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t|              Update Options                   |");
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t|         1. Consultation Date                  |");
        System.out.println("\t\t\t\t|         2. Consultation Start Time            |");
        System.out.println("\t\t\t\t|         3. Consultation End Time              |");
        System.out.println("\t\t\t\t|         4. Staff ID                           |");
        System.out.println("\t\t\t\t|         5. back                               |");
        System.out.println("\t\t\t\t=================================================");

        choice = Input.getIntegerInput("\t\t\t\tEnter your choice > ");
        return choice;

    }

    public void displayExitMsg() {
        System.out.println("\n\t\t\t\tExiting Consultation Module.\n");
    }

    public void displayBackMsg() {
        System.out.println("\n\t\t\t\tBack Consultation Menu.\n");
    }

    public void displayCurrentUserMsg() {
        System.out.println("\t\t\t\tNo user is currently logged in.");
    }

    public void displaySuccessfullyMsg(String id, String type) {
        System.out.println("\t\t\t\tConsultation with ID " + id + type + " successfully.");
    }

    public void displayFailedMsg(String type) {
        System.out.println("\t\t\t\t" + type + "Failed.");
    }

    public void displayConsultationNotFound() {
        System.out.printf("\t\t\t\t%s%s%s\n", RED, "No Consultation data found.", RESET);
    }

    public void displayConsultationNotFound(String consultationId) {
        System.out.printf("\t\t\t\t%s%s%s%s\n", RED, "No consultation found with ID: ", consultationId, RESET);
    }

    public void displayConsultationToString(Consultation c) {
        int tableWidth = 122; // total width including borders
        String border = "-".repeat(tableWidth);

        String consultationTime = String.format("%s-%s",
                timeFormat.format(c.getConsultation_start_time()),
                timeFormat.format(c.getConsultation_end_time())
        );

        System.out.printf("\t\t\t\t| %-14s |  %-15s | %-17s | %-17s | %-10s | %-7s | %7s | %7s   | \n\t\t\t\t%s\n", c.getConsultation_Id(), dateFormat.format(c.getAppointment_date()), dateFormat.format(c.getConsultation_date()), consultationTime, c.getAppointmentStatus(), c.getType(), c.getStaff_Id(), c.getPatient_Id(), border);
    }

    public void displayConsultationToString1(Consultation c) {
        int tableWidth = 112; // total width including borders
        String border = "-".repeat(tableWidth);

        String consultationTime = String.format("%s-%s",
                timeFormat.format(c.getConsultation_start_time()),
                timeFormat.format(c.getConsultation_end_time())
        );

        System.out.printf("\t\t\t\t| %-14s |  %-15s | %-17s | %-17s | %-10s | %-7s | %8s |\n\t\t\t\t%s\n", c.getConsultation_Id(), dateFormat.format(c.getAppointment_date()), dateFormat.format(c.getConsultation_date()), consultationTime, c.getAppointmentStatus(), c.getType(), c.getPatient_Id(), border);
    }

    public void displayConsultationToString2(Consultation c) {
        int tableWidth = 110; // total width including borders
        String border = "-".repeat(tableWidth);

        String consultationTime = String.format("%s-%s",
                timeFormat.format(c.getConsultation_start_time()),
                timeFormat.format(c.getConsultation_end_time())
        );

        System.out.printf("\t\t\t\t| %-14s |  %-15s | %-17s | %-17s | %-10s | %-7s | %7s | \n\t\t\t\t%s\n", c.getConsultation_Id(), dateFormat.format(c.getAppointment_date()), dateFormat.format(c.getConsultation_date()), consultationTime, c.getAppointmentStatus(), c.getType(), c.getStaff_Id(), border);
    }

    public void displayConsultationOperationMsg(String id, String type1, String type2) {
        System.out.println("\t\t\t\t" + type1 + " Appointment " + type2 + ": " + id);
    }

    public void displayConsultationWalkInNumber(String num) {
        System.out.println("\t\t\t\tThis is your Walk-in number: " + num);

    }

    public void displayNextConsultationDate(Date newConsultationDate, Date newConsultStartTime, Date newConsultEndTime) {
        System.err.println("\t\t\t\tNext Consultation Date is " + dateFormat.format(newConsultationDate) + " Time is " + timeFormat.format(newConsultStartTime) + "-" + timeFormat.format(newConsultEndTime));
    }

    public void displayConsultationSchedulefield(int day, int month, int year, Calendar c) {
        System.out.printf("\n\t\t\t\tDate: %02d-%02d-%04d (%s)\n", day, month, year, c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
    }

    public void displayConsultationScheduleStaff(String staffID, Staff staffData) {
        System.out.printf("\n\t\t\t\t%s %-6s %s %-12s %s", "|", staffID, "|", staffData.getStaffName(), "|");
    }

    public void displayConsultationScheduleX(boolean booked) {
        System.out.printf("%-6s %s", booked ? "   X" : "", "|");
    }

    public void displayCatchError(Exception e) {
        System.out.println(e);
    }

    public void displayFilterConsultation(String type, String currentDate) {
        System.out.println("\n\t\t\t\tConsultation for current" + type + " (" + currentDate + "):");
    }

    public void displayTotalVolume(int count) {
        System.out.println("\t\t\t\tTotal Consultation Volume is: " + count);
    }

    public void displayNotFoundCurrentDate(String currentDate) {
        System.out.println("\t\t\t\tNo Consultation found for " + currentDate);
    }

    public void displayInvalidOptionMsg() {
        System.out.println("\t\t\t\tInvalid choice, please try again.\n");
    }

    public void displayStaffLeave() {
        System.out.println("\t\t\t\tToday Staff have been leave. Please choice other staff\n");
    }

    public void displayLine() {
        System.out.print("\n\t\t\t\t----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // UI
    public void staffViewAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);
        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                                                 Today Appoinment                                           |");
        System.out.println("\t\t\t\t" + border);
    }

    public void staffViewAppoinmentUI1() {
        int tableWidth = 112; // total width including borders
        String border = "=".repeat(tableWidth);
        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                                                 Today Appoinment                                             |");
        System.out.println("\t\t\t\t" + border);
    }

    public void patientViewAppoinmentUI() {
        int tableWidth = 51; // total width including borders
        String border = "=".repeat(tableWidth);
        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|               Appoinment Details                |");
        System.out.println("\t\t\t\t" + border);
    }

    public void staffReportAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                                                 All Appoinment                                             |");
        System.out.println("\t\t\t\t" + border);
    }

    public void staffReportYearAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                                                Year Appoinment                                             |");
        System.out.println("\t\t\t\t" + border);
    }

    public void staffReportMonthAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                                               Month Appoinment                                             |");
        System.out.println("\t\t\t\t" + border);
    }

    public void staffReportDayAppoinmentUI() {
        int tableWidth = 110; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                                                Day Appoinment                                              |");
        System.out.println("\t\t\t\t" + border);
    }

    public void staffReportAppoinmentFlwUI() {
        int tableWidth = 57; // total width including borders
        String border = "=".repeat(tableWidth);
        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                  All Follow Up Report                 |");
        System.out.println("\t\t\t\t" + border);
    }

    public void staffSearchAppointmentUI() {
        int tableWidth = 122; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                                                    Search Appoinment                                                   |");
        System.out.println("\t\t\t\t" + border);
    }

    public void editAppoinmentUI() {
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t|              Edit Appoinment                  |");
        System.out.println("\t\t\t\t=================================================");
    }

    public void deleteAppoinmentUI() {
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t|              Delete Appoinment                |");
        System.out.println("\t\t\t\t=================================================");
    }

    public void consultationScheduleUI() {
        int tableWidth = 178; // total width including borders
        String border = "=".repeat(tableWidth);
        System.out.println("\t\t\t\t" + border);
        System.out.println("\t\t\t\t|                                                                      Consultation Schedule (Next 3 Days)                                                                       |");
        System.out.println("\t\t\t\t" + border);
    }

    public void appointmentFieldUI() {
        int tableWidth = 110; // total width including borders
        String border = "-".repeat(tableWidth);

//        System.out.println("\t\t\t\t" + border);
        System.out.printf("\t\t\t\t| %-14s | %-15s | %-17s | %-17s | %-10s | %-7s | %-7s |\n", "ConsultationID", "Appointment Date", "Consultation Date", "Consultation Time", "Status", "Type", "StaffID");
        System.out.println("\t\t\t\t" + border);
    }

    public void appointmentFieldUI2() {
        int tableWidth = 122; // total width including borders
        String border = "-".repeat(tableWidth);

        System.out.println("\t\t\t\t" + border);
        System.out.printf("\t\t\t\t| %-14s | %-15s | %-17s | %-17s | %-10s | %-7s | %-7s | %-7s |\n", "ConsultationID", "Appointment Date", "Consultation Date", "Consultation Time", "Status", "Type", "StaffID", "PatientID");
        System.out.println("\t\t\t\t" + border);
    }

    public void appointmentFieldUI3() {
        int tableWidth = 57; // total width including borders
        String border = "-".repeat(tableWidth);

        System.out.println("\t\t\t\t" + border);
        System.out.printf("\t\t\t\t| %-17s | %-10s | %-20s |\n", "Consultation Date", "Staff ID", "Number of Follow-up");
        System.out.println("\t\t\t\t" + border);
    }

    public void appointmentFieldUI4() {
        int tableWidth = 112; // total width including borders
        String border = "-".repeat(tableWidth);

//        System.out.println("\t\t\t\t" + border);
        System.out.printf("\t\t\t\t| %-14s | %-15s | %-17s | %-17s | %-10s | %-7s | %-7s |\n", "ConsultationID", "Appointment Date", "Consultation Date", "Consultation Time", "Status", "Type", "PatientID");
        System.out.println("\t\t\t\t" + border);
    }

    public void consultationScheduleUI(MapInterface<String, String> timeSlotMap) {
        int tableWidth = 178; // total width including borders
        String border = "-".repeat(tableWidth);
        System.out.println("\t\t\t\t" + border);

        System.out.printf("\t\t\t\t%-21s", "| Staff ID / Staff Name  |");
        Object[] timeSlot = timeSlotMap.getAllKeys();
        for (Object slot : timeSlot) {
            System.out.printf("%6s %s", slot, "|");
        }
        System.out.print("\n\t\t\t\t" + border);
    }

    public void displayFlwUpReport(String date, String staffID, int count) {
        int tableWidth = 57; // total width including borders
        String border = "-".repeat(tableWidth);
        System.out.printf("\t\t\t\t| %-17s | %-10s |           %-10d |\n", date, staffID, count);
        System.out.println("\t\t\t\t" + border);
    }

    public void displayAppoinment(Consultation consultation) {
        int tableWidth = 51; // total width including borders
        String border = "=".repeat(tableWidth);

        System.out.println("\t\t\t\t" + border);
        System.out.printf("\t\t\t\t| %-25s | %-19s |\n", "Consultation ID", consultation.getConsultation_Id());
        System.out.printf("\t\t\t\t| %-25s | %-19s |\n", "Appointment Date", dateFormat.format(consultation.getAppointment_date()));
        System.out.printf("\t\t\t\t| %-25s | %-19s |\n", "Consultation Date", dateFormat.format(consultation.getConsultation_date()));
        System.out.printf("\t\t\t\t| %-25s | %-19s |\n", "Consultation Start Time", timeFormat.format(consultation.getConsultation_start_time()));
        System.out.printf("\t\t\t\t| %-25s | %-19s |\n", "Consultation End Time", timeFormat.format(consultation.getConsultation_end_time()));
        System.out.printf("\t\t\t\t| %-25s | %-19s |\n", "Status", consultation.getAppointmentStatus());
        System.out.printf("\t\t\t\t| %-25s | %-19s |\n", "Type", consultation.getType());
        System.out.printf("\t\t\t\t| %-25s | %-19s |\n", "Staff ID", consultation.getStaff_Id());
        System.out.println("\t\t\t\t" + border);
    }

    public void displayspace() {
        System.out.println("");
    }

    // promptUI
    public String promptConsultationID() {
        boolean valid = false;
        String consultationID = "";
        while (!valid) {
            consultationID = Input.getStringInput("\t\t\t\tEnter the Consultation ID: ");
            if (Master.getConsultationMap().containsKey(consultationID)) {
                valid = true;
            } else {
                System.out.println("\t\t\t\tInvalid Consultation Id. Please try agian.");
            }
        }
        return consultationID;
    }

    public Date promptConsultationDate() {
        Date consultatiDate = null;
        boolean valid = false;
        while (!valid) {
            String newConsultationDate = Input.getStringInput("\t\t\t\tEnter your Booking Date (dd-MM-yyyy):");
            try {
                consultatiDate = dateFormat.parse(newConsultationDate);

                long mills = consultatiDate.getTime() - new Date().getTime();
                long days = mills / (1000 * 60 * 60 * 24);
                if (days >= 0 && days <= 2) {
                    valid = true;
                } else {
                    System.out.println("\t\t\t\tBooking date must be after 3 days from today.");
                }

            } catch (ParseException e) {
                System.out.println("\t\t\t\tInvalid date format. Please use dd-MM-yyyy.");
            }
        }
        return consultatiDate;
    }

    public Date promptConsultationStartTime() {
        Date consultationStartTime = null;
        boolean valid = false;
        while (!valid) {
            String newConsultStartTime = Input.getStringInput("\t\t\t\tEnter your Booking Start Time (HH:mm):");
            try {
                consultationStartTime = timeFormat.parse(newConsultStartTime);

                Calendar c = Calendar.getInstance();
                c.setTime(consultationStartTime);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                if (hour >= 9 || hour <= 18) {
                    valid = true;
                } else {
                    System.out.println("\t\t\t\tStart time must be between 9:00 AM and 6:00 PM.");
                }

            } catch (ParseException ex) {
                System.out.println("\t\t\t\tInvalid Time format. Please use HH:mm.");
            }
        }

        return consultationStartTime;
    }

    public Date promptConsultationEndTime() {
        Date consultationEndTime = null;
        boolean valid = false;
        while (!valid) {
            String newConsultEndTime = Input.getStringInput("\t\t\t\tEnter your Booking End Time (HH:mm):");
            try {
                consultationEndTime = timeFormat.parse(newConsultEndTime);

                Calendar c = Calendar.getInstance();
                c.setTime(consultationEndTime);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                if (hour >= 9 || hour <= 18) {
                    valid = true;
                } else {
                    System.out.println("\t\t\t\tEnd time must be between 9:00 AM and 6:00 PM");
                }
            } catch (ParseException ex) {
                System.out.println("\t\t\t\tInvalid Time format. Please use HH:mm.");
            }
        }

        return consultationEndTime;
    }

    public String promptPatientID() {

        boolean valid = false;
        String patientID = "";
        while (!valid) {
            patientID = Input.getStringInput("\t\t\t\tEnter Patient Id:");
            if (Master.getPatientMap().containsKey(patientID)) {
                valid = true;
            } else {
                System.out.println("\t\t\t\tInvalid Patient ID. Please try again.");
            }
        }
        return patientID;
    }

    public String promptStaffID() {
        boolean valid = false;
        String staffID = "";
        while (!valid) {
            staffID = Input.getStringInput("\t\t\t\tEnter Staff Id:");
            if (Master.getStaffMap().containsKey(staffID)) {
                valid = true;
            } else {
                System.out.println("\t\t\t\tInvalid Staff ID. Please try again.");
            }
        }
        return staffID;
    }

    public Boolean promptDeteleMsg() {
        boolean confirm = Input.getBooleanInput("\t\t\t\tAre you sure you want to delete this consultation? ");
        return confirm;
    }

}
