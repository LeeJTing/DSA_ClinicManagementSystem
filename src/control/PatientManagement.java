/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.MapInterface;
import adt.ChainBucket;
import boundary.PatientManagementUI;
import dao.Master;
import entity.Patient;
import entity.Staff;
import entity.Ticket;
import entity.Visit;
import java.text.SimpleDateFormat;
import utility.IDGenerator;
import java.util.Date;
import java.util.Iterator;

public class PatientManagement implements CRUD {

    private MapInterface<String, Patient> patientMap = new ChainBucket<>();
    private MapInterface<Integer, Patient> historyPatient = new ChainBucket<>();
    private MapInterface<Integer, String> historyAction = new ChainBucket<>();
    private int historyKey = 1;
    public boolean patientWasDeleted = false;
    private final PatientManagementUI ui;

    public PatientManagement() {    // Constructor - load patient map and UI
        this.patientMap = Master.getPatientMap();
        this.ui = new PatientManagementUI();
    }

    public static Patient getCurrentPatient() {    // Get current logged-in patient
        return Master.getPatientMap().getValue(Master.getCurrentPatientId());
    }

    public static boolean patientExists(String patientId) {// Check if patient exists
        return patientId != null && Master.getPatientMap().getValue(patientId) != null;
    }

    public void PatientModule() {// Main module for patient login/register
        int choice = ui.displayUserPageMenu();
        switch (choice) {
            case 1:
                createNewInstance();// Register new patient
                break;
            case 2:// Login existing patient
                String enteredId = ui.promptLoginId();
                if (patientExists(enteredId)) {
                    patientWasDeleted = false;
                    Master.setCurrentPatientId(enteredId.toUpperCase());
                    patientManagementModule();
                } else {
                    ui.patientNotFound();
                }
            case 3: // Doctor login required for patient report
                ui.confirmLoginStaff(); // UI prompt before login

                DoctorManagement doctorMgmt = new DoctorManagement();
                boolean doctorLoggedIn = doctorMgmt.login(); // Attempt doctor login

                if (doctorLoggedIn) {
                    ui.displayDoctorLoginSuccess();
                    boolean keepViewing = true;
                    while (keepViewing) {
                        patientReport(); // Show patient report
                        keepViewing = ui.askViewReportsAgain(); 
                    }
                } else {
                    ui.displayDoctorLoginFailed();
                }
                break;

            default:
                return;
        }
    }

    public void patientManagementModule() {// Patient management menu (CRUD, reports, undo, logout)
        int choice;
        while (true) {
            choice = ui.displayPatientManagementMenu();
            switch (choice) {
                case 1 -> {
                    if (revertDelete()) {//Check if the patient had deleted their own account or not if yes then redirect to the undo function
                        break;
                    }
                    readInstance();
                }
                case 2 -> {
                    if (revertDelete()) {
                        break;
                    }
                    updateInstance();
                }
                case 3 -> {
                    if (revertDelete()) {
                        break;
                    }
                    deleteInstance();
                }
                case 4 -> {
                    waitTimeReportsModule();
                }
                case 5 -> {
                    undo();
                }
                case 6 -> {
                    Master.setCurrentPatientId("");
                    return;
                }
                default ->
                    ui.displayInvalidChoice();
            }
        }
    }

    public void offlinePatientModule() {    // Offline patient module - let patient to get ticket
        MapInterface<String, Ticket> ticketQueue = Master.getTicketQueue();
        displayPublicQueueForOffline(ticketQueue);
        int choice = ui.displayGetTicketMenu();
        switch (choice) {
            case 1:
                getTicket();// Get new ticket
                break;
            case 2:
                return;
            default:
                ui.displayInvalidChoice();
        }

    }

    public void waitTimeReportsModule() {//wait time report module
        while (true) {
            PatientManagementUI.displayAverageQueueMenu();
            int ch = ui.promptQueueReportMenuOption();
            switch (ch) {
                case 1 ->
                    reportTimelineByDateRange(); // Report by date range
                case 2 ->
                    reportTimelineByDayOfWeek();// Report by day of week
                case 3 ->
                    reportTimelineHeatmapAllData();// Heatmap report
                case 4 -> {
                    return;
                }
                default ->
                    ui.displayInvalidChoice();
            }
        }
    }

