/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedHashMap;
import adt.MapInterface;
import boundary.PaymentUI;
import boundary.PharmacyUI;
import dao.Master;
import entity.Consultation;
import entity.Medicine;
import entity.Payment;
import entity.Prescription;
import entity.Treatment;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.function.Function;
import utility.IDGenerator;
import utility.MessageUI;

/**
 *
 * @author Teh Zhi Qin
 */
public class PharmacyManagementModule implements CRUD {

    private static MapInterface<String, Medicine> medicineMap = new LinkedHashMap<>();
    private static MapInterface<String, Payment> paymentMap = new LinkedHashMap<>();
    private static MapInterface<String, Treatment> treatmentMap = new LinkedHashMap<>();
    private static MapInterface<String, Consultation> consultationMap = new LinkedHashMap<>();
    private static MapInterface<String, Prescription> prescriptionMap = new LinkedHashMap<>();
    private static final MapInterface<String, Medicine> medicineDispensedMap = new LinkedHashMap<>();
    private static final MapInterface<Integer, String> alertMap = new LinkedHashMap<>();
    private static final MapInterface<String, Medicine> medicineStatusMap = new LinkedHashMap<>();
    private static final MapInterface<String, Medicine> actionHistory = new LinkedHashMap<>();
    private static final MapInterface<Integer, Medicine> expiredMedicine = new LinkedHashMap<>();
    private static final PharmacyUI PharmacyUI = new PharmacyUI();
    private static final PaymentUI PaymentUI = new PaymentUI();

    PharmacyManagementModule() {
        getAllMap();
    }

    private void getAllMap() {
        medicineMap = Master.getMedicineMap();
        paymentMap = Master.getPaymentMap();
        treatmentMap = Master.getTreatmentMap();
        consultationMap = Master.getConsultationMap();
        prescriptionMap = Master.getPrescriptionMap();
    }
    
    public static void main(String[] args) {
        Master.initializer();
        medicineMap = Master.getMedicineMap();
        paymentMap = Master.getPaymentMap();
        treatmentMap = Master.getTreatmentMap();
        consultationMap = Master.getConsultationMap();
        prescriptionMap = Master.getPrescriptionMap();
        PharmacyManagementModule pmm = new PharmacyManagementModule();
        pmm.pharmacyMenu();
    }

    public void pharmacyMenu() {
        boolean exit = false;
        alertMap.clear();
        checkNearExpiryDate();
        checkLowStock();
        int alert = alertMap.size();

        while (!exit) {
            MessageUI.clearScreen();
            int choice = PharmacyUI.getMainMenuChoice(alert);

            switch (choice) {
                case 1:
                    createNewInstance();
                    PharmacyUI.promptReturn();
                    break;
                case 2:
                    updateInstance();
                    PharmacyUI.promptReturn();
                    break;
                case 3:
                    readInstance();
                    boolean detail = PharmacyUI.promptViewMedicineDetails();
                    if (detail) {
                        searchMedicine();
                    }
                    PharmacyUI.promptReturn();
                    break;
                case 4:
                    deleteInstance();
                    PharmacyUI.promptReturn();
                    break;
                case 5:
                    generateDispensingSummaryReport();
                    PharmacyUI.promptReturn();
                    break;
                case 6:
                    generateStockStatusReport();
                    PharmacyUI.promptReturn();
                    break;
                case 7:
                    alertGeneration();
                    PharmacyUI.promptReturn();
                    break;
                case 8:
                    undoAction();
                    PharmacyUI.promptReturn();
                    break;
                case 9:
                    exit = true;
                    PharmacyUI.displayExitMsg();
            }
        }

    }

    // Add new medicine
    @Override
    public void createNewInstance() {
        String category;
        category = PharmacyUI.promptCategory();

        if (!"".equals(category)) {
            String lastID = medicineMap.getLastKey();
            String nextID = IDGenerator.generateNextID(lastID);
            Medicine medicineFound = medicineMap.getValue(nextID);
            while (medicineFound != null) {
                nextID = IDGenerator.generateNextID(nextID);
                medicineFound = medicineMap.getValue(nextID);
            }

            Medicine newMedicine = PharmacyUI.addMedicineUI(nextID, category);
            if (newMedicine != null) {
                medicineMap.put(nextID, newMedicine);
                actionHistory.put("Create " + nextID, newMedicine);
                Master.setMedicineMap(medicineMap);
                PharmacyUI.displayOperationSuccessfullyMessage("added");

                String id = newMedicine.getMedicineID();
                findMedicine(id, Medicine::getMedicineID);
            } else {
                PharmacyUI.displayOperationErrorMessage("add");
            }
        }

    }

