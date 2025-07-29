/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package boundary;

import utility.Input;
import control.PatientManagement;

/**
 *
 * @author User
 */
public class menu {

    public static String currentPatientId;

    // main Menu (User,Staff,exit)
    public static void mainMenu() {
        while (true) {
            System.out.println("\n====== Main Page ======");
            System.out.println("1. User");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            int mainChoice = Input.getIntegerInput();

            switch (mainChoice) {
                case 1 ->
                    displayUserMenu();
                case 2, 3 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default ->
                    System.out.println("Invalid selection.");
            }
        }
    }

    public static void displayUserMenu() {
        while (true) {
            System.out.println("\n====== User Page ======");
            System.out.println("1. New User");
            System.out.println("2. Existing User");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            int userChoice = Input.getIntegerInput();

            switch (userChoice) {
                case 1 ->
                    PatientManagement.registerPatient();
                case 2 -> {
                    String id = Input.getStringInput("Enter Patient ID: ");
                    if (PatientManagement.patientExists(id)) {
                        PatientManagement.displayPatientById(id); // pass ID to next menu
                        currentPatientId = id;
                        displayExistingUserMenu();
                    } else {
                        System.out.println("No patient found with ID: " + id);
                    }
                }
                case 3 -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default ->
                    System.out.println("Invalid selection.");
            }
        }
    }

    public static void displayExistingUserMenu() {
        while (true) {
            System.out.println("\n====== Existing User Menu ======");
            System.out.println("1. Patient Management Module");
            System.out.println("2. Consultation Module");
            System.out.println("3. Treatment Module");
            System.out.println("4. Prescription Module");
            System.out.println("5. Medicine Module");
            System.out.println("6. Exit");
            System.out.print("Select option: ");
            int moduleChoice = Input.getIntegerInput();

            switch (moduleChoice) {
                case 1 -> {
//                    System.out.println("...");
//                }
                    int patientChoice = PatientManagementUI.displayPatientManagementMenu(currentPatientId);
                    switch (patientChoice) {
                        case 1 -> PatientManagement.displayPatientById(currentPatientId);
                        case 2 -> PatientManagement.editPatient(currentPatientId);
                        case 3 -> System.out.println("Returning to Previous Menu...");
                        default -> System.out.println("Invalid selection.");
                    }
                }

                case 2 -> {
                    int consultationChoice = ConsultationUI.consultationMenu();
                    switch (consultationChoice) {
                        case 1 ->
                            System.out.println("...");
                        case 2 ->
                            System.out.println("...");
                        case 3 -> {
                            int addType = ConsultationUI.addAppointmentMenu();
                            if (addType == 1) {
                                System.out.println("...");
                            } else if (addType == 2) {
                                System.out.println("...");
                            }
                        }
                        case 4 ->
                            System.out.println("...");
                        case 5 ->
                            System.out.println("...");
                        case 6 ->
                            System.out.println("...");
                        case 7 ->
                            System.out.println("...");
                        case 8 ->
                            System.out.println("...");
                        default ->
                            System.out.println("Invalid selection.");
                    }
                }

                case 3, 4, 5 -> {
                    System.out.println("This module is not implemented yet. Exiting...");
                    System.exit(0);
                }
                case 6 -> {
                    System.out.println("Returning to User Page...");
                    return;
                }
                default ->
                    System.out.println("Invalid selection.");
            }
        }
    }

}
