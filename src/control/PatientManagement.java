/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.MapInterface;
import adt.LinkedHashMap;
import boundary.PatientManagementUI;
import dao.Master;
import entity.Consultation;
import entity.Patient;
import entity.Ticket;
import entity.Visit;
import utility.Input;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import utility.IDGenerator;

public class PatientManagement implements CRUD {

    private MapInterface<String, Patient> patientMap = new LinkedHashMap<>();
    private final PatientManagementUI ui;

    public boolean patientWasDeleted = false;

    public PatientManagement() {
        this.patientMap = Master.getPatientMap();
        this.ui = new PatientManagementUI();
    }

    public static Patient getCurrentPatient() {
        return Master.getPatientMap().getValue(Master.getCurrentPatientId());
    }

    public static boolean patientExists(String patientId) {
        return patientId != null && Master.getPatientMap().getValue(patientId) != null;
    }

    // ===== Core CRUD Operations =====
    @Override
    public void createNewInstance() {
        ui.displayCreateHeader();
        String id = generatePatientId(); // no ADTs used

        Patient patient = new Patient(
                id,
                Input.getStringInput("\t\t\t\tEnter Patient Name: "),
                Input.getStringInput("\t\t\t\tEnter Contact Number: "),
                Input.getStringInput("\t\t\t\tEnter Email: "),
                Input.getStringInput("\t\t\t\tEnter Gender (Male/Female): "),
                Input.getIntegerInput("\t\t\t\tEnter Age: "),
                new Date()
        );

        patientMap.put(id, patient);
        ui.displayCreateSuccess(id);
    }

    @Override
    public void readInstance() {
        String currentPatientId = Master.getCurrentPatientId();
        System.out.println("\n\t\t\t\t[DEBUG] Current Patient ID: " + currentPatientId);
        Patient patient = Master.getPatientMap().getValue(currentPatientId);
        if (patient == null) {
            ui.displayNotFound("Patient record");
            return;
        }
        if (patient.getVisitCount() == 1) {
            System.out.println("\t\t\t\tSingle visit record found.");
            PatientManagementUI.displayProfile(patient, patient.getVisits()[0]);
            return;
        }
        System.out.println("\t\t\t\tMultiple visits found. Displaying visit menu...");
        String[] visitDates = new String[patient.getVisitCount()];
        for (int i = 0; i < patient.getVisitCount(); i++) {
            visitDates[i] = patient.getDateFormat().format(patient.getVisits()[i].getQueueStart());
        }

        int selected = PatientManagementUI.displayMultipleRecordsMenu(visitDates);

        if (selected < 1 || selected > patient.getVisitCount()) {
            PatientManagementUI.displayInvalidChoice();
            return;
        }

        PatientManagementUI.displayProfile(patient, patient.getVisits()[selected - 1]);
    }

    public void updateInstance() {
        String id = Master.getCurrentPatientId();                  // no prompt
        Patient patient = patientMap.getValue(id);
        if (patient == null) {
            ui.displayNotFound("Patient");
            return;
        }

        String[] options = {"Contact Number", "Email", "Age"};
        String[] values = {
            patient.getPatient_contact(),
            patient.getPatient_email(),
            String.valueOf(patient.getAge())
        };

        while (true) {
            ui.displayUpdateMenu(options, values);
            int choice = Input.getIntegerInput("\t\t\t\tSelect option: ");
            switch (choice) {
                case 1 ->
                    values[0] = Input.getStringInput("\t\t\t\tEnter new Contact Number: ");
                case 2 ->
                    values[1] = Input.getStringInput("\t\t\t\tEnter new Email: ");
                case 3 ->
                    values[2] = Input.getStringInput("\t\t\t\tEnter new Age: ");
                case 4 -> {
                    patient.setPatient_contact(values[0]);
                    patient.setPatient_email(values[1]);
                    patient.setAge(Integer.parseInt(values[2]));
                    patientMap.put(id, patient);                   // persist
                    ui.displayUpdateSuccess();
                    return;
                }
                case 5 -> {
                    return;
                }
                default ->
                    ui.displayInvalidChoice();
            }
        }
    }