    // Edit medicine details
    @Override
    public void updateInstance() {
        readInstance();
        String id = PharmacyUI.promptMedicineID();
        Medicine medicineFound = medicineMap.getValue(id);
        boolean exit = false;

        while (medicineFound == null) {
            PharmacyUI.displayMedicineNotFound();
            id = PharmacyUI.promptMedicineID();
            medicineFound = medicineMap.getValue(id);
        }

        Medicine beforeEditMed = new Medicine(medicineFound);

        PharmacyUI.displayMedicineDetails(medicineFound);
        while (!exit) {
            int editField = PharmacyUI.getEditOption();
            boolean edited = false;

            switch (editField) {
                case 1:
                    // Edit medicine name
                    String newName = PharmacyUI.promptName();
                    medicineFound.setMedicineName(newName);
                    edited = true;
                    break;
                case 2:
                    // Edit medicine category
                    String newCategory = PharmacyUI.promptCategory();
                    medicineFound.setMedicineCategory(newCategory);
                    edited = true;
                    break;
                case 3:
                    // Edit medicine expiry date
                    Date newExpiryDate = PharmacyUI.promptExpiryDateWithoutLimit();
                    medicineFound.setMedicineExpiryDate(newExpiryDate);
                    edited = true;
                    break;
                case 4:
                    // Add medicine stock
                    int currentStock = medicineFound.getMedicineStock();
                    int newStock = PharmacyUI.promptStock();
                    medicineFound.setMedicineStock(newStock + currentStock);
                    edited = true;
                    break;
                case 5:
                    // Edit medicine unit price
                    double newUnitPrice = PharmacyUI.promptUnitPrice();
                    medicineFound.setMedicineUnitPrice(newUnitPrice);
                    edited = true;
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    PharmacyUI.displayInvalidChoiceMsg();
            }

            if (edited) {
                medicineMap.put(id, medicineFound);
                actionHistory.put("Edit " + id, beforeEditMed);
                PharmacyUI.displayOperationSuccessfullyMessage("updated");
                PharmacyUI.displayMedicineDetails(medicineFound);
                Master.setMedicineMap(medicineMap);
            }

        }
    }

    // display medicine list
    @Override
    public void readInstance() {
        if (!medicineMap.isEmpty()) {
            PharmacyUI.displayMedicineListHeader();
            PharmacyUI.displayMedicineHeader();

            Object[] medicine = medicineMap.getAllValues();
            for (Object m : medicine) {
                Medicine med = (Medicine) m;
                PharmacyUI.displayMedicineToString(med);
            }
        } else {
            PharmacyUI.displayMedicineNotFound();
        }
    }