    public void patientReport() {//patient report module
        Object[] allVals = patientMap.getAllValues();
        PatientManagementUI.displayAllPatientsTable(allVals);
        ui.displayDemographicsHeader();
        int choice = ui.promptDemographicsMenuOption();
        switch (choice) {
            case 1 ->
                groupByGender();// Group by gender
            case 2 ->
                groupByAge();// Group by age
            case 3 -> {
                displayVisitFrequencyReport();// Visit frequency
            }
            case 4 -> {
                return;
            }
            default ->
                ui.displayInvalidChoice();
        }
    }

    @Override
    public void createNewInstance() {    // Create new patient record
        ui.displayCreateHeader();
        String id = generatePatientId();// Generate ID for patient
        Patient patient = new Patient(
                id,
                ui.promptPatientName(),
                ui.promptPatientContact(),
                ui.promptPatientEmail(),
                ui.promptPatientGender(),
                ui.promptPatientAge(),
                new Date()
        );
        patientMap.put(id, patient);
        saveHistory(patient, "Create");// Save history for undo
        ui.displayCreateSuccess(id);
    }

    @Override
    public void readInstance() {    // Read/view patient details
        String currentPatientId = Master.getCurrentPatientId();
        Patient patient = Master.getPatientMap().getValue(currentPatientId);
        String[] visitDates = new String[patient.getVisitCount()];// Handle single/multiple visits
        if (patient == null) {
            ui.displayNotFound("Patient record");
            return;
        }

        if (patient.getVisitCount() == 0) {
            PatientManagementUI.displayProfile(patient, patient.getVisits()[0]);
            return;
        }

        if (patient.getVisitCount() == 1) {
            ui.displaySingleVisitFound();
            PatientManagementUI.displayProfile(patient, patient.getVisits()[0]);
            return;
        }
        ui.displayMultipleVisitsFound();
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

    public void updateInstance() {// Update patient details
        String id = Master.getCurrentPatientId();
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
        while (true) {//prompt to let user to choose what info to update if done save or cancel if they dont want
            ui.displayUpdateMenu(options, values);
            int choice = ui.promptUpdateSelectOption();
            switch (choice) {
                case 1 ->
                    values[0] = ui.promptNewContactNumber();
                case 2 ->
                    values[1] = ui.promptNewEmail();
                case 3 ->
                    values[2] = ui.promptNewAge();
                case 4 -> {
                    saveHistory(patient, "Update");
                    patient.setPatient_contact(values[0]);
                    patient.setPatient_email(values[1]);
                    patient.setAge(Integer.parseInt(values[2]));
                    patientMap.put(id, patient);
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
    public void deleteInstance() {    // Delete patient or patient visit
        String id = Master.getCurrentPatientId();
        Patient patient = patientMap.getValue(id);
        if (patient == null) {
            ui.displayNotFound("Patient");
            return;
        }
        ui.displayDeleteOptions();
        int option = ui.promptDeleteOption();
        switch (option) {
            case 1 -> {
                if (patient.getVisitCount() == 0) {
                    ui.displayNoVisitRecordsToDelete();//if no patient record then display no record and return
                    return;
                }
                ui.displayVisitList(patient);//if there is record let user to  delete it
                int visitChoice = ui.promptVisitNumberToDelete();
                if (visitChoice < 1 || visitChoice > patient.getVisitCount()) {
                    ui.displayInvalidChoice();
                    return;
                }
                saveHistory(patient, "Update");//update undo opereation to allow revert
                Visit[] visits = patient.getVisits();
                for (int i = visitChoice - 1; i < patient.getVisitCount() - 1; i++) {
                    visits[i] = visits[i + 1];
                }
                visits[patient.getVisitCount() - 1] = null;
                patient.setVisitCount(patient.getVisitCount() - 1);
                patientMap.put(id, patient);
                ui.displayVisitDeleteSuccess();
            }
            case 2 -> {// let user to decide delete their account or not
                ui.displayDeleteWarning();
                boolean confirm = ui.confirmDeletePatient();
                if (confirm) {
                    saveHistory(patient, "Delete");//save to undo to allow revert
                    String keyDelete = patientMap.getKey(patient);
                    String keyToRemove = (keyDelete != null && !keyDelete.isEmpty()) ? keyDelete : id;

                    patientMap.remove(keyToRemove);
                    ui.displayDeleteSuccess();
                    patientWasDeleted = true;
                }
            }
            default ->
                ui.displayInvalidChoice();
        }
    }

    public void undo() {    // Undo last action (Create/Update/Delete)
        if (historyAction.isEmpty()) {
            ui.displayNoUndoHistory();
            return;
        }
        String lastAction = historyAction.removeLast();
        Patient snapshot = historyPatient.removeLast();
        if (snapshot == null || snapshot.getPatient_id() == null || snapshot.getPatient_id().isEmpty()) {
            ui.displayUndoFailed();
            return;
        }
        String id = snapshot.getPatient_id();
        if (lastAction != null && lastAction.startsWith("TicketAssign:")) {
            String ticketNo = lastAction.substring("TicketAssign:".length());
            patientMap.put(id, snapshot);
            ui.displayUndoDone("update", id);
            return;
        }
        switch (lastAction) {
            case "Create" -> {
                if (patientMap.containsKey(id)) {
                    patientMap.remove(id);
                    ui.displayUndoDone("create", id);
                } else {
                    ui.displayUndoNoEffect("create");
                }
            }
            case "Delete" -> {
                patientMap.put(id, snapshot);
                ui.displayUndoDone("delete", id);
            }
            case "Update" -> {
                patientMap.put(id, snapshot);
                ui.displayUndoDone("update", id);
            }
            default ->
                ui.displayUndoUnknown();
        }
    }

    public void getTicket() {    // Assign new ticket to patient, create new patient before status changed to complete after the patient id has been set at consultation 
        MapInterface<String, Ticket> ticketQueue = Master.getTicketQueue();
        Object[] ticketKeys = ticketQueue.getAllKeys();
        Date currentDate = new Date();

        for (int i = 0; i < ticketKeys.length; i++) {
            Ticket front = ticketQueue.getFront();
            if (front == null) {
                break;
            }
            if ("complete".equals(front.getTicketStatus())) {
                Ticket completedTicket = ticketQueue.removeFirst();
                recordVisitFromTicket(completedTicket);
                ticketQueue.put(completedTicket.getTicketNumber(), completedTicket);
            } else {
                break;
            }
        }

        Ticket assignedTicket = null;
        for (int i = 0; i < ticketKeys.length; i++) {
            String ticketKey = (String) ticketKeys[i];
            Ticket ticket = ticketQueue.getValue(ticketKey);

            if (ticket != null && (ticket.getTicketStatus() == null || ticket.getTicketStatus().isEmpty())) {
                ticket.setQueueStart(currentDate);
                ticket.setTicketStatus("queue");
                ticketQueue.put(ticketKey, ticket);
                Master.setCurrentTicket(ticket.getTicketNumber());
                assignedTicket = ticket;
                break;
            }
        }

        if (assignedTicket == null) {
            ui.displayNoTicketsAvailable();//if all ticket are fully assigned then display no ticket message
            return;
        }
        displayCurrentQueue(ticketQueue);
        ui.displayTicketAssigned(assignedTicket.getTicketNumber());//display ticket queue and ticket assigned to patient, including current waiting
    }

    private void displayCurrentQueue(MapInterface<String, Ticket> ticketQueue) {//operation to display current queue
        MapInterface<String, Ticket> queuedTickets = new ChainBucket<>();
        Object[] ticketKeys = ticketQueue.getAllKeys();
        for (int i = 0; i < ticketKeys.length; i++) {
            String ticketKey = (String) ticketKeys[i];
            Ticket ticket = ticketQueue.getValue(ticketKey);
            if (ticket != null && "queue".equals(ticket.getTicketStatus())) {
                queuedTickets.put(ticketKey, ticket);
            }
        }
        ui.displayQueueHeader();
        if (queuedTickets.isEmpty()) {
            System.out.println("\t\t\t\t|                                                         |");
            System.out.println("\t\t\t\t|                                                         |");
            System.out.println("\t\t\t\t|                                                         |");
            ui.displayQueueFooter();
            return;
        }
        String frontTicketKey = queuedTickets.getFrontKey();
        String lastTicketKey = queuedTickets.getLastKey();

        String currentAssignedTicket = Master.getCurrentTicket();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Object[] allTicketKeys = queuedTickets.getAllKeys();
        int position = 1;

        for (int i = 0; i < allTicketKeys.length; i++) {//loop to check the patient with all patient key to get number of bucket and check which one is the last to get current latest waiting patient
            String ticketKey = (String) allTicketKeys[i];
            Ticket ticket = queuedTickets.getValue(ticketKey);
            String ticketNo = ticket.getTicketNumber();
            String timeStarted = "N/A";
            String timeWaited = "N/A";
            String countLabel = String.valueOf(position);
            String youIndicator = "";
            boolean isCurrentServing = ticketKey.equals(frontTicketKey);
            boolean isMostRecent = ticketKey.equals(lastTicketKey);
            boolean isUsersAssignedTicket = ticketNo.equals(currentAssignedTicket);

            if (isCurrentServing) {
                youIndicator = "[Current]";
            } else if (isMostRecent && isUsersAssignedTicket) {
                youIndicator = "[You]";
            }

            if (ticket.getQueueStart() != null) {//use queue start and current time to display all the currently queued patient waiting time
                timeStarted = timeFormat.format(ticket.getQueueStart());
                long waitedMillis = new Date().getTime() - ticket.getQueueStart().getTime();
                long waitedMins = waitedMillis / (60 * 1000);
                timeWaited = String.valueOf(waitedMins);
            }
            ui.displayQueueRow(youIndicator, countLabel, ticketNo, timeStarted, timeWaited);
            position++;
        }

        ui.displayQueueFooter();
    }

    private void recordVisitFromTicket(Ticket ticket) {//record visit if the patient id has been verified by the consultation and is existing patient and if not then create a new patient to record down
        if (ticket == null || ticket.getPatientID() == null || ticket.getPatientID().isEmpty()) {
            return;
        }

        String patientId = ticket.getPatientID();
        Patient patient = patientMap.getValue(patientId);

        if (patient == null) {
            patient = createNewOfflinePatient(patientId);
            if (patient == null) {
                System.out.println("Failed to create patient record for: " + patientId);
                return;
            }
        }
        patient.addVisit(ticket.getQueueStart(), ticket.getQueueEnd());
        patientMap.put(patientId, patient);
        System.out.println("Visit recorded for patient: " + patientId);
    }

    private Patient createNewOfflinePatient(String patientId) {//create new patient for offline
        try {
            Patient patient = new Patient();
            patient.setPatient_id(patientId);
            patient.setPatient_name("Offline Patient " + patientId);
            patient.setPatient_contact("N/A");
            patient.setPatient_email("N/A");
            patient.setPatient_gender("Unknown");
            patient.setAge(0);
            patient.setRegistration_date(new Date());

            return patient;
        } catch (Exception e) {
            System.out.println("Error creating offline patient: " + e.getMessage());
            return null;
        }
    }

    private void reportTimelineByDateRange() {//find wait time distribution by start date and end date
        try {
            String sStart = ui.promptStartDate();
            String sEnd = ui.promptEndDate();
            java.text.SimpleDateFormat sdfD = new java.text.SimpleDateFormat("dd-MM-yyyy");
            Date start = sdfD.parse(sStart);
            Date end = sdfD.parse(sEnd);
            int[] avg = computeHourlyAvg(start, end, -1);
            PatientManagementUI.printHourlyTimeline(9, 18, avg);
        } catch (Exception e) {
            ui.displayInvalidChoice();
        }
    }

    private void reportTimelineByDayOfWeek() {//find wait time through day of week with calendar and find with date same with which day and find patient on that day to calculate
        ui.displayDayOfWeekMenu();
        int day = ui.promptDayOfWeek();
        if (day < 1 || day > 7) {
            ui.displayInvalidChoice();
            return;
        }
        int[] avg = computeHourlyAvg(null, null, day);
        PatientManagementUI.printHourlyTimeline(9, 18, avg);
    }

    private void reportTimelineHeatmapAllData() {//generate the whole average waiting time table with x-axis day y-axis time
        int[][] avg = computeHeatmap();
        PatientManagementUI.printHeatmap(9, 18, avg);
    }

    private int[] computeHourlyAvg(Date start, Date end, int dayOfWeek) {//operation to compute the hourly average wait time
        final int START = 9, END = 18, SLOTS = END - START;
        int[] sum = new int[SLOTS], cnt = new int[SLOTS];
        Iterator<Patient> it = patientMap.getIterator();
        while (it.hasNext()) {
            Patient p = it.next();
            if (p == null || p.getVisitCount() == 0) {
                continue;
            }
            Visit[] vs = p.getVisits();
            for (int i = 0; i < p.getVisitCount(); i++) {
                Visit v = vs[i];
                if (v == null || v.getQueueStart() == null || v.getQueueEnd() == null) {
                    continue;
                }
                Date qs = v.getQueueStart();
                if (start != null && end != null && (qs.before(startOfDay(start)) || qs.after(endOfDay(end)))) {
                    continue;
                }
                if (dayOfWeek != -1 && toMon1Sun7(qs) != dayOfWeek) {
                    continue;
                }
                int h = hour(qs);
                if (h >= START && h < END) {
                    int mins = (int) ((v.getQueueEnd().getTime() - qs.getTime()) / 60000);
                    if (mins > 0) {
                        sum[h - START] += mins;
                        cnt[h - START]++;
                    }
                }
            }
        }
        int[] avg = new int[SLOTS];
        for (int s = 0; s < SLOTS; s++) {
            avg[s] = cnt[s] > 0 ? sum[s] / cnt[s] : 0;
        }
        return avg;
    }

    private int[][] computeHeatmap() {//operation to do the whole table
        final int START = 9, END = 18, SLOTS = END - START, DAYS = 7;
        int[][] sum = new int[DAYS][SLOTS];
        int[][] cnt = new int[DAYS][SLOTS];
        Iterator<Patient> it = patientMap.getIterator();
        while (it.hasNext()) {
            Patient p = it.next();
            if (p == null || p.getVisitCount() == 0) {
                continue;
            }
            Visit[] vs = p.getVisits();
            for (int i = 0; i < p.getVisitCount(); i++) {
                Visit v = vs[i];
                if (v == null || v.getQueueStart() == null || v.getQueueEnd() == null) {
                    continue;
                }
                int d = toMon1Sun7(v.getQueueStart()) - 1;
                int h = hour(v.getQueueStart());
                if (h >= START && h < END) {
                    int mins = (int) ((v.getQueueEnd().getTime() - v.getQueueStart().getTime()) / 60000);
                    if (mins > 0) {
                        sum[d][h - START] += mins;
                        cnt[d][h - START]++;
                    }
                }
            }
        }
        int[][] avg = new int[DAYS][SLOTS];
        for (int d = 0; d < DAYS; d++) {
            for (int s = 0; s < SLOTS; s++) {
                avg[d][s] = cnt[d][s] > 0 ? sum[d][s] / cnt[d][s] : 0;
            }
        }
        return avg;
    }

    private Date startOfDay(Date d) {//compute the start date
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(d);
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        c.set(java.util.Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    private Date endOfDay(Date d) {//compute the end date
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(d);
        c.set(java.util.Calendar.HOUR_OF_DAY, 23);
        c.set(java.util.Calendar.MINUTE, 59);
        c.set(java.util.Calendar.SECOND, 59);
        c.set(java.util.Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    private int hour(Date d) {//get time of the day 
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(d);
        return c.get(java.util.Calendar.HOUR_OF_DAY);
    }

    private int toMon1Sun7(Date d) {//get day of week through date
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(d);
        int dw = c.get(java.util.Calendar.DAY_OF_WEEK);
        return (dw == java.util.Calendar.SUNDAY) ? 7 : (dw - 1);
    }

    private void groupByGender() {//group patient by gender
        String[] maleIds = new String[4];
        int maleCount = 0;
        String[] femaleIds = new String[4];
        int femaleCount = 0;

        Iterator<Patient> it = patientMap.getIterator();
        while (it.hasNext()) {
            Patient p = it.next();
            if (p == null) {
                continue;
            }

            if ("Male".equalsIgnoreCase(p.getPatient_gender())) {
                if (maleCount == maleIds.length) {
                    maleIds = grow(maleIds);
                }
                maleIds[maleCount++] = p.getPatient_id();
            } else {
                if (femaleCount == femaleIds.length) {
                    femaleIds = grow(femaleIds);
                }
                femaleIds[femaleCount++] = p.getPatient_id();
            }
        }

        ui.displayGenderGrid(maleIds, maleCount, femaleIds, femaleCount);
    }

    private String[] grow(String[] a) {//copy the array a into b so operations wont interrupt a
        String[] b = new String[a.length * 2];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    private void groupByAge() {//group age of patients
        String[] young = new String[4];
        int yc = 0;
        String[] middle = new String[4];
        int mc = 0;
        String[] senior = new String[4];
        int sc = 0;

        Iterator<Patient> it = patientMap.getIterator();
        while (it.hasNext()) {
            Patient p = it.next();
            if (p == null) {
                continue;
            }
            int age = p.getAge();

            if (age <= 30) {
                if (yc == young.length) {
                    String[] tmp = new String[young.length == 0 ? 1 : young.length * 2];
                    for (int i = 0; i < young.length; i++) {
                        tmp[i] = young[i];
                    }
                    young = tmp;
                }
                young[yc++] = p.getPatient_id();
            } else if (age <= 50) {
                if (mc == middle.length) {
                    String[] tmp = new String[middle.length == 0 ? 1 : middle.length * 2];
                    for (int i = 0; i < middle.length; i++) {
                        tmp[i] = middle[i];
                    }
                    middle = tmp;
                }
                middle[mc++] = p.getPatient_id();
            } else {
                if (sc == senior.length) {
                    String[] tmp = new String[senior.length == 0 ? 1 : senior.length * 2];
                    for (int i = 0; i < senior.length; i++) {
                        tmp[i] = senior[i];
                    }
                    senior = tmp;
                }
                senior[sc++] = p.getPatient_id();
            }
        }
        String[] youngIds = new String[yc];
        for (int i = 0; i < yc; i++) {
            youngIds[i] = young[i];
        }
        String[] middleIds = new String[mc];
        for (int i = 0; i < mc; i++) {
            middleIds[i] = middle[i];
        }
        String[] seniorIds = new String[sc];
        for (int i = 0; i < sc; i++) {
            seniorIds[i] = senior[i];
        }
        int total = patientMap.size();
        ui.displayAgeDemographicsTable(youngIds, middleIds, seniorIds, total);
    }

    private String generatePatientId() {//generate patient id 
        String lastId = patientMap.isEmpty() ? "P000000" : patientMap.getLastKey();
        return IDGenerator.generateNextID(lastId);
    }

    private void saveHistory(Patient patient, String action) {//save patient details to allow revert and save when patient didnt do undo
        historyPatient.put(historyKey, copyPatient(patient));
        historyAction.put(historyKey, action);
        historyKey++;
    }

    private Patient copyPatient(Patient src) {//clone a petient to make undo
        if (src == null) {
            return null;
        }
        Patient patientCopy = new Patient();
        patientCopy.setPatient_id(src.getPatient_id());
        patientCopy.setPatient_name(src.getPatient_name());
        patientCopy.setPatient_contact(src.getPatient_contact());
        patientCopy.setPatient_email(src.getPatient_email());
        patientCopy.setPatient_gender(src.getPatient_gender());
        patientCopy.setAge(src.getAge());
        Date reg = src.getRegistration_date();
        if (reg != null) {
            patientCopy.setRegistration_date(new Date(reg.getTime()));
        }
        Visit[] vs = src.getVisits();
        int count = src.getVisitCount();
        if (vs != null && count > 0) {
            for (int i = 0; i < count; i++) {
                Visit v = vs[i];
                if (v == null) {
                    continue;
                }
                Date qs = v.getQueueStart() == null ? null : new Date(v.getQueueStart().getTime());
                Date qe = v.getQueueEnd() == null ? null : new Date(v.getQueueEnd().getTime());
                patientCopy.addVisit(qs, qe);
            }
        }
        return patientCopy;
    }

    public MapInterface<String, Patient>[] groupPatientsByVisitFrequency() {//group patient by visit counts,clone one patient with sorting from last and reomelast to display
        MapInterface<String, Patient> work = new ChainBucket<>();
        Iterator<Patient> it = patientMap.getIterator();
        while (it.hasNext()) {
            Patient p = it.next();
            long t = latestVisitMillis(p);
            String k = padMillis(t) + "|" + p.getPatient_id();
            work.put(k, p);
        }
        work.sorting();
        MapInterface<String, Patient> newPatients = new ChainBucket<>();
        MapInterface<String, Patient> returningPatients = new ChainBucket<>();
        while (!work.isEmpty()) {
            Patient p = work.getLast();
            if (p == null) {
                break;
            }
            if (p.getVisitCount() < 2) {
                newPatients.put(p.getPatient_id(), p);
            } else {
                returningPatients.put(p.getPatient_id(), p);
            }
            work.removeLast();
        }
        MapInterface<String, Patient>[] result = new MapInterface[2];
        result[0] = newPatients;
        result[1] = returningPatients;
        return result;
    }

    private long latestVisitMillis(Patient p) {
        if (p == null || p.getVisitCount() <= 0) {
            return 0L;
        }
        long best = 0L;
        Visit[] vs = p.getVisits();
        int n = p.getVisitCount();
        for (int i = 0; i < n; i++) {
            Visit v = vs[i];
            if (v == null) {
                continue;
            }
            Date d = (v.getQueueEnd() != null) ? v.getQueueEnd() : v.getQueueStart();
            if (d != null && d.getTime() > best) {
                best = d.getTime();
            }
        }
        return best;
    }

    private String padMillis(long t) {
        return String.format("%013d", Math.max(0, t));
    }

    private String fmtLatest(Patient p, long t) {
        if (t <= 0) {
            return "N/A";
        }
        java.text.SimpleDateFormat df = (p.getDateFormat() != null)
                ? p.getDateFormat()
                : new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm");
        return df.format(new java.util.Date(t));
    }

    public void displayVisitFrequencyReport() {//display the patient visit to classify them is returned patient or new patient
        int total = patientMap.size();
        int newCnt = 0, retCnt = 0;
        Iterator<Patient> itCnt = patientMap.getIterator();
        while (itCnt.hasNext()) {
            Patient p = itCnt.next();
            if (p != null) {
                if (p.getVisitCount() < 2) {
                    newCnt++;
                } else {
                    retCnt++;
                }
            }
        }
        ui.displayVisitFreqReportHeader(total, newCnt, retCnt);
        ui.displayVisitFreqTableHeader();
        MapInterface<String, Patient> work = new ChainBucket<>();
        Iterator<Patient> it = patientMap.getIterator();
        while (it.hasNext()) {
            Patient p = it.next();
            if (p == null) {
                continue;
            }
            long t = latestVisitMillis(p);
            String k = padMillis(t) + "|" + p.getPatient_id();
            work.put(k, p);
        }
        work.sorting();
        while (!work.isEmpty()) {
            Patient p = work.getLast();
            if (p == null) {
                break;
            }
            String type = (p.getVisitCount() < 2) ? "NEW" : "RETURN";
            long t = latestVisitMillis(p);
            ui.displayVisitFreqTableRow(type, p.getPatient_id(), p.getPatient_name(), p.getVisitCount(), fmtLatest(p, t));
            work.removeLast();
        }

        ui.displayVisitFreqTableFooter();
    }

    private boolean revertDelete() {//revert to undo the account deletion 
        String currentId = Master.getCurrentPatientId();
        if (currentId == null || currentId.isEmpty() || patientMap.getValue(currentId) == null) {
            PatientManagementUI.displayDeletedAccountNotice();
            boolean yes = PatientManagementUI.promptUndoDeletedAccount();
            if (yes) {
                undo();
            } else {
                Master.setCurrentPatientId("");
                PatientManagementUI.displayAccessCancelled();
            }
            return true;
        }
        return false;
    }

    private void displayPublicQueueForOffline(MapInterface<String, Ticket> ticketQueue) {
        if (ticketQueue == null) {
            return;
        }

        PatientManagementUI.displayQueueHeader();
        MapInterface<String, Ticket> queued = new ChainBucket<>();
        Object[] keys = ticketQueue.getAllKeys();
        for (int i = 0; i < keys.length; i++) {
            String k = (String) keys[i];
            Ticket t = ticketQueue.getValue(k);
            if (t != null && "queue".equals(t.getTicketStatus())) {
                queued.put(k, t);
            }
        }

        if (queued.isEmpty()) {
            ui.displayNoTicketsInQueueRow();
            ui.displayQueueFooter();
            return;
        }
        String frontKey = queued.getFrontKey();
        SimpleDateFormat hhmm = new SimpleDateFormat("HH:mm");
        Object[] qKeys = queued.getAllKeys();
        int pos = 1;

        for (int i = 0; i < qKeys.length; i++) {
            String k = (String) qKeys[i];
            Ticket t = queued.getValue(k);

            String indicator = k.equals(frontKey) ? "[Current]" : "";
            String count = String.valueOf(pos);
            String ticketNo = t.getTicketNumber();

            String timeStarted = "N/A";
            String timeWaited = "N/A";
            if (t.getQueueStart() != null) {
                timeStarted = hhmm.format(t.getQueueStart());
                long waitedMillis = System.currentTimeMillis() - t.getQueueStart().getTime();
                long waitedMins = Math.max(0, waitedMillis / 60000);
                timeWaited = String.valueOf(waitedMins);
            }
            ui.displayQueueRow(indicator, count, ticketNo, timeStarted, timeWaited);
            pos++;
        }
        ui.displayQueueFooter();
    }

}
