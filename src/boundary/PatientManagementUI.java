/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.MapInterface;
import entity.Patient;
import entity.Visit;
import utility.Input;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientManagementUI {

    public static void displayCreateHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              NEW PATIENT REGISTRATION               ");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayCreateSuccess(String id) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                   SUCCESS                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|         Patient %s registered.%-14s |\n", id, "");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayNotLoggedIn() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    WARNING                        |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|         No patient is currently logged in.       |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static int displayMultipleRecordsMenu(String[] visitDates) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t            MULTIPLE PATIENT RECORDS                ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|    This patient has multiple visit records.       |");
        System.out.println("\t\t\t\t|         Please select a record to view:           |");
        System.out.println("\t\t\t\t|                                                   |");
        for (int i = 0; i < visitDates.length; i++) {
            System.out.printf("\t\t\t\t|  %d. Visit Date: %-31s   |\n", i + 1, visitDates[i]);
        }
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect a record (1-" + visitDates.length + "): ");
    }

    public static void displayProfile(Patient patient, Visit visit) {
        System.out.println("\n\t\t\t\t========================================================");
        System.out.println("\t\t\t\t               Patient Details                          ");
        System.out.println("\t\t\t\t========================================================");
        System.out.printf("\t\t\t\t| Patient ID      : %-33s |\n", patient.getPatient_id());
        System.out.printf("\t\t\t\t| Name            : %-33s |\n", patient.getPatient_name());
        System.out.printf("\t\t\t\t| Contact         : %-33s |\n", patient.getPatient_contact());
        System.out.printf("\t\t\t\t| Email           : %-33s |\n", patient.getPatient_email());
        System.out.printf("\t\t\t\t| Gender          : %-33s |\n", patient.getPatient_gender());
        System.out.printf("\t\t\t\t| Age             : %-33s |\n", patient.getAge() + " years old");
        SimpleDateFormat df = patient.getDateFormat();
        String reg = patient.getRegistration_date() == null ? "N/A" : df.format(patient.getRegistration_date());
        System.out.printf("\t\t\t\t| Registration    : %-33s |\n", reg);
        String qs = (visit == null || visit.getQueueStart() == null) ? "N/A" : df.format(visit.getQueueStart());
        String qe = (visit == null || visit.getQueueEnd() == null) ? "N/A" : df.format(visit.getQueueEnd());
        System.out.printf("\t\t\t\t| Queue Start     : %-33s |\n", qs);
        System.out.printf("\t\t\t\t| Queue End       : %-33s |\n", qe);
        System.out.println("\t\t\t\t========================================================");
    }

    public static void displayUpdateMenu(String[] options, String[] values) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                EDIT PATIENT                         ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        for (int i = 0; i < 3; i++) {
            System.out.printf("\t\t\t\t|  %d. %-15s: %-27s  |\n", i + 1, options[i], values[i]);
        }
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  4. Save Changes                                  |");
        System.out.println("\t\t\t\t|  5. Cancel                                        |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayUpdateSuccess() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                   SUCCESS                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|            Patient updated successfully.          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayDeleteWarning() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                DELETE ACCOUNT                       ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|               WARNING                             |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|         This action cannot be undone!             |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayDeleteSuccess() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                   SUCCESS                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|            Patient account deleted.               |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayExistingTicket(String ticketNumber) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|      Patient already has ticket: %-14s |\n", ticketNumber);
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayTicketAssigned(String ticketNumber, String staffId) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                   SUCCESS                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|    Assigned ticket %s to Dr.%-16s   |\n", ticketNumber, staffId);
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayNoTicketsAvailable() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|     No available tickets for today's doctors     |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayQueueHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                 CURRENT QUEUE                       ");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayQueueItem(int position, String ticketNumber, String patientName, String staffId) {
        System.out.printf("\t\t\t\t%d. %s - %s (Dr.%s)%n", position, ticketNumber, patientName, staffId);
    }

    public static void displayEmptyQueue() {
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|            No patients in queue                   |");
        System.out.println("\t\t\t\t|                                                   |");
    }

    public static void displayQueuePosition(int position) {
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|       Your position in queue: %-18d |\n", position);
        System.out.println("\t\t\t\t|                                                   |");
    }

    public static void displayQueueFooter() {
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayReportHeader() {
        System.out.println("\n\t\t\t\t==========================================================================================");
        System.out.println("\t\t\t\t                             QUEUE TIME ANALYSIS REPORT              ");
        System.out.println("\t\t\t\t==========================================================================================");
    }

    public static void displayReportTypeMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              SELECT REPORT TYPE                     ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  1. All Data                                      |");
        System.out.println("\t\t\t\t|  2. Date Range                                    |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayPerformanceSummary(String reportScope, int totalPatients, long avgTime) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              PERFORMANCE SUMMARY                    ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.printf("\t\t\t\t|  Report Scope: %-34s |\n", reportScope);
        System.out.printf("\t\t\t\t|  Patients Processed: %-27d  |\n", totalPatients);
        if (totalPatients > 0) {
            System.out.printf("\t\t\t\t|  Average Queue Time: %-5d minutes                |\n", avgTime);
        }
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayWaitTimeDistribution(int[] waitCategories, int totalPatients) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t           WAIT TIME DISTRIBUTION                    ");
        System.out.println("\t\t\t\t=====================================================");
        String[] labels = {"0-15 min", "16-30 min", "31-45 min", "45+ min"};
        for (int i = 0; i < 4; i++) {
            int pct = (totalPatients > 0) ? (waitCategories[i] * 100 / totalPatients) : 0;
            System.out.printf("\t\t\t\t|  %-12s: %3d (%3d%%)%-25s|\n", labels[i], waitCategories[i], pct, "");
        }
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayDemographicsHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t           PATIENT DEMOGRAPHICS REPORT              ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  1. Group by Gender                               |");
        System.out.println("\t\t\t\t|  2. Group by Age Range                            |");
        System.out.println("\t\t\t\t|  3. Group by Visit Frequency                      |");
        System.out.println("\t\t\t\t|  4. Back                                          |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayInvalidChoice() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    ERROR                          |");
        System.out.println("\t\t\t\t|               Invalid choice                      |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayGenderDemographics(int totalPatients, int maleCount, int femaleCount) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              GENDER DEMOGRAPHICS                    ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.printf("\t\t\t\t|  Total: %-41d |\n", totalPatients);
        System.out.printf("\t\t\t\t|  Male: %3d (%.1f%%)%-31s |\n", maleCount, totalPatients == 0 ? 0 : (maleCount * 100.0 / totalPatients), "");
        System.out.printf("\t\t\t\t|  Female: %3d (%.1f%%)%-29s |\n", femaleCount, totalPatients == 0 ? 0 : (femaleCount * 100.0 / totalPatients), "");
        int others = totalPatients - maleCount - femaleCount;
        if (others > 0) {
            System.out.printf("\t\t\t\t|  Others: %3d (%.1f%%)%-32s |\n", others, (others * 100.0 / totalPatients), "");
        }
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayAgeDemographics(int totalPatients, int youngAdult, int middleAge, int senior) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t               AGE DEMOGRAPHICS                      ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.printf("\t\t\t\t|  Total: %-42d|\n", totalPatients);
        System.out.printf("\t\t\t\t|  Young Adult (18-35): %-27d |\n", youngAdult);
        System.out.printf("\t\t\t\t|  Middle Age (36-55): %-28d |\n", middleAge);
        System.out.printf("\t\t\t\t|  Senior (55+): %-34d |\n", senior);
        System.out.println("\t\t\t\t=====================================================");
    }

    public static int displayPatientManagementMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                PATIENT MANAGEMENT                   ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  1. View My Profile                               |");
        System.out.println("\t\t\t\t|  2. Update My Information                         |");
        System.out.println("\t\t\t\t|  3. Delete My Account                             |");
        System.out.println("\t\t\t\t|  4. Average Queue Time Report                     |");
        System.out.println("\t\t\t\t|  5. Get Ticket                                    |");
        System.out.println("\t\t\t\t|  6. Patient Report                                |");
        System.out.println("\t\t\t\t|  7. Undo Last Change                              |");
        System.out.println("\t\t\t\t|  8. Return to Main Menu                           |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect an option > ");
    }

    public static int displayUserPageMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                 PATIENT PORTAL                      ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  1. Register as New Patient                       |");
        System.out.println("\t\t\t\t|  2. Login with Patient ID                         |");
        System.out.println("\t\t\t\t|  3. Return to Main Menu                           |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect an option > ");
    }
    
    public static int displayGetTicketMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              Get Ticket Portal                   ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  1. Get Ticket Number                             |");
        System.out.println("\t\t\t\t|  2. Exit                                          |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect an option > ");
    }

    public static void displayPatientInfo(String patientId, String name, String contact,
            String email, String gender, int age, String regDate) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              PATIENT INFORMATION                    ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  Patient ID       : " + String.format("%-25s", patientId) + " |");
        System.out.println("\t\t\t\t|  Name            : " + String.format("%-26s", name) + " |");
        System.out.println("\t\t\t\t|  Contact         : " + String.format("%-26s", contact) + " |");
        System.out.println("\t\t\t\t|  Email           : " + String.format("%-26s", email) + " |");
        System.out.println("\t\t\t\t|  Gender          : " + String.format("%-26s", gender) + " |");
        System.out.println("\t\t\t\t|  Age             : " + String.format("%-26s", String.valueOf(age)) + " |");
        System.out.println("\t\t\t\t|  Registration    : " + String.format("%-26s", regDate) + " |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayPatientManagementHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t           PATIENT MANAGEMENT SYSTEM                ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|        Welcome to Patient Management             |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displaySystemFooter() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|         Thank you for using our system!          |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayNotFound(String entityName) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    ERROR                          |");
        System.out.printf("\t\t\t\t|      %s not found.%-32s|\n", entityName, "");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static String promptText(String label) {
        return Input.getStringInput("\t\t\t\t" + label);
    }

    public static int promptInt(String label) {
        return Input.getIntegerInput("\t\t\t\t" + label);
    }

    public static boolean confirm(String label) {
        String s = Input.getStringInput("\t\t\t\t" + label);
        return "Y".equalsIgnoreCase(s.trim());
    }

    public static void displayMessage(String msg) {
        System.out.println("\t\t\t\t" + msg);
    }

    public static void displayAverageQueueMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              AVERAGE QUEUE TIME REPORT         ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  1. Selected Date Range                           |");
        System.out.println("\t\t\t\t|  2. Day of Week                                   |");
        System.out.println("\t\t\t\t|  3. All Data Timeline                             |");
        System.out.println("\t\t\t\t|  4. Back                                          |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void printHourlyTimeline(int startHour, int endHour, int[] avg) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                HOURLY TIMELINE (mins)               ");
        System.out.println("\t\t\t\t=====================================================");
        int idx = 0;
        for (int h = startHour; h < endHour; h++) {
            System.out.printf("\t\t\t\t| %02d:00-%02d:00 | Avg: %3d |\n", h, h + 1, avg[idx++]);
        }
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void printHeatmap(int startHour, int endHour, int[][] avg) {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String pad = "\t\t\t\t";
        int cells = (endHour - startHour) + 1;
        displayReportHeader();
        sep(pad, cells);
        System.out.printf(pad + "| %-5s |", "Day");
        for (int h = startHour; h < endHour; h++) {
            System.out.printf(" %5s |", h + ":00");
        }
        System.out.println();
        sep(pad, cells);
        for (int d = 0; d < 7; d++) {
            System.out.printf(pad + "| %-5s |", days[d]);
            for (int s = 0; s < endHour - startHour; s++) {
                System.out.printf(" %5d |", avg[d][s]);
            }
            System.out.println();
            sep(pad, cells);
        }
    }

    private static void sep(String pad, int cells) {
        System.out.print(pad);
        for (int i = 0; i < cells; i++) {
            System.out.print("+-------");
        }
        System.out.println("+");
    }

    @SuppressWarnings("unused")
    private static String fit(String s, int w) {
        if (s == null) {
            s = "";
        }
        if (s.length() >= w) {
            return s.substring(0, w);
        }
        StringBuilder b = new StringBuilder(s);
        while (b.length() < w) {
            b.append(' ');
        }
        return b.toString();
    }

    public void newLine() {
        System.out.println("\t\t\t\t-----------------------------------------------------");
    }

    public String promptLoginId() {
        return Input.getStringInput("\t\t\t\tEnter your patient ID: ");
    }

    public String promptPatientName() {
        return Input.getStringInput("\t\t\t\tEnter Patient Name: ");
    }

    public String promptPatientContact() {
        newLine();
        return Input.getStringInput("\t\t\t\tEnter Contact Number: ");
    }

    public String promptPatientEmail() {
        newLine();
        return Input.getStringInput("\t\t\t\tEnter Email: ");
    }

    public String promptPatientGender() {
        newLine();
        return Input.getStringInput("\t\t\t\tEnter Gender (Male/Female): ");
    }

    public int promptPatientAge() {
        newLine();
        return Input.getIntegerInput("\t\t\t\tEnter Age: ");
    }

    public void displaySingleVisitFound() {
        System.out.println("\t\t\t\tSingle visit record found.");
    }

    public void displayMultipleVisitsFound() {
        System.out.println("\t\t\t\tMultiple visits found. Displaying visit menu...");
    }

    public int promptUpdateSelectOption() {
        return Input.getIntegerInput("\t\t\t\tSelect option: ");
    }

    public String promptNewContactNumber() {
        return Input.getStringInput("\t\t\t\tEnter new Contact Number: ");
    }

    public String promptNewEmail() {
        return Input.getStringInput("\t\t\t\tEnter new Email: ");
    }

    public String promptNewAge() {
        return Input.getStringInput("\t\t\t\tEnter new Age: ");
    }

    public void displayDeleteOptions() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              DELETE OPTIONS                         ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. Delete visit record                           |");
        System.out.println("\t\t\t\t|  2. Delete entire patient                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public int promptDeleteOption() {
        return Input.getIntegerInput("\t\t\t\tEnter option (1/2): ");
    }

    public void displayVisitList(Patient patient) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              VISIT RECORDS                          ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");

        Visit[] visits = patient.getVisits();
        int count = patient.getVisitCount();
        for (int i = 0; i < count; i++) {
            Date d = visits[i].getQueueStart();
            String dateStr = d == null ? "N/A" : patient.getDateFormat().format(d);
            System.out.printf("\t\t\t\t|  [%d] %-44s |\n", (i + 1), dateStr);
        }

        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public int promptVisitNumberToDelete() {
        return Input.getIntegerInput("\t\t\t\tEnter visit number to delete: ");
    }

    public void displayVisitDeleteSuccess() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                   SUCCESS                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|        Visit record deleted successfully.         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public boolean confirmDeletePatient() {
        String s = Input.getStringInput("\t\t\t\tConfirm delete patient (Y/N): ");
        return "Y".equalsIgnoreCase(s.trim());
    }

    public boolean confirmGetTicket() {
        String s = Input.getStringInput("\t\t\t\tDo you want to get a ticket now? (Y/N): ");
        return "Y".equalsIgnoreCase(s.trim());
    }

    public void displayNoTicketIssued() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|                no ticket will be issued.         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public int promptQueueReportMenuOption() {
        return Input.getIntegerInput("\t\t\t\tSelect an option > ");
    }

    public void displayDayOfWeekMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              SELECT DAY OF WEEK                     ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. Monday     2. Tuesday    3. Wednesday         |");
        System.out.println("\t\t\t\t|  4. Thursday   5. Friday     6. Saturday          |");
        System.out.println("\t\t\t\t|  7. Sunday                                        |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public int promptDayOfWeek() {
        return Input.getIntegerInput("\t\t\t\tChoose day of week (1 - 7): ");
    }

    public String promptStartDate() {
        return Input.getStringInput("\t\t\t\tStart Date (dd-MM-yyyy): ");
    }

    public String promptEndDate() {
        return Input.getStringInput("\t\t\t\tEnd Date   (dd-MM-yyyy): ");
    }

    public int promptDemographicsMenuOption() {
        return Input.getIntegerInput("\t\t\t\tSelect option: ");
    }

    public void displayNoUndoHistory() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|            No undo history found.                |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public void displayUndoDone(String action, String id) {
        String msg = switch (action.toLowerCase()) {
            case "create" ->
                "Undo: newly created patient removed";
            case "delete" ->
                "Undo: deleted patient restored";
            case "update" ->
                "Undo: patient reverted to previous data";
            default ->
                "Undo: change reverted";
        };
        System.out.println("\n\t\t\t\t----------------------------------------------");
        System.out.println("\t\t\t\t" + msg + " (" + id + ").");
        System.out.println("\t\t\t\t----------------------------------------------");
    }

    public void displayUndoNoEffect(String action) {
        System.out.println("\n\t\t\t\t----------------------------------------------");
        System.out.println("\t\t\t\tUndo: " + action + " had no effect (record not present).");
        System.out.println("\t\t\t\t----------------------------------------------");
    }

    public void displayUndoFailed() {
        System.out.println("\n\t\t\t\t----------------------------------------------");
        System.out.println("\t\t\t\tUndo failed: snapshot missing or invalid.");
        System.out.println("\t\t\t\t----------------------------------------------");
    }

    public void displayUndoUnknown() {
        System.out.println("\n\t\t\t\t----------------------------------------------");
        System.out.println("\t\t\t\tUnknown history action. Nothing undone.");
        System.out.println("\t\t\t\t----------------------------------------------");
    }

    public void patientNotFound() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    ERROR                          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|            Patient ID not found.                 |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public void displayQueueStatus(String message) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t               QUEUE STATUS                         ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t" + message);
        System.out.println("\t\t\t\t=====================================================");
    }

    public void displayNoVisitRecordsToDelete() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|         No visit records to delete.              |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayVisitFrequencyTables(MapInterface<String, Patient> newPatients, MapInterface<String, Patient> returningPatients) {
        System.out.println("\n\t\t\t\t=====================================================\t				|=====================================================");
        System.out.println("\t\t\t\t              NEW PATIENTS (0 VISITS)                \t 				|          RETURNING PATIENTS (1+ VISITS)            |");
        System.out.println("\t\t\t\t=====================================================\t				======================================================");
        System.out.printf("\t\t\t\t| Total: %-43d |\t\t			|  Total: %-42d |\n",
                newPatients.size(), returningPatients.size());
        System.out.println("\t\t\t\t-----------------------------------------------------\t				|-----------------------------------------------------");
        Object[] newPatientValues = newPatients.getAllValues();
        Object[] returnPatientValues = returningPatients.getAllValues();

        int maxRows = Math.max(newPatientValues.length, returnPatientValues.length);

        for (int i = 0; i < maxRows; i++) {
            String leftLine = "\t\t\t\t|                                                     |";
            String rightLine = "\t\t\t\t|                                                    |";
            if (i < newPatientValues.length) {
                Patient patient = (Patient) newPatientValues[i];
                leftLine = String.format("\t\t\t\t| %-8s | %-20s | %2d visits        |",
                        patient.getPatient_id(),
                        truncateName(patient.getPatient_name(), 20),
                        patient.getVisitCount());
            }
            if (i < returnPatientValues.length) {
                Patient patient = (Patient) returnPatientValues[i];
                rightLine = String.format("\t\t\t\t| %-8s | %-20s | %2d visits        |",
                        patient.getPatient_id(),
                        truncateName(patient.getPatient_name(), 20),
                        patient.getVisitCount());
            }

            System.out.println(leftLine + "\t" + rightLine);
        }
        System.out.println("\t\t\t\t=====================================================\t				|=====================================================");
        displayVisitFrequencySummary(newPatients.size(), returningPatients.size());
    }

    public static void displayVisitFrequencySummary(int newPatientCount, int returningPatientCount) {
        int total = newPatientCount + returningPatientCount;
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                      SUMMARY                         ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.printf("\t\t\t\t| Total Patients: %-33d |\n", total);
        System.out.printf("\t\t\t\t| New Patients (0 visits): %-24d |\n", newPatientCount);
        System.out.printf("\t\t\t\t| Returning Patients (1+ visits): %-17d |\n", returningPatientCount);
        System.out.println("\t\t\t\t=====================================================");
    }

    private static String truncateName(String name, int maxLength) {
        if (name == null) {
            return "";
        }
        if (name.length() <= maxLength) {
            return name;
        }
        return name.substring(0, maxLength - 3) + "...";
    }
}
