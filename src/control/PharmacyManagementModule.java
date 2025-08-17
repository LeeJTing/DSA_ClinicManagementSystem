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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static final PharmacyUI PharmacyUI = new PharmacyUI();
    private static final PaymentUI PaymentUI = new PaymentUI();

    private static final Master Master = new Master();
    
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
                    if (medicineMap.size() != 0) {
                        readInstance();
                        boolean detail = PharmacyUI.promptViewMedicineDetails();
                        if (detail) {
                            searchMedicine();
                        }
                    } else {
                        PharmacyUI.displayMedicineNotFound();
                    }
                    PharmacyUI.promptReturn();
                    break;
                case 4:
                    if (medicineMap.size() != 0) {
                        deleteInstance();
                    } else {
                        PharmacyUI.displayMedicineNotFound();
                    }
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
//                    testPayment();
                    alertGeneration();
                    PharmacyUI.promptReturn();
                    break;
                case 8:
                    exit = true;
                    System.out.println("\n\t\tExiting Pharmacy Module.\n");
            }
        }

    }

    @Override
    public void createNewInstance() {
        String category;
        category = PharmacyUI.promptCategory();

        if (!"".equals(category)) {
            String lastID = medicineMap.getLastKey();
            String nextID = IDGenerator.generateNextID(lastID);

            Medicine newMedicine = PharmacyUI.addMedicineUI(nextID, category);
            if (newMedicine != null) {
                medicineMap.put(newMedicine.getMedicineID(), newMedicine);
                Master.setMedicineMap(medicineMap);
                PharmacyUI.displayOperationSuccessfullyMessage("added");

                String id = newMedicine.getMedicineID();
                findMedicine(id, Medicine::getMedicineID);
            } else {
                PharmacyUI.displayOperationErrorMessage("add");
            }
        }

    }

    public Medicine getMedicine(String id) {
        if (medicineMap.containsKey(id)) {
            return medicineMap.getValue(id);
        }
        return null;
    }

    @Override
    public void updateInstance() {
        readInstance();
        String id = PharmacyUI.promptMedicineID();
        Medicine medicineFound = getMedicine(id);
        boolean exit = false;

        while (medicineFound == null) {
            PharmacyUI.displayMedicineNotFound();
            id = PharmacyUI.promptMedicineID();
            medicineFound = getMedicine(id);
        }

        PharmacyUI.displayMedicineDetails(medicineFound);
        while (!exit) {
            int editField = PharmacyUI.getEditOption();
            boolean edited = false;

            switch (editField) {
                case 1:
                    String newName = PharmacyUI.promptName();
                    medicineFound.setMedicineName(newName);
                    edited = true;
                    break;
                case 2:
                    String newCategory = PharmacyUI.promptCategory();
                    medicineFound.setMedicineCategory(newCategory);
                    edited = true;
                    break;
                case 3:
                    Date newExpiryDate = PharmacyUI.promptExpiryDate();
                    medicineFound.setMedicineExpiryDate(newExpiryDate);
                    edited = true;
                    break;
                case 4:
                    int currentStock = medicineFound.getMedicineStock();
                    int newStock = PharmacyUI.promptStock();
                    medicineFound.setMedicineStock(newStock + currentStock);
                    edited = true;
                    break;
                case 5:
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
                PharmacyUI.displayOperationSuccessfullyMessage("updated");
                PharmacyUI.displayMedicineDetails(medicineFound);
                Master.setMedicineMap(medicineMap);
            }

        }
    }

    @Override
    public void readInstance() {
        PharmacyUI.displayMedicineListHeader();
        PharmacyUI.displayMedicineHeader();

        Object[] medicine = medicineMap.getAllValues();
        for (Object m : medicine) {
            Medicine med = (Medicine) m;
            PharmacyUI.displayMedicineToString(med);
        }

    }

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

    @Override
    public void deleteInstance() {
        int i = 0;
        String id;
        Medicine medicineFound;
        boolean confirm;

        id = PharmacyUI.promptMedicineID();
        medicineFound = getMedicine(id);

        while (i < 3 && medicineFound == null) {
            PharmacyUI.displayMedicineNotFound();
            id = PharmacyUI.promptMedicineID();
            medicineFound = getMedicine(id);
            i++;
        }

        if (medicineFound != null) {
            PharmacyUI.displayMedicineDetails(medicineFound);
            confirm = PharmacyUI.promptDeleteMsg();
            if (confirm) {
                medicineMap.remove(id);
                Master.setMedicineMap(medicineMap);
                PharmacyUI.displayOperationSuccessfullyMessage("deleted");
                boolean undo = PharmacyUI.promptUndo();
                if (undo) {
                    medicineMap.put(id, medicineFound);
                    medicineMap.keyReverseSorting();
                    Master.setMedicineMap(medicineMap);
                    PharmacyUI.displayOperationSuccessfullyMessage("restored");
                } 
            } else {
                PharmacyUI.displayOperationErrorMessage("delete");
            }
        } else {
            PharmacyUI.displayExceedLimitMsg();

        }
    }

    public void displayCustomMedicineList() {
        Iterator<Medicine> medicineDispensedIt = medicineDispensedMap.getIterator();
        while (medicineDispensedIt.hasNext()) {
            Medicine md = medicineDispensedIt.next();
            PharmacyUI.displayMedicineCustomizedToString(md);
        }
    }

    public void generateDispensingSummaryReport() {
        int choice;
        boolean exit = false;
        medicineDispensedMap.clear();
        updateMedicineDispensed();
        PharmacyUI.displayDispensingSummaryReportUI();
        displayCustomMedicineList();

        while (!exit) {
            choice = PharmacyUI.promptMedicineDispensingSummaryFilter();
            switch (choice) {
                case 1:
                    filterDispensingSummaryReportByTopValue();
                    break;
                case 2:
                    groupMedicineDispensedByCategory();
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }

    }

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

    private void updateMedicineStockStatus() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date expiryDateThreshold = cal.getTime();

        Object[] medicine = medicineMap.getAllValues();
        for (Object m : medicine) {
            Medicine med = (Medicine) m;
            if (med.getMedicineExpiryDate().before(expiryDateThreshold)) {
                medicineStatusMap.put(med.getMedicineID(), new Medicine(med, "Expiry Alert"));
            } else if (med.getMedicineStock() < 30) {
                medicineStatusMap.put(med.getMedicineID(), new Medicine(med, "Low Stock Alert"));
            } else {
                medicineStatusMap.put(med.getMedicineID(), new Medicine(med, "Good"));
            }
        }
    }

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

    private void updateMedicineDispensed() {
        Iterator<Prescription> prescriptionIterator = prescriptionMap.getIterator();

        // loop through every prescription
        while (prescriptionIterator.hasNext()) {
            Prescription prescription = prescriptionIterator.next();

            // get medicine list from the prescription
            Iterator<Medicine> medIterator = prescription.getMedicineList().getIterator();

            // loop through each medicine in the prescription
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
                categoryMedicineDispensedMap.put(category, new Medicine(category, totalStock));
            }
        }

        Medicine compareMed = new Medicine();
        compareMed.setCompare("medicine_stock");
        categoryMedicineDispensedMap.sorting();

        Iterator<Medicine> medIt = categoryMedicineDispensedMap.getIterator();
        PharmacyUI.displayMedicineCategoryHeader();
        while (medIt.hasNext()) {
            Medicine med = medIt.next();
            PharmacyUI.displayMedicineListByCategory(med);
        }
        compareMed.setCompare("medicine_stock_asc");
        categoryMedicineDispensedMap.sorting();
        medIt = categoryMedicineDispensedMap.getIterator();
        PharmacyUI.displayMedicineCategoryHeader();
        while (medIt.hasNext()) {
            Medicine med = medIt.next();
            PharmacyUI.displayMedicineListByCategory(med);
        }
    }

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

    public void testPayment() {
        try {
            MapInterface<String, Medicine> testMed = new LinkedHashMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date expiryDate = sdf.parse("10-10-2030");
            testMed.put("M000001", new Medicine("M000001", "Paracetamol", "Analgesics (Painkillers)", expiryDate, 2, 5.0));
            testMed.put("M000006", new Medicine("M000006", "Iodine", "Antiseptics & Disinfectants", expiryDate, 9, 4.0));
            Prescription testPre = new Prescription("PH000001", testMed, "S000001", "P000005", "T000001");
            payment(testPre);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void displayPaymentMap() {
        Object[] payment = paymentMap.getAllValues();
        for (Object p : payment) {
            Payment pm = (Payment) p;
            PaymentUI.displayPaymentToString(pm);
        }
    }

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

    public MapInterface<String, Medicine> getAvailableMedicine(MapInterface<String, Medicine> medicineNeeded) {
        MapInterface<String, Medicine> availableMedicine = new LinkedHashMap<>();

        LocalDate threshold = LocalDate.now().plusWeeks(2);
        Date expiryDateThreshold = Date.from(threshold.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Iterator<Medicine> mNIterator = medicineNeeded.getIterator();

        while (mNIterator.hasNext()) {
            Medicine medNeeded = mNIterator.next();
            int stockNeeded = medNeeded.getMedicineStock();
            int stockUnfulfilled = stockNeeded;

            Iterator<Medicine> mIterator = medicineMap.getIterator();

            while (mIterator.hasNext() && stockUnfulfilled > 0) {
                Medicine med = mIterator.next();

                if (med.getMedicineExpiryDate().after(expiryDateThreshold)
                        && med.getMedicineName().equals(medNeeded.getMedicineName())) {

                    int availableStock = med.getMedicineStock();

                    if (availableStock > 0) {
                        int stockToTake = Math.min(availableStock, stockUnfulfilled);

                        Medicine takenMed = new Medicine(
                                med.getMedicineID(),
                                med.getMedicineName(),
                                med.getMedicineCategory(),
                                med.getMedicineExpiryDate(),
                                stockToTake,
                                med.getMedicineUnitPrice()
                        );

                        availableMedicine.put(med.getMedicineID(), takenMed);
                        med.updateMedicineStock(stockToTake);
                        stockUnfulfilled -= stockToTake;
                    }
                }
            }

        }
        Master.setMedicineMap(medicineMap);
        return availableMedicine;
    }
}