    @Override
    public void deleteInstance() {
        String id = Master.getCurrentPatientId();                  // no prompt
        Patient patient = patientMap.getValue(id);
        if (patient == null) {
            ui.displayNotFound("Patient");
            return;
        }

        System.out.println("\t\t\t\tDelete Options:");
        System.out.println("\t\t\t\t1. Delete visit record");
        System.out.println("\t\t\t\t2. Delete entire patient");

        int option = Input.getIntegerInput("\t\t\t\tEnter option (1/2): ");

        switch (option) {
            case 1 -> {
                if (patient.getVisitCount() == 0) {
                    System.out.println("\t\t\t\tNo visit records to delete.");
                    return;
                }
                System.out.println("\t\t\t\tVisit Records:");
                for (int i = 0; i < patient.getVisitCount(); i++) {
                    Visit visit = patient.getVisits()[i];
                    System.out.println("\t\t\t\t[" + (i + 1) + "] " + visit.getQueueStart());
                }
                int visitChoice = Input.getIntegerInput("\t\t\t\tEnter visit number to delete: ");
                if (visitChoice < 1 || visitChoice > patient.getVisitCount()) {
                    System.out.println("\t\t\t\tInvalid visit number.");
                    return;
                }
                Visit[] visits = patient.getVisits();
                for (int i = visitChoice - 1; i < patient.getVisitCount() - 1; i++) {
                    visits[i] = visits[i + 1];
                }
                visits[patient.getVisitCount() - 1] = null;
                patient.setVisitCount(patient.getVisitCount() - 1);
                patientMap.put(id, patient);                       // persist
                System.out.println("\t\t\t\tVisit record deleted successfully.");
            }
            case 2 -> {
                ui.displayDeleteWarning();
                String confirm = Input.getStringInput("\t\t\t\tConfirm delete patient (Y/N): ");
                if (confirm.equalsIgnoreCase("Y")) {
                    patientMap.remove(id);
                    ui.displayDeleteSuccess();
                    patientWasDeleted = true;
                    // (Optional) clear current patient in Master if you support logout:
                    // Master.setCurrentPatientId("");
                }
            }
            default ->
                System.out.println("\t\t\t\tInvalid option.");
        }
    }

    public void getTicket() {
        String patientId = Master.getCurrentPatientId();           
        Patient patient = patientMap.getValue(patientId);
        if (patient == null) {
            ui.displayNotFound("Patient");
            return;
        }

        String want = Input.getStringInput("\t\t\t\tDo you want to get a ticket now? (Y/N): ");
        if (!"Y".equalsIgnoreCase(want)) {
            System.out.println("\t\t\t\tOkay, no ticket will be issued.");
            return;
        }

        MapInterface<Integer, String> todaysDoctors = Master.getDutyScheduleMap().getValue(java.time.LocalDate.now());
        if (todaysDoctors == null || todaysDoctors.isEmpty()) {
            ui.displayNoTicketsAvailable();
            return;
        }

        freeTicketsCompletedByConsultation();

        Visit ticketedToday = findTodaysTicketedVisit(patient);
        if (ticketedToday != null && ticketedToday.getTicket() != null && !ticketedToday.getTicket().isEmpty()) {
            ui.displayExistingTicket(ticketedToday.getTicket());
            return;
        }

        String assignedTicket = assignAvailableTicketForDuty(patientId, todaysDoctors);
        if (assignedTicket == null) {
            ui.displayNoTicketsAvailable();
            return;
        }

        Visit attachTo = findTodaysUnticketedVisit(patient);
        if (attachTo != null) {
            if (attachTo.getQueueStart() == null) {
                attachTo.setQueueStart(new Date());
            }
            attachTo.setTicket(assignedTicket);
        } else {
            patient.addVisit(new Date(), null, assignedTicket);
        }

        ui.displayTicketAssigned(assignedTicket, getAssignedDoctor(assignedTicket));
    }

