/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Patient;
import utility.IDGenerator;
import java.io.*;
import java.util.*;

/**
 *
 * @author Elwin Koh Soon Yit
 */
public class PatientManagement {

    private static final String PATIENT_FILE = "patients.txt";
    private static final String DELIMITER = ",";

    public static String generateNextPatientId() {
        String lastId = "P000000";

        try {
            File file = new File(PATIENT_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.trim().length() > 0) {
                        String[] parts = line.split(DELIMITER);
                        if (parts.length > 0) {
                            String existingId = parts[0].trim();
                            if (existingId.startsWith("P") && existingId.compareTo(lastId) > 0) {
                                lastId = existingId;
                            }
                        }
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error reading patient file: " + e.getMessage());
            lastId = "P000000";
        }
        return IDGenerator.generateNextID(lastId);
    }

    public static boolean savePatient(Patient patient) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE, true))) {
            writer.write(patient.toDataString());
            writer.newLine();
            System.out.println("Patient data saved successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving patient: " + e.getMessage());
            return false;
        }
    }

    public static List<Patient> loadAllPatients() {
        List<Patient> patients = new ArrayList<>();

        try {
            File file = new File(PATIENT_FILE);
            if (!file.exists()) {
                System.out.println("Patient file does not exist. Starting with empty patient list.");
                return patients;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    String[] parts = line.split(DELIMITER);
                    if (parts.length >= 11) {
                        Patient patient = new Patient();
                        patient.setPatient_id(parts[0].trim());
                        patient.setPatient_name(parts[1].trim());
                        patient.setPatient_contact(parts[2].trim());
                        patient.setPatient_email(parts[3].trim());
                        patient.setPatient_gender(Boolean.parseBoolean(parts[4].trim()));
                        patient.setAge(Integer.parseInt(parts[5].trim()));
                        patient.setRegistration_date(parts[6].trim());
                        patient.setQueue_start_time(parts[7].trim());
                        patient.setQueue_end_time(parts[8].trim());
                        patient.setQueue_start_date(parts[9].trim());
                        patient.setQueue_end_date(parts[10].trim());

                        patients.add(patient);
                    }
                }
            }
            reader.close();

            System.out.println(String.format("Loaded %d patients from file.", patients.size()));

        } catch (IOException e) {
            System.out.println("Error loading patients from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing patient data: " + e.getMessage());
        }

        return patients;
    }

    public static Patient findPatientById(String patientId) {
        List<Patient> patients = loadAllPatients();

        for (Patient patient : patients) {
            if (patient.getPatient_id().equals(patientId)) {
                return patient;
            }
        }

        System.out.println(String.format("Patient with ID %s not found.", patientId));
        return null;
    }

    public static List<Patient> findPatientsByName(String name) {
        List<Patient> patients = loadAllPatients();
        List<Patient> matchingPatients = new ArrayList<>();

        for (Patient patient : patients) {
            if (patient.getPatient_name().toUpperCase().contains(name.toUpperCase())) {
                matchingPatients.add(patient);
            }
        }

        System.out.println(String.format("Found %d patients matching name '%s'.", matchingPatients.size(), name));
        return matchingPatients;
    }

    public static boolean updatePatient(Patient updatedPatient) {
        List<Patient> patients = loadAllPatients();
        boolean patientFound = false;

        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatient_id().equals(updatedPatient.getPatient_id())) {
                patients.set(i, updatedPatient);
                patientFound = true;
                break;
            }
        }

        if (!patientFound) {
            System.out.println("Patient not found for update.");
            return false;
        }

        return rewritePatientFile(patients);
    }

    public static boolean deletePatient(String patientId) {
        List<Patient> patients = loadAllPatients();
        boolean patientRemoved = patients.removeIf(patient -> patient.getPatient_id().equals(patientId));

        if (!patientRemoved) {
            System.out.println("Patient not found for deletion.");
            return false;
        }
        return rewritePatientFile(patients);
    }

    private static boolean rewritePatientFile(List<Patient> patients) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATIENT_FILE, false));

            for (Patient patient : patients) {
                bufferedWriter.write(patient.toDataString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Patient file updated successfully.");
            return true;

        } catch (IOException e) {
            System.out.println("Error updating patient file: " + e.getMessage());
            return false;
        }
    }

    public static void displayAllPatients() {
        List<Patient> patients = loadAllPatients();

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\n=== ALL PATIENTS ===");
        for (Patient p : patients) {
            p.displayProfile();
        }
        System.out.println(String.format("Total patients: %d", patients.size()));
    }
} 