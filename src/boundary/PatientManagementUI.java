/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;
import utility.Input;

/**
 * User Interface for Patient Management Module
 *
 * @author User
 */
public class PatientManagementUI {
    
    /**
     * Displays the main patient management menu for logged-in patients
     *
     * @return user's menu choice
     */
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
    
    /**
     * Displays the user page menu for new/returning patients
     *
     * @return user's menu choice
     */
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
    
    /**
     * Displays patient information in a formatted way
     *
     * @param patientId Patient ID
     * @param name Patient name
     * @param contact Contact number
     * @param email Email address
     * @param gender Gender
     * @param age Age
     * @param regDate Registration date
     */
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
    
    /**
     * Displays the header for patient management operations
     */
    public static void displayPatientManagementHeader() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t           PATIENT MANAGEMENT SYSTEM                ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|        Welcome to Patient Management             |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
    
    /**
     * Displays the system footer
     */
    public static void displaySystemFooter() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|         Thank you for using our system!          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
}