    /**
     * Use MapInterface keys to free tickets where a matching consultation is
     * Completed.
     */
    private void freeTicketsCompletedByConsultation() {
        MapInterface<String, Consultation> consMap = Master.getConsultationMap(); // rename if different
        if (consMap == null || consMap.isEmpty()) {
            return;
        }

        Object[] consKeys = consMap.getAllKeys();
        for (int i = 0; i < consKeys.length; i++) {
            String cKey = (String) consKeys[i];
            Consultation c = consMap.getValue(cKey);
            if (c == null) {
                continue;
            }

            String status = c.getAppointmentStatus() == null ? "" : c.getAppointmentStatus();
            if (!"Completed".equalsIgnoreCase(status)) {
                continue;
            }

            String pid = c.getPatient_Id() == null ? "" : c.getPatient_Id();
            String sid = c.getStaff_Id() == null ? "" : c.getStaff_Id();
            if (pid.isEmpty() || sid.isEmpty()) {
                continue;
            }

            // scan ticket queue by keys
            Object[] tkKeys = Master.getTicketQueue().getAllKeys();
            for (int j = 0; j < tkKeys.length; j++) {
                String tkKey = (String) tkKeys[j];
                Ticket tk = Master.getTicketQueue().getValue(tkKey);
                if (tk == null) {
                    continue;
                }

                String tPid = tk.getPatientId() == null ? "" : tk.getPatientId();
                String tSid = tk.getStaffId() == null ? "" : tk.getStaffId();

                if (!tPid.isEmpty() && tPid.equals(pid) && tSid.equals(sid)) {
                    // Clear this ticket from the patient's visit if present
                    Patient p = patientMap.getValue(pid);
                    if (p != null && p.getVisitCount() > 0) {
                        Visit[] vs = p.getVisits();
                        for (int vi = 0; vi < p.getVisitCount(); vi++) {
                            Visit v = vs[vi];
                            if (v != null && tk.getTicketNumber().equals(v.getTicket())) {
                                v.setTicket("");
                                break;
                            }
                        }
                    }
                    // Free the ticket in the queue
                    tk.setPatientId("");
                    Master.getTicketQueue().put(tkKey, tk); // update
                }
            }
        }
    }

    /**
     * Assign first free ticket whose staff is on duty today
     * (MapInterface.containsValue).
     */
    private String assignAvailableTicketForDuty(String patientId, MapInterface<Integer, String> todaysDoctors) {
        Object[] tkKeys = Master.getTicketQueue().getAllKeys();
        for (int i = 0; i < tkKeys.length; i++) {
            String tkKey = (String) tkKeys[i];
            Ticket ticket = Master.getTicketQueue().getValue(tkKey);
            if (ticket == null) {
                continue;
            }

            boolean unassigned = ticket.getPatientId() == null || ticket.getPatientId().isEmpty();
            boolean staffOnDuty = todaysDoctors.containsValue(ticket.getStaffId());

            if (unassigned && staffOnDuty) {
                ticket.setPatientId(patientId);
                Master.getTicketQueue().put(tkKey, ticket); // persist
                return ticket.getTicketNumber();
            }
        }
        return null;
    }

    /**
     * Find today's visit that already has a ticket (no ADTs).
     */
    private Visit findTodaysTicketedVisit(Patient p) {
        if (p == null || p.getVisitCount() == 0) {
            return null;
        }
        Date today = new Date();
        Visit[] vs = p.getVisits();
        for (int i = 0; i < p.getVisitCount(); i++) {
            Visit v = vs[i];
            if (v != null
                    && v.getQueueStart() != null && isSameDay(v.getQueueStart(), today)
                    && v.getTicket() != null && !v.getTicket().isEmpty()) {
                return v;
            }
        }
        return null;
    }

    /**
     * Find a today's visit without a ticket (to attach a newly issued ticket).
     */
    private Visit findTodaysUnticketedVisit(Patient p) {
        if (p == null || p.getVisitCount() == 0) {
            return null;
        }
        Date today = new Date();
        Visit[] vs = p.getVisits();
        for (int i = 0; i < p.getVisitCount(); i++) {
            Visit v = vs[i];
            if (v != null
                    && v.getQueueStart() != null && isSameDay(v.getQueueStart(), today)
                    && (v.getTicket() == null || v.getTicket().isEmpty())) {
                return v;
            }
        }
        return null;
    }

