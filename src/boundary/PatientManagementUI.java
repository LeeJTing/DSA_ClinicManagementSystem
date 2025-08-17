/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;
import utility.Input;

public class PatientManagementUI {
    
    // Create Patient UI
    public static void displayCreateHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              NEW PATIENT REGISTRATION               ");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displayCreateSuccess(String id) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                   SUCCESS                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|         Patient %s registered.%-14s|\n", id, "");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    // Update Patient UI
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
    
    // Delete Patient UI
    public static void displayDeleteWarning() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                DELETE ACCOUNT                       ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|               WARNING                             |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|         This action cannot be undone!            |");
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
    
    // Ticket Management UI
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
    
    // Queue Display UI
    public static void displayQueueHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                 CURRENT QUEUE                       ");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displayQueueItem(int position, String ticketNumber, String patientName, String staffId) {
        System.out.printf("\t\t\t\t%d. %s - %s (Dr.%s)%s%n", position, ticketNumber,
            patientName, staffId);
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
    
    // Reports UI
    public static void displayReportHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t            QUEUE TIME ANALYSIS REPORT              ");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displayReportTypeMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              SELECT REPORT TYPE                     ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. All Data                                      |");
        System.out.println("\t\t\t\t|  2. Date Range                                    |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displayPerformanceSummary(String reportScope, int totalPatients, long avgTime) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              PERFORMANCE SUMMARY                    ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|  Report Scope: %-34s |\n", reportScope);
        System.out.printf("\t\t\t\t|  Patients Processed: %-27d  |\n", totalPatients);
        if (totalPatients > 0) {
            System.out.printf("\t\t\t\t|  Average Queue Time: %-5d minutes                |\n", avgTime);
        }
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displayWaitTimeDistribution(int[] waitCategories, int totalPatients) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t           WAIT TIME DISTRIBUTION                    ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        String[] labels = {"0-15 min", "16-30 min", "31-45 min", "45+ min"};
        for (int i = 0; i < 4; i++) {
            int pct = (totalPatients > 0) ? (waitCategories[i] * 100 / totalPatients) : 0;
            System.out.printf("\t\t\t\t|  %-12s: %3d (%3d%%)%-25s|\n", labels[i], waitCategories[i], pct, "");
        }
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    // Patient Reports UI
    public static void displayDemographicsHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t           PATIENT DEMOGRAPHICS REPORT              ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. Group by Gender                               |");
        System.out.println("\t\t\t\t|  2. Group by Age Range                            |");
        System.out.println("\t\t\t\t|  3. Back                                          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displayInvalidChoice() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    ERROR                          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|               Invalid choice                      |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displayGenderDemographics(int totalPatients, int maleCount, int femaleCount) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              GENDER DEMOGRAPHICS                    ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|  Total: %-41d |\n", totalPatients);
        System.out.printf("\t\t\t\t|  Male: %3d (%.1f%%)%-31s |\n", maleCount, (maleCount * 100.0 / totalPatients), "");
        System.out.printf("\t\t\t\t|  Female: %3d (%.1f%%)%-29s |\n", femaleCount, (femaleCount * 100.0 / totalPatients), "");
        
        int others = totalPatients - maleCount - femaleCount;
        if (others > 0) {
            System.out.printf("\t\t\t\t|  Others: %3d (%.1f%%)%-32s |\n", others, (others * 100.0 / totalPatients), "");
        }
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displayAgeDemographics(int totalPatients, int youngAdult, int middleAge, int senior) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t               AGE DEMOGRAPHICS                      ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|  Total: %-42d|\n", totalPatients);
        System.out.printf("\t\t\t\t|  Young Adult (18-35): %-27d |\n", youngAdult);
        System.out.printf("\t\t\t\t|  Middle Age (36-55): %-28d |\n", middleAge);
        System.out.printf("\t\t\t\t|  Senior (55+): %-34d |\n", senior);
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    // Main Menus
    public static int displayPatientManagementMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                PATIENT MANAGEMENT                   ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. View My Profile                               |");
        System.out.println("\t\t\t\t|  2. Update My Information                         |");
        System.out.println("\t\t\t\t|  3. Delete My Account                             |");
        System.out.println("\t\t\t\t|  4. Average Queue Time Report                     |");
        System.out.println("\t\t\t\t|  5. Get Ticket                                    |");
        System.out.println("\t\t\t\t|  6. Patient Report                                |");
        System.out.println("\t\t\t\t|  7. Return to Main Menu                           |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect an option > ");
    }
    
    public static int displayUserPageMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                 PATIENT PORTAL                      ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. Register as New Patient                       |");
        System.out.println("\t\t\t\t|  2. Login with Patient ID                         |");
        System.out.println("\t\t\t\t|  3. Return to Main Menu                           |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect an option > ");
    }
    
    // Patient Profile Display
    public static void displayPatientInfo(String patientId, String name, String contact,
            String email, String gender, int age, String regDate) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              PATIENT INFORMATION                    ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  Patient ID       : " + String.format("%-25s", patientId) + " |");
        System.out.println("\t\t\t\t|  Name            : " + String.format("%-26s", name) + " |");
        System.out.println("\t\t\t\t|  Contact         : " + String.format("%-26s", contact) + " |");
        System.out.println("\t\t\t\t|  Email           : " + String.format("%-26s", email) + " |");
        System.out.println("\t\t\t\t|  Gender          : " + String.format("%-26s", gender) + " |");
        System.out.println("\t\t\t\t|  Age             : " + String.format("%-26s", String.valueOf(age)) + " |");
        System.out.println("\t\t\t\t|  Registration    : " + String.format("%-26s", regDate) + " |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    // System UI
    public static void displayPatientManagementHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t           PATIENT MANAGEMENT SYSTEM                ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|        Welcome to Patient Management             |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    public static void displaySystemFooter() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|         Thank you for using our system!          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
}