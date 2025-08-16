/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedHashMap;
import adt.MapInterface;
import dao.Master;
import entity.Patient;
import utility.IDGenerator;
import java.io.*;
import java.util.*;
import utility.Input;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author Elwin Koh Soon Yit
 */
public class PatientManagement {
 private static Date currentDate = new Date();

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static String generateNextPatientId() {
        String lastId = "P000000";
        if (!Master.getPatientMap().isEmpty()) {
            String lastKey = Master.getPatientMap().getLastKey();
            if (lastKey != null && lastKey.startsWith("P")) {
                lastId = lastKey;
            }
        }
        return IDGenerator.generateNextID(lastId);
    }

    public static void registerPatient() {
        String id = generateNextPatientId();
        System.out.println("Your ID is:" + id);
        String name = Input.getStringInput("Enter Patient Name: ");
        String contact = Input.getStringInput("Enter Contact Number: ");
        String email = Input.getStringInput("Enter Email: ");
        String gender = Input.getStringInput("Enter Gender (Male/Female): ");
        int age = Input.getIntegerInput("Enter Age: ");
        Patient patient = new Patient();
        try {
            String regDateStr = fullDateFormat.format(currentDate);
            Date regDate = fullDateFormat.parse(regDateStr);
            patient.setRegistration_date(regDate);
        } catch (ParseException e) {
            System.out.println(" Failed to parse registration date.");
            return;
        }
        patient.setPatient_id(id);
        patient.setPatient_name(name);
        patient.setPatient_contact(contact);
        patient.setPatient_email(email);
        patient.setPatient_gender(gender);
        patient.setAge(age);

        Master.getPatientMap().put(id, patient);
        System.out.println("\n Patient registered successfully.\n");
    }

    public static void editPatient(String id) {
        Patient patient = Master.getPatientMap().getValue(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String tempContact = patient.getPatient_contact();
        String tempEmail = patient.getPatient_email();
        int tempAge = patient.getAge();

        while (true) {
            System.out.println("\n------ Edit Patient Information ------");
            System.out.println("Current Contact: " + tempContact);
            System.out.println("Current Email  : " + tempEmail);
            System.out.println("Current Age    : " + tempAge);
            System.out.println("--------------------------------------");
            System.out.println("1. Edit Contact Number");
            System.out.println("2. Edit Email");
            System.out.println("3. Edit Age");
            System.out.println("4. Save & Return");
            System.out.println("5. Cancel & Return");
            int choice = Input.getIntegerInput("Select an option > ");

            switch (choice) {
                case 1 ->
                    tempContact = Input.getStringInput("Enter new contact number: ");
                case 2 ->
                    tempEmail = Input.getStringInput("Enter new email: ");
                case 3 ->
                    tempAge = Input.getIntegerInput("Enter new age: ");
                case 4 -> {
                    String confirm = Input.getStringInput("Are you sure you want to save changes? (Y/N): ");
                    if (confirm.equalsIgnoreCase("Y")) {
                        patient.setPatient_contact(tempContact);
                        patient.setPatient_email(tempEmail);
                        patient.setAge(tempAge);
                        Master.getPatientMap().put(id, patient);
                        System.out.println("Patient information updated.");
                    } else {
                        System.out.println("Changes discarded.");
                    }
                    return;
                }
                case 5 -> {
                    System.out.println("Edit cancelled.");
                    return;
                }
                default ->
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static MapInterface<String, Patient> getPatientMap() {
        return Master.getPatientMap();
    }

    public static boolean patientExists(String id) {
        return Master.getPatientMap().containsKey(id);
    }

    public static void displayPatientById(String id) {
        Patient p = Master.getPatientMap().getValue(id);
        if (p != null) {
            p.displayProfile();
        }
    }

    public static void deletePatient(String id) {
        Patient patient = Master.getPatientMap().getValue(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        while (true) {
            System.out.println("Would you like to delete current patient");
            patient.displayProfile();
            System.out.println("1. Delete current Patient");
            System.out.println("2. Exit");
            int choice = Input.getIntegerInput("Select an option > ");
            switch (choice) {
                case 1 -> {
                        String confirm = Input.getStringInput("Are you sure you want to Delete Patient:" + id + "? (Y/N): ");
                        if (confirm.equalsIgnoreCase("Y")) {
                            Master.getPatientMap().remove(id);
                            System.out.println("Patient "+id+"deleted succesfully.");
                        } else {
                            System.out.println("Patient"+id+"deleted unsuccessful");
                        }
                        return;
                    }
                case 2 -> {
                    System.out.println("Edit cancelled.");
                    return;
                }
                default ->
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
