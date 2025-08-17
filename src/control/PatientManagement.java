/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.MapInterface;
import boundary.PatientManagementUI;
import dao.Master;
import entity.*;
import utility.*;
import java.text.*;
import java.time.*;

public class PatientManagement implements CRUD {

    public boolean patientWasDeleted = false;
    MapInterface<String, Patient> patientMap = Master.getPatientMap();

    @Override
    public void createNewInstance() {
        PatientManagementUI.displayCreateHeader();
        
        String id = IDGenerator.generateNextID(Master.getPatientMap().isEmpty()
                ? "P000000" : Master.getPatientMap().getLastKey());

        Patient patient = new Patient(id,
                Input.getStringInput("\t\t\t\tEnter Patient Name: "),
                Input.getStringInput("\t\t\t\tEnter Contact Number: "),
                Input.getStringInput("\t\t\t\tEnter Email: "),
                Input.getStringInput("\t\t\t\tEnter Gender (Male/Female): "),
                Input.getIntegerInput("\t\t\t\tEnter Age: "),
                new java.util.Date(), null, null, "");

        Master.getPatientMap().put(id, patient);
        PatientManagementUI.displayCreateSuccess(id);
    }

    @Override
    public void readInstance() {
        Patient p = getCurrentPatient();
        if (p != null) p.displayProfile();
    }

    @Override
    public void updateInstance() {
        Patient patient = getCurrentPatient();
        if (patient == null) return;

        String[] options = {"Contact Number", "Email", "Age"};
        String[] values = {patient.getPatient_contact(), patient.getPatient_email(), String.valueOf(patient.getAge())};

        while (true) {
            PatientManagementUI.displayUpdateMenu(options, values);
            
            int choice = Input.getIntegerInput("\t\t\t\tSelect option: ");
            if (choice == 4) {
                patient.setPatient_contact(values[0]);
                patient.setPatient_email(values[1]);
                patient.setAge(Integer.parseInt(values[2]));
                PatientManagementUI.displayUpdateSuccess();
                return;
            }
            if (choice == 5) return;
            if (choice > 0 && choice <= 3) {
                values[choice - 1] = Input.getStringInput("\t\t\t\tEnter new " + options[choice - 1] + ": ");
            }
        }
    }

    @Override
    public void deleteInstance() {
        Patient patient = getCurrentPatient();
        if (patient == null) return;

        PatientManagementUI.displayDeleteWarning();
        
        if (Input.getStringInput("\t\t\t\tConfirm delete (Y/N): ").equalsIgnoreCase("Y")) {
            Master.getPatientMap().remove(patient.getPatient_id());
            PatientManagementUI.displayDeleteSuccess();
            patientWasDeleted = true;
        }
    }

    public void getTicket() {
        String currentPatientId = Master.getCurrentPatientId();
        if (currentPatientId == null || currentPatientId.isEmpty()) return;
        
        displayCurrentQueue(Master.getCurrentTicket());
        Object[] allTickets = Master.getTicketQueue().getAllValues();
        
        // Check if patient already has ticket
        for (Object obj : allTickets) {
            Ticket ticket = (Ticket) obj;
            if (currentPatientId.equals(ticket.getPatientId())) {
                PatientManagementUI.displayExistingTicket(ticket.getTicketNumber());
                return;
            }
        }

        MapInterface<Integer, String> todaysDoctors = Master.getDutyScheduleMap().getValue(LocalDate.now());
        Patient patient = Master.getPatientMap().getValue(currentPatientId);

        // Find available ticket
        for (Object obj : allTickets) {
            Ticket ticket = (Ticket) obj;
            if (ticket.getPatientId().isEmpty() && todaysDoctors.containsValue(ticket.getStaffId())) {
                ticket.setPatientId(currentPatientId);
                Master.setCurrentTicket(ticket.getTicketNumber());
                patient.setTicket(ticket.getTicketNumber());
                Master.getPatientMap().put(currentPatientId, patient);
                PatientManagementUI.displayTicketAssigned(ticket.getTicketNumber(), ticket.getStaffId());
                return;
            }
        }
        
        PatientManagementUI.displayNoTicketsAvailable();
    }

    private void displayCurrentQueue(String currentTicketNumber) {
        Object[] allTickets = Master.getTicketQueue().getAllValues();
        int position = 1;
        boolean queueEmpty = true;
        int currentPosition = -1;
        
        PatientManagementUI.displayQueueHeader();
        
        for (Object obj : allTickets) {
            Ticket ticket = (Ticket) obj;
            if (!ticket.getPatientId().isEmpty()) {
                queueEmpty = false;
                Patient p = Master.getPatientMap().getValue(ticket.getPatientId());
                boolean isCurrent = ticket.getTicketNumber().equals(currentTicketNumber);
                if (isCurrent) currentPosition = position;
                
                PatientManagementUI.displayQueueItem(position++, ticket.getTicketNumber(), p != null ? p.getPatient_name() : "Unknown", ticket.getStaffId());
            }
        }
        
        if (queueEmpty) {
            PatientManagementUI.displayEmptyQueue();
        } else if (currentTicketNumber != null && currentPosition != -1) {
            PatientManagementUI.displayQueuePosition(currentPosition);
        }
        PatientManagementUI.displayQueueFooter();
    }

