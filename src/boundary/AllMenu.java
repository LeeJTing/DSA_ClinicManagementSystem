/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;
import utility.Input;

/**
 *
 * @author Lwin
 */
public class AllMenu {
    
    public static int mainMenuUI() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                   MAIN PAGE                         ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. User                                          |");
        System.out.println("\t\t\t\t|  2. Staff                                         |");
        System.out.println("\t\t\t\t|  3. Exit                                          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect option: ");
    }
    
    public static int patientMenu() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                 PATIENT MENU                        ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. My Profile                                    |");
        System.out.println("\t\t\t\t|  2. My Consultations                              |");
        System.out.println("\t\t\t\t|  3. My Treatments                                 |");
        System.out.println("\t\t\t\t|  4. My Prescriptions                              |");
        System.out.println("\t\t\t\t|  5. My Medicines                                  |");
        System.out.println("\t\t\t\t|  6. Logout                                        |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        return Input.getIntegerInput("\t\t\t\tSelect option: ");
    }
}