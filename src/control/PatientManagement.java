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
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              NEW PATIENT REGISTRATION               ");
        System.out.println("\t\t\t\t=====================================================");
        
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
        
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                   SUCCESS                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|         Patient %s registered.%-14s|\n", id, "");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
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

            int choice = Input.getIntegerInput("\t\t\t\tSelect option: ");
            if (choice == 4) {
                patient.setPatient_contact(values[0]);
                patient.setPatient_email(values[1]);
                patient.setAge(Integer.parseInt(values[2]));
                
                System.out.println("\n\t\t\t\t=====================================================");
                System.out.println("\t\t\t\t|                   SUCCESS                         |");
                System.out.println("\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t|            Patient updated successfully.          |");
                System.out.println("\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t=====================================================");
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

        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                DELETE ACCOUNT                       ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|               WARNING                             |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|         This action cannot be undone!            |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");

        if (Input.getStringInput("\t\t\t\tConfirm delete (Y/N): ").equalsIgnoreCase("Y")) {
            Master.getPatientMap().remove(patient.getPatient_id());
            
            System.out.println("\n\t\t\t\t=====================================================");
            System.out.println("\t\t\t\t|                   SUCCESS                         |");
            System.out.println("\t\t\t\t|                                                   |");
            System.out.println("\t\t\t\t|            Patient account deleted.               |");
            System.out.println("\t\t\t\t|                                                   |");
            System.out.println("\t\t\t\t=====================================================");
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
                System.out.println("\n\t\t\t\t=====================================================");
                System.out.println("\t\t\t\t|                    NOTICE                         |");
                System.out.println("\t\t\t\t|                                                   |");
                System.out.printf("\t\t\t\t|      Patient already has ticket: %-14s |\n", ticket.getTicketNumber());
                System.out.println("\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t=====================================================");
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
                
                System.out.println("\n\t\t\t\t=====================================================");
                System.out.println("\t\t\t\t|                   SUCCESS                         |");
                System.out.println("\t\t\t\t|                                                   |");
                System.out.printf("\t\t\t\t|    Assigned ticket %s to Dr.%-16s   |\n", ticket.getTicketNumber(), ticket.getStaffId());
                System.out.println("\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t=====================================================");
                return;
            }
        }
        
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                    NOTICE                         |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|     No available tickets for today's doctors     |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
    }

    private void displayCurrentQueue(String currentTicketNumber) {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t                 CURRENT QUEUE                       ");
        System.out.println("\t\t\t\t=====================================================");
        
        Object[] allTickets = Master.getTicketQueue().getAllValues();
        int position = 1;
        boolean queueEmpty = true;
        int currentPosition = -1;
        
        for (Object obj : allTickets) {
            Ticket ticket = (Ticket) obj;
            if (!ticket.getPatientId().isEmpty()) {
                queueEmpty = false;
                Patient p = Master.getPatientMap().getValue(ticket.getPatientId());
                boolean isCurrent = ticket.getTicketNumber().equals(currentTicketNumber);
                if (isCurrent) currentPosition = position;
                
                System.out.printf("\t\t\t\t%d. %s - %s (Dr.%s)%s%n", position++, ticket.getTicketNumber(),
                    p != null ? p.getPatient_name() : "Unknown", ticket.getStaffId(), isCurrent ? " [CURRENT]" : "");
            }
        }
        
        if (queueEmpty) {
            System.out.println("\t\t\t\t|                                                   |");
            System.out.println("\t\t\t\t|            No patients in queue                   |");
            System.out.println("\t\t\t\t|                                                   |");
        } else if (currentTicketNumber != null && currentPosition != -1) {
            System.out.println("\t\t\t\t|                                                   |");
            System.out.printf("\t\t\t\t|       Your position in queue: %-18d |\n", currentPosition);
            System.out.println("\t\t\t\t|                                                   |");
        }
        System.out.println("\t\t\t\t=====================================================");
    }

    public void averageQueueTimeReport() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t            QUEUE TIME ANALYSIS REPORT              ");
        System.out.println("\t\t\t\t=====================================================");
        
        Object[] patients = patientMap.getAllValues();
        
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              SELECT REPORT TYPE                     ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. All Data                                      |");
        System.out.println("\t\t\t\t|  2. Date Range                                    |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        
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

        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              PERFORMANCE SUMMARY                    ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|  Report Scope: %-34s |\n", reportScope);
        System.out.printf("\t\t\t\t|  Patients Processed: %-27d  |\n", totalPatients);
        if (totalPatients > 0) {
            System.out.printf("\t\t\t\t|  Average Queue Time: %-5d minutes                |\n", (totalTime / totalPatients));
        }
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");

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

    public void patientReport() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t           PATIENT DEMOGRAPHICS REPORT              ");
        System.out.println("\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t|  1. Group by Gender                               |");
        System.out.println("\t\t\t\t|  2. Group by Age Range                            |");
        System.out.println("\t\t\t\t|  3. Back                                          |");
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
        
        int choice = Input.getIntegerInput("\t\t\t\tSelect option: ");
        switch (choice) {
            case 1 -> groupByGender();
            case 2 -> groupByAge();
            case 3 -> { return; }
            default -> {
                System.out.println("\n\t\t\t\t=====================================================");
                System.out.println("\t\t\t\t|                    ERROR                          |");
                System.out.println("\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t|               Invalid choice                      |");
                System.out.println("\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t=====================================================");
            }
        }
    }

    private void groupByGender() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t              GENDER DEMOGRAPHICS                    ");
        System.out.println("\t\t\t\t=====================================================");
        
        Object[] allPatients = patientMap.getAllValues();
        int totalPatients = patientMap.size();
        int maleCount = 0, femaleCount = 0;
        
        for (Object obj : allPatients) {
            Patient p = (Patient) obj;
            if (p.getPatient_gender().equalsIgnoreCase("Male")) maleCount++;
            else if (p.getPatient_gender().equalsIgnoreCase("Female")) femaleCount++;
        }
        
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

    private void groupByAge() {
        System.out.println("\n\t\t\t\t=====================================================");
        System.out.println("\t\t\t\t               AGE DEMOGRAPHICS                      ");
        System.out.println("\t\t\t\t=====================================================");
        
        Object[] allPatients = patientMap.getAllValues();
        int youngAdult = 0, middleAge = 0, senior = 0;
        int validCount = 0;
        
        for (Object obj : allPatients) {
            Patient p = (Patient) obj;
            int age = p.getAge();
            if (age > 0) {
                if (age <= 35) youngAdult++;
                else if (age <= 55) middleAge++;
                else senior++;
            }
        }
        
        System.out.println("\t\t\t\t|                                                   |");
        System.out.printf("\t\t\t\t|  Total: %-42d|\n", patientMap.size());
        System.out.printf("\t\t\t\t|  Young Adult (18-35): %-27d |\n", youngAdult);
        System.out.printf("\t\t\t\t|  Middle Age (36-55): %-28d |\n", middleAge);
        System.out.printf("\t\t\t\t|  Senior (55+): %-34d |\n", senior);
        System.out.println("\t\t\t\t|                                                   |");
        System.out.println("\t\t\t\t=====================================================");
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