    public void averageQueueTimeReport() {
        PatientManagementUI.displayReportHeader();
        
        Object[] patients = patientMap.getAllValues();
        PatientManagementUI.displayReportTypeMenu();
        
        int reportChoice = Input.getIntegerInput("\t\t\t\tEnter choice: ");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Calendar startCal = null, endCal = null;
        String reportScope = "All Data";
        
        if (reportChoice == 2) {
            try {
                startCal = java.util.Calendar.getInstance();
                startCal.setTime(sdf.parse(Input.getStringInput("\n\t\t\t\tEnter Start Date (dd-MM-yyyy): ")));
                endCal = java.util.Calendar.getInstance();
                endCal.setTime(sdf.parse(Input.getStringInput("\t\t\t\tEnter End Date (dd-MM-yyyy): ")));
                reportScope = sdf.format(startCal.getTime()) + " to " + sdf.format(endCal.getTime());
            } catch (Exception e) { reportChoice = 1; }
        }

        int totalPatients = 0;
        long totalTime = 0;
        int[] waitCategories = new int[4]; // 0-15, 16-30, 31-45, 45+

        for (Object obj : patients) {
            if (obj == null) continue;
            Patient p = (Patient) obj;
            if (p.getQueue_start() == null || p.getQueue_end() == null) continue;

            java.util.Calendar qs = java.util.Calendar.getInstance();
            java.util.Calendar qe = java.util.Calendar.getInstance();
            qs.setTime(p.getQueue_start());
            qe.setTime(p.getQueue_end());

            long duration = (qe.getTimeInMillis() - qs.getTimeInMillis()) / 60000;
            if (duration <= 0 || duration > 480) continue;

            // Date range filter
            if (reportChoice == 2 && startCal != null && endCal != null) {
                if (qs.before(startCal) || qs.after(endCal)) continue;
            }

            totalPatients++;
            totalTime += duration;

            if (duration <= 15) waitCategories[0]++;
            else if (duration <= 30) waitCategories[1]++;
            else if (duration <= 45) waitCategories[2]++;
            else waitCategories[3]++;
        }

        PatientManagementUI.displayPerformanceSummary(reportScope, totalPatients, 
            totalPatients > 0 ? (totalTime / totalPatients) : 0);
        
        PatientManagementUI.displayWaitTimeDistribution(waitCategories, totalPatients);
    }

    public void patientReport() {
        PatientManagementUI.displayDemographicsHeader();
        
        int choice = Input.getIntegerInput("\t\t\t\tSelect option: ");
        switch (choice) {
            case 1 -> groupByGender();
            case 2 -> groupByAge();
            case 3 -> { return; }
            default -> PatientManagementUI.displayInvalidChoice();
        }
    }

    private void groupByGender() {
        Object[] allPatients = patientMap.getAllValues();
        int totalPatients = patientMap.size();
        int maleCount = 0, femaleCount = 0;
        
        for (Object obj : allPatients) {
            Patient p = (Patient) obj;
            if (p.getPatient_gender().equalsIgnoreCase("Male")) maleCount++;
            else if (p.getPatient_gender().equalsIgnoreCase("Female")) femaleCount++;
        }
        
        PatientManagementUI.displayGenderDemographics(totalPatients, maleCount, femaleCount);
    }

    private void groupByAge() {
        Object[] allPatients = patientMap.getAllValues();
        int youngAdult = 0, middleAge = 0, senior = 0;
        
        for (Object obj : allPatients) {
            Patient p = (Patient) obj;
            int age = p.getAge();
            if (age > 0) {
                if (age <= 35) youngAdult++;
                else if (age <= 55) middleAge++;
                else senior++;
            }
        }
        
        PatientManagementUI.displayAgeDemographics(patientMap.size(), youngAdult, middleAge, senior);
    }

    public static Patient getCurrentPatient() {
        return Master.getPatientMap().getValue(Master.getCurrentPatientId());
    }

    public static boolean patientExists(String patientId) {
        return patientId != null && Master.getPatientMap().getValue(patientId) != null;
    }

    public void patientManagementModule() {
        while (true) {
            switch (PatientManagementUI.displayPatientManagementMenu()) {
                case 1 -> readInstance();
                case 2 -> updateInstance();
                case 3 -> deleteInstance();
                case 4 -> averageQueueTimeReport();
                case 5 -> getTicket();
                case 6 -> patientReport();
                case 7 -> { return; }
            }
            if (patientWasDeleted) return;
        }
    }
}