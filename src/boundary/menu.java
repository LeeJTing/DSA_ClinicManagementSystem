/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package boundary;

import control.PatientManagement;
import static control.PatientManagement.getCurrentPatient;
import dao.Master;
import entity.Patient;
import utility.Input;

/**
 * Main Menu System for Hospital Management System
 *
 * @author Lwin
 */
public class menu {
    
    public static String askStaffID() {
        System.out.print("\n          Staff ID(e.s. S000001): ");
        String input = Input.getStringInput();

        return input;
    }

    public static String askPatientID() {
        System.out.print("\n          Patient ID(e.s. P000001): ");
        String id = Input.getStringInput();

        return id;
    }

    public static int mainMenu() {
        int choice = 0;
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t          WELCOME TO HOSPITAL MANAGEMENT SYSTEM     ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|       1. Doctor Management Module                 |");
        System.out.println("\t\t\t\t|       2. Consultation Management Module           |");
        System.out.println("\t\t\t\t|       3. Medical Treatment Management Module      |");
        System.out.println("\t\t\t\t|       4. Pharmacy Management Module               |");
        System.out.println("\t\t\t\t|       5. Exit                                     |");
        System.out.println("\t\t\t\t=====================================================");
        while (choice < 0 || choice > 5) {
            choice = Input.getIntegerInput("\t\t\t\tEnter your choice: ");
        }
        return choice;
    }

    public static int mainMenuUI() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                   MAIN PAGE                         ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. Patient                                       |");
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

    public static void displayMainMenu() {
        while (true) {
            int choice = AllMenu.mainMenuUI();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t              Entering User Portal                   ");
                    System.out.println("\t\t\t\t=====================================================");
                    handleUserPortal();
                }
                case 2 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t              Entering Staff Portal                  ");
                    System.out.println("\t\t\t\t=====================================================");
                    handleStaffPortal();
                }
                case 3 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t|    Thank you for using Dental Management         |");
                    System.out.println("\t\t\t\t|                   System!                         |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t|                   Goodbye!                        |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t=====================================================");
                    System.exit(0);
                }
                default -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t|                    ERROR                          |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t|        Invalid option. Please select 1-3.        |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t=====================================================");
                }
            }
        }
    }

    public static void handlePatientMenu() {
        patientMenu();

//        PatientManagement pm = new PatientManagement();
//        while (!pm.patientWasDeleted) {
//            int choice = AllMenu.patientMenu();
//            Patient current = PatientManagement.getCurrentPatient();
//            if (current == null) {
//                break;
//            }
//
//            switch (choice) {
//                case 1 -> {
//                    System.out.println("\n\t\t\t\t=====================================================");
//                    System.out.println("\t\t\t\t               My Patient                            ");
//                    System.out.println("\t\t\t\t=====================================================");
//                }
//                case 2 -> {
//                    System.out.println("\n\t\t\t\t=====================================================");
//                    System.out.println("\t\t\t\t               My Consultations                      ");
//                    System.out.println("\t\t\t\t=====================================================");
//                }
//                case 3 -> {
//                    System.out.println("\n\t\t\t\t=====================================================");
//                    System.out.println("\t\t\t\t                My Treatments                        ");
//                    System.out.println("\t\t\t\t=====================================================");
//                }
//                case 4 -> {
//                    System.out.println("\n\t\t\t\t=====================================================");
//                    System.out.println("\t\t\t\t               My Prescriptions                      ");
//                    System.out.println("\t\t\t\t=====================================================");
//                }
//                case 5 -> {
//                    System.out.println("\n\t\t\t\t=====================================================");
//                    System.out.println("\t\t\t\t                 My Medicines                        ");
//                    System.out.println("\t\t\t\t=====================================================");
//                }
//                case 6 -> {
//                    System.out.println("\n\t\t\t\t=====================================================");
//                    System.out.println("\t\t\t\t|                                                   |");
//                    System.out.println("\t\t\t\t|               Logging out...                      |");
//                    System.out.println("\t\t\t\t|                                                   |");
//                    System.out.println("\t\t\t\t=====================================================");
//                    Master.setCurrentPatientId("");
//                    return;
//                }
//                default -> {
//                    System.out.println("\n\t\t\t\t=====================================================");
//                    System.out.println("\t\t\t\t|                    ERROR                          |");
//                    System.out.println("\t\t\t\t|                                                   |");
//                    System.out.println("\t\t\t\t|               Invalid option.                     |");
//                    System.out.println("\t\t\t\t|                                                   |");
//                    System.out.println("\t\t\t\t=====================================================");
//                }
//            }
//        }
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

    public static void handleUserPortal() {

        System.out.print("\n\t\t\t\t");

//        while (true) {
//            if (Master.getPatientMap().isEmpty()) {
//                Master.initializer();
//            }
//            int choice = PatientManagementUI.displayUserPageMenu();
//            switch (choice) {
//                case 1 ->
//                    patientManagement.createNewInstance();
//                case 2 -> {
//                    String enteredId = Input.getStringInput("\t\t\t\tEnter your Patient ID: ");
//                    if (patientManagement.patientExists(enteredId)) {
//                        patientManagement.patientWasDeleted = false;
//                        Master.setCurrentPatientId(enteredId.toUpperCase());
//                        handlePatientMenu();
//                    } else {
//                        System.out.println("\n\t\t\t\t=====================================================");
//                        System.out.println("\t\t\t\t|                    ERROR                          |");
//                        System.out.println("\t\t\t\t|                                                   |");
//                        System.out.println("\t\t\t\t|            Patient ID not found.                 |");
//                        System.out.println("\t\t\t\t|                                                   |");
//                        System.out.println("\t\t\t\t=====================================================");
//                    }
//                }
//                case 3 -> {
//                    return;
//                }
//                default -> {
//                    System.out.println("\n\t\t\t\t=====================================================");
//                    System.out.println("\t\t\t\t|                    ERROR                          |");
//                    System.out.println("\t\t\t\t|                                                   |");
//                    System.out.println("\t\t\t\t|               Invalid option.                     |");
//                    System.out.println("\t\t\t\t|                                                   |");
//                    System.out.println("\t\t\t\t=====================================================");
//                }
//            }
//        }
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
