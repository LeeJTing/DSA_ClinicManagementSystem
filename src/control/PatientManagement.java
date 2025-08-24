/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.MapInterface;
import adt.ChainBucket;
import boundary.PatientManagementUI;
import dao.Master;
import entity.Consultation;
import entity.Medicine;
import entity.Patient;
import entity.Payment;
import entity.Ticket;
import entity.Treatment;
import entity.Visit;
import java.text.SimpleDateFormat;
import utility.IDGenerator;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class PatientManagement implements CRUD {

    private MapInterface<String, Ticket> ticketMap = new ChainBucket<>();
    private MapInterface<String, Patient> patientMap = new ChainBucket<>();
    private MapInterface<String, Consultation> consultationMap = new ChainBucket<>();
    private MapInterface<String, Medicine> medicineMap = new ChainBucket<>();
    private MapInterface<String, Payment> paymentMap = new ChainBucket<>();
    private MapInterface<String, Treatment> treatmentMap = new ChainBucket<>();
    private MapInterface<Integer, Patient> historyPatient = new ChainBucket<>();
    private MapInterface<Integer, String> historyAction = new ChainBucket<>();
    private int historyKey = 1;
    public boolean patientWasDeleted = false;
    private final PatientManagementUI ui;

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

    public void PatientModule() {
        int choice = ui.displayUserPageMenu();
        switch (choice) {
            case 1:
                createNewInstance();
                break;
            case 2:
                String enteredId = ui.promptLoginId();
                if (patientExists(enteredId)) {
                    patientWasDeleted = false;
                    Master.setCurrentPatientId(enteredId.toUpperCase());
                    patientManagementModule();
                } else {
                    ui.patientNotFound();
                }
            default:
                return;
        }
    }

    public void patientManagementModule() {
        int choice;
        while (true) {
            choice = ui.displayPatientManagementMenu();
            switch (choice) {
                case 1 -> {
                    if (revertDelete()) {
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
                    reportsModule();
                }
                case 5 -> {
                    if (revertDelete()) {
                        break;
                    }
                    patientReport();
                }
                case 6 -> {
                    undo();

                }
                case 7 -> {
                    Master.setCurrentPatientId("");
                    return;
                }

                default ->
                    ui.displayInvalidChoice();
            }
        }
    }

    public void offlinePatientModule() {
        int choice = ui.displayGetTicketMenu();
        switch (choice) {
            case 1:
                getTicket();
                break;
            case 2:
                return;
            default:
                return;
        }

    }

    public void reportsModule() {
        while (true) {
            PatientManagementUI.displayAverageQueueMenu();
            int ch = ui.promptQueueReportMenuOption();
            switch (ch) {
                case 1 ->
                    reportTimelineByDateRange();
                case 2 ->
                    reportTimelineByDayOfWeek();
                case 3 ->
                    reportTimelineHeatmapAllData();
                case 4 -> {
                    return;
                }
                default ->
                    ui.displayInvalidChoice();
            }
        }
    }

    public void patientReport() {
        Object[] allVals = patientMap.getAllValues();
        PatientManagementUI.displayAllPatientsTable(allVals);
        ui.displayDemographicsHeader();
        int choice = ui.promptDemographicsMenuOption();
        switch (choice) {
            case 1 ->
                groupByGender();
            case 2 ->
                groupByAge();
            case 3 -> {
                displayVisitFrequencyReport();
            }
            case 4 -> {
                return;
            }
            default ->
                ui.displayInvalidChoice();
        }
    }

    @Override
    public void createNewInstance() {
        ui.displayCreateHeader();
        String id = generatePatientId();
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

        saveHistory(patient, "Create");
        ui.displayCreateSuccess(id);
    }

    @Override
    public void readInstance() {
        String currentPatientId = Master.getCurrentPatientId();
        Patient patient = Master.getPatientMap().getValue(currentPatientId);
        String[] visitDates = new String[patient.getVisitCount()];
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

    public void updateInstance() {
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
        while (true) {
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
    public void deleteInstance() {
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
                    ui.displayNoVisitRecordsToDelete();
                    return;
                }
                ui.displayVisitList(patient);
                int visitChoice = ui.promptVisitNumberToDelete();
                if (visitChoice < 1 || visitChoice > patient.getVisitCount()) {
                    ui.displayInvalidChoice();
                    return;
                }
                saveHistory(patient, "Update");
                Visit[] visits = patient.getVisits();
                for (int i = visitChoice - 1; i < patient.getVisitCount() - 1; i++) {
                    visits[i] = visits[i + 1];
                }
                visits[patient.getVisitCount() - 1] = null;
                patient.setVisitCount(patient.getVisitCount() - 1);
                patientMap.put(id, patient);
                ui.displayVisitDeleteSuccess();
            }
            case 2 -> {
                ui.displayDeleteWarning();
                boolean confirm = ui.confirmDeletePatient();
                if (confirm) {
                    saveHistory(patient, "Delete");
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

    public void undo() {
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

    public void getTicket() {
        MapInterface<String, Ticket> ticketQueue = Master.getTicketQueue();
        Object[] ticketKeys = ticketQueue.getAllKeys();
        Date currentDate = new Date();
        MapInterface<String, Ticket> completedTickets = new ChainBucket<>();
        Ticket completedTicket = completedTickets.removeFirst();

        for (int i = 0; i < ticketKeys.length; i++) {
            String ticketKey = (String) ticketKeys[i];
            Ticket ticket = ticketQueue.getValue(ticketKey);

            if (ticket != null && "complete".equals(ticket.getTicketStatus())) {
                recordVisitFromTicket(ticket);

                ticketQueue.put(ticketKey, ticket);
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
            ui.displayNoTicketsAvailable();
            return;
        }
        displayCurrentQueue(ticketQueue);
        ui.displayTicketAssigned(assignedTicket.getTicketNumber());
    }

    private void displayCurrentQueue(MapInterface<String, Ticket> ticketQueue) {
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
        Ticket frontTicket = queuedTickets.getFront();
        String lastTicketKey = queuedTickets.getLastKey();
        Ticket lastTicket = queuedTickets.getLast();

        String currentAssignedTicket = Master.getCurrentTicket();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Object[] allTicketKeys = queuedTickets.getAllKeys();
        int position = 1;

        for (int i = 0; i < allTicketKeys.length; i++) {
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

            if (ticket.getQueueStart() != null) {
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

    private void recordVisitFromTicket(Ticket ticket) {
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

    private Patient createNewOfflinePatient(String patientId) {
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

    private void reportTimelineByDateRange() {
        try {
            String sStart = ui.promptStartDate();
            String sEnd = ui.promptEndDate();
            java.text.SimpleDateFormat sdfD = new java.text.SimpleDateFormat("dd-MM-yyyy");
            Date start = sdfD.parse(sStart);
            Date end = sdfD.parse(sEnd);
            int[] avg = computeHourlyAvg(start, end, -1);
            PatientManagementUI.printHourlyTimeline(8, 18, avg);
        } catch (Exception e) {
            ui.displayInvalidChoice();
        }
    }

    private void reportTimelineByDayOfWeek() {
        ui.displayDayOfWeekMenu();
        int day = ui.promptDayOfWeek();
        if (day < 1 || day > 7) {
            ui.displayInvalidChoice();
            return;
        }
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

    private int[][] computeHeatmap() {
        final int START = 8, END = 18, SLOTS = END - START, DAYS = 7;
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

    private void groupByGender() {
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

    private String[] grow(String[] a) {
        String[] b = new String[a.length * 2];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    private void groupByAge() {
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

    private String generatePatientId() {
        String lastId = patientMap.isEmpty() ? "P000000" : patientMap.getLastKey();
        return IDGenerator.generateNextID(lastId);
    }

    private void saveHistory(Patient patient, String action) {
        historyPatient.put(historyKey, copyPatient(patient));
        historyAction.put(historyKey, action);
        historyKey++;
    }

    private Patient copyPatient(Patient src) {
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

    public MapInterface<String, Patient>[] groupPatientsByVisitFrequency() {
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

    public void displayVisitFrequencyReport() {
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

    private boolean revertDelete() {
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
}