    // search medicine by id, name or category
    private void searchMedicine() {
        int search;
        boolean exit = false;
        while (!exit) {
            search = PharmacyUI.promptSearchField();
            switch (search) {
                case 1:
                    String id = PharmacyUI.promptMedicineID();
                    findMedicine(id, Medicine::getMedicineID);
                    break;
                case 2:
                    String name = PharmacyUI.promptName();
                    findMedicine(name, Medicine::getMedicineName);
                    break;
                case 3:
                    String category = PharmacyUI.promptCategory();
                    findMedicine(category, Medicine::getMedicineCategory);
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }

    }

    // get medicine by id, name or category
    private void findMedicine(String searchValue, Function<Medicine, String> getter) {
        boolean found = false;
        Iterator<Medicine> mIterator = medicineMap.getIterator();
        while (mIterator.hasNext()) {
            Medicine med = mIterator.next();
            if (getter.apply(med).equals(searchValue)) {
                found = true;
                PharmacyUI.displayMedicineDetails(med);
            }
        }
        if (!found) {
            PharmacyUI.displayMedicineNotFound();
        }
    }

    // delete medicine menu
    @Override
    public void deleteInstance() {
        int choice = 0;

        while (choice != 3) {
            choice = PharmacyUI.promptDeleteMenu();
            switch (choice) {
                case 1:
                    deleteMedicine();
                    break;
                case 2:
                    removeExpiredMedicine();
                    break;
                case 3:
                    break;
            }
        }

    }

    // delete medicine by id
    public void deleteMedicine() {
        int i = 0;
        String id;
        Medicine medicineFound;
        boolean confirm;

        if (medicineMap.size() != 0) {
            id = PharmacyUI.promptMedicineID();
            medicineFound = medicineMap.getValue(id);

            while (i < 3 && medicineFound == null) {
                PharmacyUI.displayMedicineNotFound();
                id = PharmacyUI.promptMedicineID();
                medicineFound = medicineMap.getValue(id);
                i++;
            }

            if (medicineFound != null) {
                PharmacyUI.displayMedicineDetails(medicineFound);
                confirm = PharmacyUI.promptDeleteMsg();
                if (confirm) {
                    medicineMap.remove(id);
                    actionHistory.put("Delete " + id, medicineFound);
                    Master.setMedicineMap(medicineMap);
                    PharmacyUI.displayOperationSuccessfullyMessage("deleted");
                } else {
                    PharmacyUI.displayOperationErrorMessage("delete");
                }
            } else {
                PharmacyUI.displayExceedLimitMsg();

            }
        } else {
            PharmacyUI.displayMedicineNotFound();
        }
    }

    // display medicine with only medicine_name, medicine_category, medicine_stock, unit_price
    public void displayCustomMedicineList() {
        Iterator<Medicine> medicineDispensedIt = medicineDispensedMap.getIterator();
        while (medicineDispensedIt.hasNext()) {
            Medicine md = medicineDispensedIt.next();
            PharmacyUI.displayMedicineCustomizedToString(md);
        }
    }

    // generate medicine dispensed summary report
    public void generateDispensingSummaryReport() {
        int choice;
        boolean exit = false;

        // clear current medicineDispensedMap
        medicineDispensedMap.clear();

        // update medicineDispensedMap
        updateMedicineDispensed();

        PharmacyUI.displayDispensingSummaryReportUI();
        displayCustomMedicineList();

        while (!exit) {
            choice = PharmacyUI.promptMedicineDispensingSummaryFilter();
            switch (choice) {
                case 1:
                    // Display the top value desired by user
                    filterDispensingSummaryReportByTopValue();
                    break;
                case 2:
                    // Display the total medicine dispensed for each category
                    groupMedicineDispensedByCategory();
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }

    }

    // Display the top value desired by user
    private void filterDispensingSummaryReportByTopValue() {
        int i = 0;
        int topValue;

        topValue = PharmacyUI.promptTopMedicineDispensedSummary();

        Medicine firstMed = medicineDispensedMap.getFront();
        firstMed.setCompare("medicine_stock");
        medicineDispensedMap.sorting();

        Iterator<Medicine> medicineIterator = medicineDispensedMap.getIterator();
        PharmacyUI.displayTopDispensingSummaryReportUI(topValue);
        while (medicineIterator.hasNext() && i < topValue) {
            Medicine med = medicineIterator.next();
            PharmacyUI.displayMedicineCustomizedToString(med);
            i++;
        }
    }

    // Display medicine list with stock status (Expiry Soon/Low Stock)
    public void displayMedicineStockStatus() {
        medicineStatusMap.clear();
        updateMedicineStockStatus();

        PharmacyUI.displayMedicineStatusListHeader();
        PharmacyUI.displayMedicineStatusHeader();

        if (medicineStatusMap == null || medicineStatusMap.size() == 0) {
            PharmacyUI.displayMedicineNotFound();
            return;
        }

        Object[] medicine = medicineStatusMap.getAllValues();
        for (Object m : medicine) {
            Medicine med = (Medicine) m;
            PharmacyUI.displayMedicineStatusToString(med);
        }
    }

    // Update medicine stock status
    private void updateMedicineStockStatus() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date expiryDateThreshold = cal.getTime();

        Object[] medicine = medicineMap.getAllValues();
        for (Object m : medicine) {
            Medicine med = (Medicine) m;
            if (med.getMedicineExpiryDate().before(expiryDateThreshold)) { // Check expiry date
                medicineStatusMap.put(med.getMedicineID(), new Medicine(med, "Expiry Alert"));
            } else if (med.getMedicineStock() < 30) { // Check low stock (30)
                medicineStatusMap.put(med.getMedicineID(), new Medicine(med, "Low Stock Alert"));
            } else {
                medicineStatusMap.put(med.getMedicineID(), new Medicine(med, "Good"));
            }
        }
    }

    // Display medicine list with stock status report
    public void generateStockStatusReport() {
        displayMedicineStockStatus();
        boolean exit = false;
        int choice;

        while (!exit) {
            choice = PharmacyUI.promptFilterMedicineStockBy();
            switch (choice) {
                case 1:
                    int stockThreshold = PharmacyUI.promptStockLevelTreshold();
                    filterMedicineStockLevel(stockThreshold);
                    break;
                case 2:
                    Date expiryDate = PharmacyUI.promptExpiryDateWithoutLimit();
                    filterMedicineByExpiryDate(expiryDate);
                    break;
                case 3:
                    String category = PharmacyUI.promptCategory();
                    filterMedicineByCategory(category);
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }

    }

    // Update medicineDispensedMap
    private void updateMedicineDispensed() {
        Iterator<Prescription> prescriptionIterator = prescriptionMap.getIterator();

        while (prescriptionIterator.hasNext()) {
            Prescription prescription = prescriptionIterator.next();

            // get medicine list from the prescription
            Iterator<Medicine> medIterator = prescription.getMedicineList().getIterator();

            // loop through the medicine list in the prescription
            while (medIterator.hasNext()) {
                Medicine med = medIterator.next();
                String name = med.getMedicineName();
                String category = med.getMedicineCategory();
                int quantity = med.getMedicineStock();
                double up = med.getMedicineUnitPrice();

                // if the medicine has been counted before, update it
                if (medicineDispensedMap.containsKey(name)) {
                    Medicine medicineFound = medicineDispensedMap.getValue(name);
                    int currentCount = medicineFound.getMedicineStock();
                    medicineFound.setMedicineStock(currentCount + quantity);
                    medicineDispensedMap.put(name, medicineFound);
                } else {
                    // add the medicine to the medicineDispensedMap
                    medicineDispensedMap.put(name, new Medicine(name, category, quantity, up));
                }
            }
        }
    }

    // Display the total medicine dispensed for each category
    private void groupMedicineDispensedByCategory() {
        MapInterface<String, Medicine> categoryMedicineDispensedMap = new LinkedHashMap<>();

        String[] categories = {
            "Analgesics (Painkillers)", "Antibiotics", "Antipyretics (Fever Reducers)",
            "Antiseptics & Disinfectants", "Antihistamines (Allergy Relief)", "Cough and Cold Remedies", "Anti-inflammatory Drugs"
        };

        for (String category : categories) {
            Medicine categoryKey = new Medicine(category);
            categoryKey.setCompare("category");

            MapInterface<String, Medicine> categoryGroup = medicineDispensedMap.groupBy(categoryKey);

            if (categoryGroup != null && !categoryGroup.isEmpty()) {
                int totalStock = 0;

                Iterator<Medicine> catMedIterator = categoryGroup.getIterator();
                while (catMedIterator.hasNext()) {
                    Medicine med = catMedIterator.next();
                    totalStock += med.getMedicineStock();
                }
                // Store the total medicine dispensed for each category
                categoryMedicineDispensedMap.put(category, new Medicine(category, totalStock));
            }
        }

        Medicine compareMed = new Medicine();
        compareMed.setCompare("medicine_stock");

        // display the value in descending
        categoryMedicineDispensedMap.sorting();
        Iterator<Medicine> medIt = categoryMedicineDispensedMap.getIterator();
        PharmacyUI.displayMedicineCategoryHeader();
        while (medIt.hasNext()) {
            Medicine med = medIt.next();
            PharmacyUI.displayMedicineListByCategory(med);
        }

        // display the value in ascending
        compareMed.setCompare("medicine_stock_asc");
        categoryMedicineDispensedMap.sorting();
        medIt = categoryMedicineDispensedMap.getIterator();
        PharmacyUI.displayMedicineCategoryHeader();
        while (medIt.hasNext()) {
            Medicine med = medIt.next();
            PharmacyUI.displayMedicineListByCategory(med);
        }
    }

    // Filter the medicine stock level desired by the user
    private void filterMedicineStockLevel(int threshold) {
        Iterator<Medicine> medicineIterator = medicineMap.getIterator();
        boolean found = false;
        PharmacyUI.displayMedicineFilterByStockLevelHeader(threshold);
        PharmacyUI.displayMedicineHeader();

        while (medicineIterator.hasNext()) {
            Medicine med = medicineIterator.next();
            if (med.getMedicineStock() < threshold) {
                PharmacyUI.displayMedicineToString(med);
                found = true;
            }
        }

        if (!found) {
            PharmacyUI.displayNoMedicineUnderThreshold(threshold);
        }
    }

    // Filter the medicine expiry date desired by the user
    private void filterMedicineByExpiryDate(Date expiryDate) {
        Iterator<Medicine> medicineIterator = medicineMap.getIterator();
        boolean found = false;
        PharmacyUI.displayMedicineFilterByExpiryDateHeader();
        PharmacyUI.displayMedicineHeader();

        while (medicineIterator.hasNext()) {
            Medicine med = medicineIterator.next();
            if (med.getMedicineExpiryDate().before(expiryDate)) {
                PharmacyUI.displayMedicineToString(med);
                found = true;
            }
        }

        if (!found) {
            PharmacyUI.displayNoMedicineBeforeExpiryDate(expiryDate);
        }
    }

    // Filter the medicine category desired by the user
    private void filterMedicineByCategory(String category) {
        MapInterface<String, Medicine> categoryMedicine;
        boolean found = false;

        Medicine newMed = new Medicine();
        newMed.setCompare("category");
        categoryMedicine = medicineMap.groupBy(new Medicine(category));

        PharmacyUI.displayMedicineFilterByCategoryHeader();
        Iterator<Medicine> catMedIterator = categoryMedicine.getIterator();
        while (catMedIterator.hasNext()) {
            Medicine med = catMedIterator.next();
            PharmacyUI.displayMedicineToString(med);
            found = true;
        }
        if (!found) {
            PharmacyUI.displayNoMedicineFoundWithinCategory(category);
        }
    }

    // Generate alert on low stock & expiry soon
    public void alertGeneration() {
        if (alertMap.size() > 0) {
            PharmacyUI.displayAlertHeader();
            Object[] alertMsg = alertMap.getAllValues();
            for (int i = 0; i < alertMap.size(); i++) {
                PharmacyUI.displayAlertMsg(i + 1, alertMsg[i].toString());
            }
            PharmacyUI.displayAlertFooter();
        } else {
            PharmacyUI.displayNoAlertMsg();
        }
    }

    // Check whether the medicine expiry date is within 1 month
    private void checkNearExpiryDate() {
        int alertIndex = alertMap.size() + 1;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date expiryDateThreshold = cal.getTime();

        Iterator<Medicine> mIterator = medicineMap.getIterator();
        while (mIterator.hasNext()) {
            Medicine med = mIterator.next();
            String medId = med.getMedicineID();
            if (med.getMedicineExpiryDate().before(expiryDateThreshold)) {
                alertMap.put(alertIndex++, medId + " is going to be expired!");
            }
        }
    }

    // Check whether the medicine stock level is under 30
    private void checkLowStock() {
        int alertIndex = alertMap.size() + 1;
        int stockThreshold = 30;
        Iterator<Medicine> mIterator = medicineMap.getIterator();
        while (mIterator.hasNext()) {
            Medicine med = mIterator.next();
            String medId = med.getMedicineID();
            if (med.getMedicineStock() < stockThreshold) {
                alertMap.put(alertIndex++, medId + " is going to stock out!");
            }
        }
    }

    // Check and update expired medicine in expiredMedicine
    private void updateExpiredMedicine() {
        Calendar cal = Calendar.getInstance();
        Date dateToCheck = cal.getTime();
        expiredMedicine.clear();
        int i = 0;
        Iterator<Medicine> medIt = medicineMap.getIterator();
        while (medIt.hasNext()) {
            Medicine med = medIt.next();
            if (med.getMedicineExpiryDate().before(dateToCheck)) {
                expiredMedicine.put(++i, med);
            }
        }
    }

    // Removed expired medicine
    public void removeExpiredMedicine() {
        updateExpiredMedicine();
        if (!expiredMedicine.isEmpty()) {
            boolean delete = PharmacyUI.confirmDeleteExpiredItems();

            if (delete) {
                PharmacyUI.displayExpiredMedicineHeader();
                while (!expiredMedicine.isEmpty()) {
                    Medicine med = expiredMedicine.removeFirst();
                    medicineMap.remove(med.getMedicineID());
                    PharmacyUI.displayExpiredMedicine(med);
                }
                PharmacyUI.displayRemoveExpiredMedicineSuccessfully();
            }
        } else {
            PharmacyUI.displayMedicineNotFound();
        }
    }

    // Undo user action (Create, Edit and Delete)
    public void undoAction() {
        if (!actionHistory.isEmpty()) {
            boolean undo = PharmacyUI.promptUndoLastAction();
            if (undo) {
                String action = actionHistory.getLastKey();
                Medicine lastMed = actionHistory.removeLast();
                if (action.startsWith("Create")) {
                    medicineMap.remove(lastMed.getMedicineID()); // Remove created medicine
                } else {
                    medicineMap.put(lastMed.getMedicineID(), lastMed); // Replace / Restore the editted and removed medicine
                }
                Master.setMedicineMap(medicineMap);
                medicineMap.keyReverseSorting();
                PharmacyUI.displayOperationSuccessfullyMessage(action + " Operation restored");
            }
        } else {
            PharmacyUI.displayNoLastAction();
        }

    }

    // Payment to consultation and medicine cost
    public void payment(Prescription prescription) {
        String lastID = paymentMap.getLastKey();
        String newID = IDGenerator.generateNextID(lastID);
        Payment newPayment;
        String treatmentID = prescription.getTreatment_id();
        Treatment treatmentFound = treatmentMap.getValue(treatmentID);
        Consultation consultationFound = consultationMap.getValue(treatmentFound.getConsultation_id());

        newPayment = PaymentUI.paymentUI(newID, consultationFound, prescription);
        if (newPayment != null) {
            paymentMap.put(newID, newPayment);
            Master.setPaymentMap(paymentMap);
            PaymentUI.displaySuccessfulMsg();
        }
    }

    // Get the available medicine details
    public MapInterface<String, Medicine> getAvailableMedicine(MapInterface<String, Medicine> medicineNeeded) {
        MapInterface<String, Medicine> availableMedicine = new LinkedHashMap<>();

        // Avoid dispensing medicine that expiry within 2 weeks
        LocalDate threshold = LocalDate.now().plusWeeks(2);
        Date expiryDateThreshold = Date.from(threshold.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Iterator<Medicine> mNIterator = medicineNeeded.getIterator();

        while (mNIterator.hasNext()) {
            Medicine medNeeded = mNIterator.next();
            int stockNeeded = medNeeded.getMedicineStock();
            int stockUnfulfilled = stockNeeded;

            Iterator<Medicine> mIterator = medicineMap.getIterator();

            while (mIterator.hasNext() && stockUnfulfilled > 0) { // loop while stock needed havent fulfilled
                Medicine med = mIterator.next();

                if (med.getMedicineExpiryDate().after(expiryDateThreshold)
                        && med.getMedicineName().equals(medNeeded.getMedicineName())) {

                    int availableStock = med.getMedicineStock(); // get the current available medicine stock

                    if (availableStock > 0) {
                        int stockToTake = Math.min(availableStock, stockUnfulfilled); // stock can be taken

                        Medicine takenMed = new Medicine(
                                med.getMedicineID(),
                                med.getMedicineName(),
                                med.getMedicineCategory(),
                                med.getMedicineExpiryDate(),
                                stockToTake,
                                med.getMedicineUnitPrice()
                        );

                        availableMedicine.put(med.getMedicineID(), takenMed); // add to the availableMedicine
                        med.updateMedicineStock(stockToTake); // update the medicine stock
                        stockUnfulfilled -= stockToTake; // update the stockUnfulfilled
                    }
                }
            }

        }
        Master.setMedicineMap(medicineMap);
        return availableMedicine;
    }
}
