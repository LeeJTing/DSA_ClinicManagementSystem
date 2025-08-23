/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package boundary;

import utility.Input;

/**
 * Main Menu System for Hospital Management System
 *
 * @author Lwin
 */
public class menu {
    
    public static String askStaffID() {
        System.out.print("\n\t\t\t\tStaff ID(e.s. S000001): ");
        String input = Input.getStringInput();

        return input;
    }

    public static String askPatientID() {
        System.out.print("\n\t\t\t\tPatient ID(e.s. P000001): ");
        String id = Input.getStringInput();

        return id;
    }

    public static int mainMenu() {
        int choice = 0;
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t          WELCOME TO HOSPITAL MANAGEMENT SYSTEM     ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|       1. Doctor Management Module                 |");
        System.out.println("\t\t\t\t|       2. Medical Treatment Management Module      |");
        System.out.println("\t\t\t\t|       3. Pharmacy Management Module               |");
        System.out.println("\t\t\t\t|       4. Consultation for Walk-in                 |");
        System.out.println("\t\t\t\t|       5. Exit                                     |");
        System.out.println("\t\t\t\t=====================================================");
        do{
            choice = Input.getIntegerInput("\t\t\t\tEnter your choice: ");
        }while (choice < 0 || choice > 5);
        return choice;
    }

    public static int mainMenuUI() {
        int choice = 0;
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                   MAIN PAGE                         ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. Patient                                       |");
        System.out.println("\t\t\t\t|  2. Staff                                         |");
        System.out.println("\t\t\t\t|  3. Consultation                                  |");
        System.out.println("\t\t\t\t|  4. Exit                                          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        do{
            choice = Input.getIntegerInput("\t\t\t\tEnter your choice: ");
        }while (choice < 0 || choice > 4);
        return choice;
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

    public static int consultationMenu() {
        int option;
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              CONSULTATION MENU                      ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. Patient                                       |");
        System.out.println("\t\t\t\t|  2. Staff                                         |");
        System.out.println("\t\t\t\t|  3. Exit                                          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        option = Input.getIntegerInput("\t\t\t\tSelect option: ");
        
        return option;
    }
    
    public static void handleStaffPortal() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|          Staff Portal not implemented.           |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
}