    /**
     * Resolve doctor for a ticket using MapInterface keys.
     */
    private String getAssignedDoctor(String ticketNumber) {
        Object[] tkKeys = Master.getTicketQueue().getAllKeys();
        for (int i = 0; i < tkKeys.length; i++) {
            String tkKey = (String) tkKeys[i];
            Ticket tk = Master.getTicketQueue().getValue(tkKey);
            if (tk != null && ticketNumber.equals(tk.getTicketNumber())) {
                return tk.getStaffId();
            }
        }
        return "Unknown";
    }

    private boolean isSameDay(Date a, Date b) {
        java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyyMMdd");
        return f.format(a).equals(f.format(b));
    }

   public void reportsModule() {
    while (true) {
        PatientManagementUI.displayAverageQueueMenu();
        int ch = Input.getIntegerInput("\t\t\t\tSelect an option > ");
        switch (ch) {
            case 1 -> reportTimelineByDateRange();
            case 2 -> reportTimelineByDayOfWeek();
            case 3 -> reportTimelineHeatmapAllData();
            case 4 -> { return; }
            default -> ui.displayInvalidChoice();
        }
    }
}

private void reportTimelineByDateRange() {
    try {
        java.text.SimpleDateFormat sdfD = new java.text.SimpleDateFormat("dd-MM-yyyy");
        Date start = sdfD.parse(Input.getStringInput("\t\t\t\tStart Date (dd-MM-yyyy): "));
        Date end   = sdfD.parse(Input.getStringInput("\t\t\t\tEnd Date   (dd-MM-yyyy): "));
        int[] avg = computeHourlyAvg(start, end, -1);
        PatientManagementUI.printHourlyTimeline(8, 18, avg);
    } catch (Exception e) {
        ui.displayInvalidChoice();
    }
}

private void reportTimelineByDayOfWeek() {
    System.out.println("\t\t\t\t1. Monday  2. Tuesday  3. Wednesday  4. Thursday");
    System.out.println("\t\t\t\t5. Friday  6. Saturday 7. Sunday");
    int day = Input.getIntegerInput("\t\t\t\tChoose day of week (1 - 7): ");
    if (day < 1 || day > 7) { ui.displayInvalidChoice(); return; }
    int[] avg = computeHourlyAvg(null, null, day);
    PatientManagementUI.printHourlyTimeline(8, 18, avg);
}

private void reportTimelineHeatmapAllData() {
    int[][] avg = computeHeatmap();
    PatientManagementUI.printHeatmap(8, 18, avg);
}

private int[] computeHourlyAvg(Date start, Date end, int dayOfWeek) {
    final int START = 8, END = 18, SLOTS = END - START;
    int[] sum = new int[SLOTS], cnt = new int[SLOTS];
    Iterator<Patient> it = patientMap.getIterator();
    while (it.hasNext()) {
        Patient p = it.next();
        if (p == null || p.getVisitCount() == 0) continue;
        Visit[] vs = p.getVisits();
        for (int i = 0; i < p.getVisitCount(); i++) {
            Visit v = vs[i];
            if (v == null || v.getQueueStart() == null || v.getQueueEnd() == null) continue;
            Date qs = v.getQueueStart();
            if (start != null && end != null && (qs.before(startOfDay(start)) || qs.after(endOfDay(end)))) continue;
            if (dayOfWeek != -1 && toMon1Sun7(qs) != dayOfWeek) continue;
            int h = hour(qs);
            if (h >= START && h < END) {
                int mins = (int)((v.getQueueEnd().getTime() - qs.getTime()) / 60000);
                if (mins > 0) { sum[h - START] += mins; cnt[h - START]++; }
            }
        }
    }
    int[] avg = new int[SLOTS];
    for (int s = 0; s < SLOTS; s++) avg[s] = cnt[s] > 0 ? sum[s] / cnt[s] : 0;
    return avg;
}

private int[][] computeHeatmap() {
    final int START = 8, END = 18, SLOTS = END - START, DAYS = 7;
    int[][] sum = new int[DAYS][SLOTS];
    int[][] cnt = new int[DAYS][SLOTS];
    Iterator<Patient> it = patientMap.getIterator();
    while (it.hasNext()) {
        Patient p = it.next();
        if (p == null || p.getVisitCount() == 0) continue;
        Visit[] vs = p.getVisits();
        for (int i = 0; i < p.getVisitCount(); i++) {
            Visit v = vs[i];
            if (v == null || v.getQueueStart() == null || v.getQueueEnd() == null) continue;
            int d = toMon1Sun7(v.getQueueStart()) - 1;
            int h = hour(v.getQueueStart());
            if (h >= START && h < END) {
                int mins = (int)((v.getQueueEnd().getTime() - v.getQueueStart().getTime()) / 60000);
                if (mins > 0) { sum[d][h - START] += mins; cnt[d][h - START]++; }
            }
        }
    }
    int[][] avg = new int[DAYS][SLOTS];
    for (int d = 0; d < DAYS; d++) for (int s = 0; s < SLOTS; s++) avg[d][s] = cnt[d][s] > 0 ? sum[d][s] / cnt[d][s] : 0;
    return avg;
}

private Date startOfDay(Date d) {
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(d);
    c.set(java.util.Calendar.HOUR_OF_DAY, 0);
    c.set(java.util.Calendar.MINUTE, 0);
    c.set(java.util.Calendar.SECOND, 0);
    c.set(java.util.Calendar.MILLISECOND, 0);
    return c.getTime();
}

private Date endOfDay(Date d) {
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(d);
    c.set(java.util.Calendar.HOUR_OF_DAY, 23);
    c.set(java.util.Calendar.MINUTE, 59);
    c.set(java.util.Calendar.SECOND, 59);
    c.set(java.util.Calendar.MILLISECOND, 999);
    return c.getTime();
}

private int hour(Date d) {
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(d);
    return c.get(java.util.Calendar.HOUR_OF_DAY);
}

private int toMon1Sun7(Date d) {
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(d);
    int dw = c.get(java.util.Calendar.DAY_OF_WEEK);
    return (dw == java.util.Calendar.SUNDAY) ? 7 : (dw - 1);
}
    public void patientReport() {
        ui.displayDemographicsHeader();
        int choice = Input.getIntegerInput("\t\t\t\tSelect option: ");

        switch (choice) {
            case 1 ->
                groupByGender();
            case 2 ->
                groupByAge();
            case 3 -> {
                return;
            }
            default ->
                ui.displayInvalidChoice();
        }
    }

