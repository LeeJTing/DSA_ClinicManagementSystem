/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

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
        SimpleDateFormat tf = patient.getTimeFormat();
        String reg = patient.getRegistration_date() == null ? "N/A" : df.format(patient.getRegistration_date());
        System.out.printf("\t\t\t\t| Registration    : %-33s |\n", reg);
        String qs = (visit == null || visit.getQueueStart() == null) ? "N/A" : tf.format(visit.getQueueStart());
        String qe = (visit == null || visit.getQueueEnd() == null) ? "N/A" : tf.format(visit.getQueueEnd());
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

    public static void displayNoTicketsAvailable() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|     No available tickets for today's patients     |");
        System.out.println("\t\t\t\t|                                                   |");
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

    public static void displayAgeDemographicsTable(String[] young, String[] middle, String[] senior, int total) {
        // Header
        System.out.println("\t\t\t\t==========================================================================");
        System.out.println("\t\t\t\t                          AGE DEMOGRAPHICS                           ");
        System.out.println("\t\t\t\t==========================================================================");
        System.out.println("\t\t\t\t|       | Young Adult        | Middle Age         | Senior       | Total |");
        System.out.println("\t\t\t\t--------------------------------------------------------------------------");

        int rows = Math.max(young == null ? 0 : young.length,
                Math.max(middle == null ? 0 : middle.length,
                        senior == null ? 0 : senior.length));

        for (int r = 0; r < rows; r++) {
            String y = (young != null && r < young.length && young[r] != null) ? young[r] : "";
            String m = (middle != null && r < middle.length && middle[r] != null) ? middle[r] : "";
            String s = (senior != null && r < senior.length && senior[r] != null) ? senior[r] : "";
            System.out.printf("\t\t\t\t| %-5s| %-19s | %-18s | %-12s | %-5s |\n",
                    "", y, m, s, "");
            System.out.println("\t\t\t\t--------------------------------------------------------------------------");
        }
        int yc = young == null ? 0 : young.length;
        int mc = middle == null ? 0 : middle.length;
        int sc = senior == null ? 0 : senior.length;

        System.out.printf("\t\t\t\t| %-5s| %-19s | %-18s | %-12s | %-5s |\n",
                "Count", String.valueOf(yc), String.valueOf(mc), String.valueOf(sc), String.valueOf(total));
        System.out.println("\t\t\t\t==========================================================================");
    }

    public static int displayPatientManagementMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                PATIENT MANAGEMENT                   ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  1. View My Profile                               |");
        System.out.println("\t\t\t\t|  2. Update My Information                         |");
        System.out.println("\t\t\t\t|  3. Delete My Account                             |");
        System.out.println("\t\t\t\t|  4. Average Queue Time Report                     |");
        System.out.println("\t\t\t\t|  5. Undo Last Change                              |");
        System.out.println("\t\t\t\t|  6. Return to Main Menu                           |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect an option > ");
    }

    public void displayDoctorLoginSuccess() {
        System.out.println("\n\t\t\t\t===================================================");
        System.out.println("\t\t\t\t             Doctor Login Successful!");
        System.out.println("\t\t\t\t   Access granted to patient reports.");
        System.out.println("\t\t\t\t===================================================\n");
    }

    public void displayDoctorLoginFailed() {
        System.out.println("\n\t\t\t\t===================================================");
        System.out.println("\t\t\t\t             Doctor Login Failed!");
        System.out.println("\t\t\t\t   Please try again with correct credentials.");
        System.out.println("\t\t\t\t===================================================\n");
    }

    public boolean askViewReportsAgain() {
        String s = Input.getStringInput("\t\t\t\tDo you want to view reports again? (Y/N): ");
        return "Y".equalsIgnoreCase(s.trim());
    }

    public static int displayUserPageMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                 PATIENT PORTAL                      ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|  1. Register as New Patient                       |");
        System.out.println("\t\t\t\t|  2. Login with Patient ID                         |");
        System.out.println("\t\t\t\t|  3. Patient Report                                |");
        System.out.println("\t\t\t\t|  4. Return to Main Menu                           |");
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
            System.out.printf("\t\t\t\t| %02d:00-%02d:00           | Avg: %3d                  |\n", h, h + 1, avg[idx++]);
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

    public boolean confirmLoginStaff() {
        String s = Input.getStringInput("\t\t\t\tAre you staff? (Y/N): ");
        return "Y".equalsIgnoreCase(s.trim());
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
        System.out.println("\t\t\t\t|                no ticket will be issued.          |");
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
        System.out.println("\t\t\t\t|            No undo history found.                 |");
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

    public static void displayPatientSummary(Patient p) {
        System.out.printf("\t\t\t\t| %-10s | %-20s | %-5d | %-6s |%n",
                p.getPatient_id(),
                p.getPatient_name(),
                p.getAge(),
                p.getPatient_gender());
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

    public static void displayQueueHeader() {
        System.out.println("\n\t\t\t\t=====================================================================");
        System.out.println("\t\t\t\t                          CURRENT TICKET QUEUE                 ");
        System.out.println("\t\t\t\t=====================================================================");
        System.out.println("\t\t\t\t| Count              | Ticket    | Time Started | Time Waited(mins) |");
        System.out.println("\t\t\t\t|--------------------|-----------|--------------|-------------------|");
    }

    public static void displayQueueRow(String youIndicator, String count, String ticketNo, String timeStarted, String timeWaited) {
        String countDisplay;
        if (!youIndicator.isEmpty()) {
            countDisplay = count + " " + youIndicator;
        } else {
            countDisplay = count;
        }

        String row = String.format("\t\t\t\t|  %-15s   | %-9s | %-12s | %-17s |",
                countDisplay,
                ticketNo,
                timeStarted,
                timeWaited);

        System.out.println(row);
    }

    public static void displayEmptyQueue() {
        System.out.println("\t\t\t\t|                                                         |");
        System.out.println("\t\t\t\t|               No patients in queue                      |");
        System.out.println("\t\t\t\t|                                                         |");
    }

    public static void displayQueueFooter() {
        System.out.println("\t\t\t\t=====================================================================");
    }

    public static void displayTicketAssigned(String ticketNumber) {
        System.out.println("\t\t\t\t                  Ticket Assigned To You: " + ticketNumber);
        System.out.println("\t\t\t\t=====================================================================");
    }

    public static void displayBracket() {
        System.out.println("\t\t\t\t|                                                         |");
        System.out.println("\t\t\t\t|                                                         |");
        System.out.println("\t\t\t\t|                                                         |");
    }

    public static void displayDeletedAccountNotice() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|              ACCOUNT STATUS NOTICE                |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|   Your account was deleted. You can revert it.    |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static boolean promptUndoDeletedAccount() {
        String s = Input.getStringInput("\t\t\t\tRevert deletion now? (Y/N): ");
        return s != null && s.trim().equalsIgnoreCase("Y");
    }

    public static void displayAccessCancelled() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t Access cancelled. You may log in with another ID.");
        System.out.println("\t\t\t\t=====================================================");
    }

    public static void displayAllPatientsTable(Object[] values) {
        System.out.println("\n\t\t\t\t====================================================================================================================");
        System.out.println("\t\t\t\t                                             Patient Details                                                        ");
        System.out.println("\t\t\t\t====================================================================================================================");
        System.out.println("\t\t\t\t| Patient ID |        Name        |   Contact   |         Email         | Gender | Age |     Registration          |");
        System.out.println("\t\t\t\t--------------------------------------------------------------------------------------------------------------------");

        if (values == null || values.length == 0) {
            System.out.println("\t\t\t\t|                                              No patients found.                                                  |");
            System.out.println("\t\t\t\t====================================================================================================================");
            return;
        }

        for (int i = 0; i < values.length; i++) {
            Patient p = (Patient) values[i];
            if (p == null) {
                continue;
            }
            String regStr = "N/A";
            try {
                if (p.getRegistration_date() != null) {
                    SimpleDateFormat df = (p.getDateFormat() != null)
                            ? p.getDateFormat()
                            : new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    regStr = df.format(p.getRegistration_date());
                }
            } catch (Exception e) {
                regStr = "N/A";
            }

            System.out.printf(
                    "\t\t\t\t| %-9s | %-18s | %-11s | %-22s | %-6s | %3d | %-19s       |\n",
                    p.getPatient_id(),
                    p.getPatient_name(),
                    p.getPatient_contact(),
                    p.getPatient_email(),
                    p.getPatient_gender(),
                    p.getAge(),
                    regStr
            );
        }
        System.out.println("\t\t\t\t====================================================================================================================");
    }

    public void displayVisitFreqSummary(int total, int newCnt, int retCnt) {
        double np = (total == 0) ? 0 : (100.0 * newCnt / total);
        double rp = (total == 0) ? 0 : (100.0 * retCnt / total);
        System.out.println();
        System.out.println("\t\t\t==================== VISIT FREQUENCY SUMMARY ====================");
        System.out.printf("\t\t\tTotal Patients : %d%n", total);
        System.out.printf("\t\t\tNew (<2 visits): %d (%.1f%%)%n", newCnt, np);
        System.out.printf("\t\t\tReturn (>=2)   : %d (%.1f%%)%n", retCnt, rp);
        System.out.println("\t\t\t===============================================================\n");
    }

    public void displayTopHeader(String title) {
        System.out.println("\t\t\t---------------------------------------------------------------");
        System.out.printf("\t\t\t%s%n", title);
        System.out.println("\t\t\t---------------------------------------------------------------");
        System.out.println("\t\t\t| ID       | Name                   | Visits | Latest Visit    |");
        System.out.println("\t\t\t---------------------------------------------------------------");
    }

    public void displayTopRow(String id, String name, int visits, String latest) {
        if (name == null) {
            name = "-";
        }
        if (name.length() > 23) {
            name = name.substring(0, 23);
        }
        System.out.printf("\t\t\t| %-8s | %-23s        | %6d | %-15s |%n",
                (id == null ? "-" : id),
                name,
                visits,
                (latest == null ? "N/A" : latest));
    }

    public void displayTopFooter() {
        System.out.println("\t\t\t---------------------------------------------------------------\n");
    }

    private static final int BOX_W = 94;

    private String repChar(char c, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    private String padRight(String s, int w) {
        if (s == null) {
            s = "";
        }
        if (s.length() >= w) {
            return s.substring(0, w);
        }
        StringBuilder sb = new StringBuilder(w);
        sb.append(s);
        while (sb.length() < w) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public void displayVisitFreqReportHeader(int total, int newCnt, int retCnt) {
        String top = repChar('=', BOX_W);
        System.out.println("\t\t\t\t" + top);
        System.out.println("\t\t\t\t| " + padRight("VISIT FREQUENCY REPORT", BOX_W - 4) + "|");
        System.out.println("\t\t\t\t" + repChar('=', BOX_W));
        System.out.println("\t\t\t\t| " + padRight("Total Patients  : " + total, BOX_W - 4) + "|");
        System.out.println("\t\t\t\t| " + padRight("New : " + newCnt, BOX_W - 4) + "|");
        System.out.println("\t\t\t\t| " + padRight("Returning : " + retCnt, BOX_W - 4) + "|");
        System.out.println("\t\t\t\t" + repChar('=', BOX_W));
    }

    public void displayVisitFreqTableHeader() {
        String header
                = padRight("Type", 8) + " | "
                + padRight("PatientID", 10) + " | "
                + padRight("Name", 26) + " | "
                + padRight("Visits", 6) + " | "
                + padRight("Latest Visit", 20);
        System.out.println("\t\t\t\t| " + padRight(header, BOX_W - 4) + "|");
        System.out.println("\t\t\t\t| " + padRight(repChar('-', 8) + "-+-"
                + repChar('-', 10) + "-+-"
                + repChar('-', 26) + "-+-"
                + repChar('-', 6) + "-+-"
                + repChar('-', 31), BOX_W - 4) + "|");
    }

    public void displayVisitFreqTableRow(String type, String patientId, String name, int visits, String latest) {
        String row
                = padRight(type, 8) + " | "
                + padRight(patientId, 10) + " | "
                + padRight(name, 26) + " | "
                + padRight(String.valueOf(visits), 6) + " | "
                + padRight(latest, 20);
        System.out.println("\t\t\t\t| " + padRight(row, BOX_W - 4) + "|");
    }

    public void displayVisitFreqTableFooter() {
        System.out.println("\t\t\t\t| " + padRight("", BOX_W - 4) + "|");
        System.out.println("\t\t\t\t" + repChar('=', BOX_W));
        System.out.println();
    }

    public static void displayGenderGrid(String[] maleIds, int maleCount,
            String[] femaleIds, int femaleCount) {
        int total = maleCount + femaleCount;
        final int labelW = 7;
        final int colW = 12;
        final int totalW = 6;

        System.out.println("\t\t\t\t=================================================================");
        System.out.println("\t\t\t\t|                       Gender DemoGraphics                     |");
        System.out.println("\t\t\t\t=================================================================");
        System.out.printf("\t\t\t\t| %-" + labelW + "s |    %-" + colW + "s    |    %-" + colW + "s    |    %-" + totalW + "s |\n", "", "Male", "Female", "total");
        System.out.println("\t\t\t\t-----------------------------------------------------------------");

        int rows = Math.max(maleCount, femaleCount);
        for (int i = 0; i < rows; i++) {
            String m = (i < maleCount) ? maleIds[i] : "";
            String f = (i < femaleCount) ? femaleIds[i] : "";
            System.out.printf("\t\t\t\t| %-" + labelW + "s |    %-" + colW + "s    |    %-" + colW + "s    |    %-" + totalW + "s |\n",
                    "", m, f, "");
            System.out.println("\t\t\t\t-----------------------------------------------------------------");
        }

        double malePct = (total == 0) ? 0 : (maleCount * 100.0 / total);
        double femalePct = (total == 0) ? 0 : (femaleCount * 100.0 / total);

        System.out.printf("\t\t\t\t| %-" + labelW + "s |   %-" + colW + "s     |    %-" + colW + "s    |    %-" + totalW + "d |\n", "Count", maleCount + " (" + String.format("%.2f%%", malePct) + ")", femaleCount + " (" + String.format("%.2f%%", femalePct) + ")", total);
        System.out.println("\t\t\t\t=================================================================");
    }

    public static void displayNoTicketsInQueueRow() {
        System.out.println("\t\t\t\t|                         No tickets in queue                       |");
    }
}
