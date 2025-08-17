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
    private static PatientManagement patientManagement = new PatientManagement();

    public static void mainMenu() {
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t          WELCOME TO HOSPITAL MANAGEMENT SYSTEM     ");
        System.out.println("\t\t\t\t=====================================================");

        displayMainMenu();
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
                    System.out.println("\t\t\t\t|    Thank you for using Hospital Management       |");
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

    private static void handlePatientMenu() {
        PatientManagement pm = new PatientManagement();
        while (!pm.patientWasDeleted) {
            int choice = AllMenu.patientMenu();
            Patient current = PatientManagement.getCurrentPatient();
            if (current == null) {
                break;
            }

            switch (choice) {
                case 1 -> {
                    current.displayProfile();
                    pm.patientManagementModule();
                }
                case 2 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t               My Consultations                      ");
                    System.out.println("\t\t\t\t=====================================================");
                }
                case 3 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t                My Treatments                        ");
                    System.out.println("\t\t\t\t=====================================================");
                }
                case 4 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t               My Prescriptions                      ");
                    System.out.println("\t\t\t\t=====================================================");
                }
                case 5 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t                 My Medicines                        ");
                    System.out.println("\t\t\t\t=====================================================");
                }
                case 6 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t|               Logging out...                      |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t=====================================================");
                    Master.setCurrentPatientId("");
                    return;
                }
                default -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t|                    ERROR                          |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t|               Invalid option.                     |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t=====================================================");
                }
            }
        }
    }

    private static void handleUserPortal() {
        while (true) {
            if (Master.getPatientMap().isEmpty()) {
                Master.initializer();
            }
            int choice = PatientManagementUI.displayUserPageMenu();
            switch (choice) {
                case 1 ->
                    patientManagement.createNewInstance();
                case 2 -> {
                    String enteredId = Input.getStringInput("\t\t\t\tEnter your Patient ID: ");
                    if (patientManagement.patientExists(enteredId)) {
                        patientManagement.patientWasDeleted = false;
                        Master.setCurrentPatientId(enteredId.toUpperCase());
                        handlePatientMenu();
                    } else {
                        System.out.println("\n\t\t\t\t=====================================================");
                        System.out.println("\t\t\t\t|                    ERROR                          |");
                        System.out.println("\t\t\t\t|                                                   |");
                        System.out.println("\t\t\t\t|            Patient ID not found.                 |");
                        System.out.println("\t\t\t\t|                                                   |");
                        System.out.println("\t\t\t\t=====================================================");
                    }
                }
                case 3 -> {
                    return;
                }
                default -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t|                    ERROR                          |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t|               Invalid option.                     |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t=====================================================");
                }
            }
        }
    }

    
    public void patientPortalAfterLogin() {
        while (true) {
            int choice = AllMenu.patientMenu();
            Patient current = getCurrentPatient();
            if (current == null) {
                break;
            }
            switch (choice) {
                case 1 -> {
                    current.displayProfile();
                    patientManagement.patientManagementModule();
                    if (patientManagement.patientWasDeleted) {
                        return;
                    }
                }
                case 2 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t               My Consultations                      ");
                    System.out.println("\t\t\t\t=====================================================");
                }
                case 3 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t                My Treatments                        ");
                    System.out.println("\t\t\t\t=====================================================");
                }
                case 4 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t               My Prescriptions                      ");
                    System.out.println("\t\t\t\t=====================================================");
                }
                case 5 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t                 My Medicines                        ");
                    System.out.println("\t\t\t\t=====================================================");
                }
                case 6 -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t|               Logging out...                      |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t=====================================================");
                    Master.setCurrentPatientId("");
                    return;
                }
                default -> {
                    System.out.println("\n\t\t\t\t=====================================================");
                    System.out.println("\t\t\t\t|                    ERROR                          |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t|               Invalid option.                     |");
                    System.out.println("\t\t\t\t|                                                   |");
                    System.out.println("\t\t\t\t=====================================================");
                }
            }
        }
    }
    
    private static void handleStaffPortal() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|          Staff Portal not implemented.           |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }
}