    private void groupByGender() {
        int male = 0, female = 0;
        Iterator<Patient> iterator = patientMap.getIterator();

        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getPatient_gender().equalsIgnoreCase("Male")) {
                male++;
            } else {
                female++;
            }
        }

        ui.displayGenderDemographics(patientMap.size(), male, female);
    }

    private void groupByAge() {
        int young = 0, middle = 0, senior = 0;
        Iterator<Patient> iterator = patientMap.getIterator();

        while (iterator.hasNext()) {
            Patient p = iterator.next();
            int age = p.getAge();
            if (age <= 35) {
                young++;
            } else if (age <= 55) {
                middle++;
            } else {
                senior++;
            }
        }

        ui.displayAgeDemographics(patientMap.size(), young, middle, senior);
    }

    private String generatePatientId() {
        String lastId = patientMap.isEmpty() ? "P000000" : patientMap.getLastKey();
        return IDGenerator.generateNextID(lastId);
    }

    public void patientManagementModule() {
        int choice;
        while (true) {
            choice = ui.displayPatientManagementMenu();
            switch (choice) {
                case 1 ->
                    readInstance();
                case 2 ->
                    updateInstance();
                case 3 ->
                    deleteInstance();
                case 4 ->
                    reportsModule();
                case 5 ->
                    getTicket();
                case 6 ->
                    patientReport();
                case 7 -> {
                    return;
                }
                default ->
                    ui.displayInvalidChoice();
            }
            if (patientWasDeleted) {
                return;
            }
        }
    }
}
