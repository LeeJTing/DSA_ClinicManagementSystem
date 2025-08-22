/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.Master;
import java.util.Date;
import java.text.SimpleDateFormat;

import adt.ChainBucket;
import adt.MapInterface;
import utility.*;
import entity.*;
import boundary.MedicalTreatmentUI;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Lee Jun Ting
 */
public class MedicalTreatmentManagement implements CRUD {

    private final MedicalTreatmentUI UI = new MedicalTreatmentUI();
    private MapInterface<String, Treatment> treatmentMap = new ChainBucket<>();
    private MapInterface<String, Patient> patientMap = new ChainBucket<>();
    private MapInterface<String, Staff> staffMap = new ChainBucket<>();
    private MapInterface<String, Consultation> consultationMap = new ChainBucket<>();
    private MapInterface<String, Medicine> medicineMap = new ChainBucket<>();
    private MapInterface<String, Prescription> prescriptionMap = new ChainBucket<>();
    private MapInterface<String, Payment> paymentMap = new ChainBucket<>();
    private MapInterface<LocalDate, MapInterface<Integer, String>> dutyScheduleMap = new ChainBucket<>();

    private static int historyKey = 1;
    private MapInterface<Integer, Treatment> treatmentRecordHistory = new ChainBucket<>();
    private MapInterface<Integer, Prescription> prescriptionRecordHistory = new ChainBucket<>();
    private MapInterface<Integer, String> actionHistory = new ChainBucket();

    Scanner scanner = new Scanner(System.in);

    MedicalTreatmentManagement() {
        getAllMap();
    }

    private void getAllMap() {
        treatmentMap = Master.getTreatmentMap();
        patientMap = Master.getPatientMap();
        staffMap = Master.getStaffMap();
        patientMap = Master.getPatientMap();
        medicineMap = Master.getMedicineMap();
        consultationMap = Master.getConsultationMap();
        prescriptionMap = Master.getPrescriptionMap();
        paymentMap = Master.getPaymentMap();
    }

    public void treatmentMenu() {
        boolean cont = true;
        while (cont) {
            getAllMap();

            int selection = UI.treatmentMenu();

            switch (selection) {
                case 1:
                    createNewInstance();
                    break;
                case 2:
                    readInstance();
                    break;
                case 3:
                    updateInstance();
                    break;
                case 4:
                    deleteInstance();
                    break;
                case 5:
                    diseasePredictionAndRelationshipReport();
                    break;
                case 6:
                    diseaseAnalysisReport();
                    break;
                case 7:
                    undo();
                    break;
                default:
                    cont = false;
                    break;
            }
            Master.setTreatmentMap(treatmentMap);
        }
    }

    public void undo() {
        if(actionHistory.isEmpty()){
            // no record UI
            UI.noRecord("History Record!!");
        }else {
            Treatment treatment;
            Prescription prescription;
            if(actionHistory.removeLast().equals("Create")){
                // the treatment and prescription sould remove
                treatment = treatmentRecordHistory.removeLast();
                prescription = prescriptionRecordHistory.removeLast();
                
                prescriptionMap.remove(prescription.getPrescription_id());
                treatmentMap.remove(treatment.getTreatment_id());
                
                String patientName = getPatientName(treatment.getPatient_id());
                String staffName = getStaffName(treatment.getStaff_id());
                
                UI.removedTreatment(treatment, prescription, staffName, patientName);
            }else{
                // delete and update
                treatment = treatmentRecordHistory.removeLast();
                prescription = prescriptionRecordHistory.removeLast();
                
                treatmentMap.put(treatment.getTreatment_id(), treatment);
                prescriptionMap.put(prescription.getPatient_id(), prescription);
                
                treatmentMap.keyReverseSorting();
                prescriptionMap.keyReverseSorting();
                
                String patientName = getPatientName(treatment.getPatient_id());
                String staffName = getStaffName(treatment.getStaff_id());
                
                UI.showUpdatedTreatment(treatment, prescription, staffName, patientName);
            }
            Master.setTreatmentMap(treatmentMap);
            Master.setPrescriptionMap(prescriptionMap);
        }
    }

    private void saveHistory(Treatment treatment, Prescription prescription, String action) {
        treatmentRecordHistory.put(historyKey, treatment);
        prescriptionRecordHistory.put(historyKey, prescription);
        actionHistory.put(historyKey, action);

        historyKey++;
    }

    // add new medical treatment
    @Override
    public void createNewInstance() {
        String consultationID = UI.askConsultationID();
        consultationID = consultationID.toUpperCase();
        if (!consultationMap.containsKey(consultationID)
                || consultationMap.getValue(consultationID).getAppointmentStatus().equals("Completed")) { // check whether completed or not
            UI.addNewConsultationNotFoundCompleted();
            scanner.nextLine();
        } else {
            consultationToTreatment(consultationID);
        }

    }

    // called if patient was walk-in consultation to Treatment
    public void consultationToTreatment(String consultationId) {
        getAllMap();
        // find which consultation, patient and staff
        Consultation consultation = consultationMap.getValue(consultationId);
        Patient patient = patientMap.getValue(consultation.getPatient_Id());
        Staff staff = staffMap.getValue(consultation.getStaff_Id());

        // data fields
        String treatmentId = treatmentMap.getLastKey();
        Date date = new Date();
        String staffId = staff.getStaffID();
        String patientId = patient.getPatient_id();
        String disease, advice, remark;
        boolean isScan;

        // last id + 1
        if (treatmentId == null) {
            treatmentId = "T000001";
        } else {
            treatmentId = IDGenerator.generateNextID(treatmentId);
        }

        // show all details
        UI.showCurrentTreatmentID(treatmentId);
        UI.showPatientIDName(patient);
        UI.showStaffIDName(staff);
        UI.showDate(date);

        // get info
        disease = UI.askDisease();
        isScan = UI.askIsScan();
        advice = UI.askAdvice();
        remark = UI.askRemark();

        Treatment treatment = new Treatment(treatmentId, consultationId, disease, advice, date, staffId, patientId, isScan, remark);
        treatmentMap.put(treatmentId, treatment);
        Master.setTreatmentMap(treatmentMap);

        // consultation set as complete
        consultation.setAppointmentStatus("Completed");
        // Only update walk-in end time
        if (consultation.getType().equals("Walk-In")) {
            consultation.setConsultation_end_time(date);
        }

        //update consultation status and end time
        consultationMap.put(consultationId, consultation);
        Master.setConsultationMap(consultationMap);

        //generate prescription
        treatmentToPrescription(treatment);
    }

    private void treatmentToPrescription(Treatment treatment) {
        String prescription_id = prescriptionMap.getLastKey();
        prescription_id = IDGenerator.generateNextID(prescription_id);
        MapInterface<String, Medicine> medicineList = new ChainBucket<>();
        String staff_id = treatment.getStaff_id();
        String patient_id = treatment.getPatient_id();
        String treatment_id = treatment.getTreatment_id();

        Iterator<Medicine> iterMedicine = medicineMap.getIterator();
        MapInterface<String, Medicine> medicine = new ChainBucket<>();
        while (iterMedicine.hasNext()) {
            Medicine m = iterMedicine.next();
            String medicineName = m.getMedicineName();
            int medicineStock = m.getMedicineStock();

            if (medicine.containsKey(medicineName)) {
                int totalStock = medicine.getValue(medicineName).getMedicineStock() + medicineStock;
                medicine.put(medicineName, new Medicine(medicineName, totalStock));
            } else {
                medicine.put(medicineName, new Medicine(medicineName, medicineStock));
            }
        }

        boolean cont = false;
        do {
            if (!medicine.isEmpty()) {
                medicine.sorting();

                Medicine tempMedicine = UI.askMedicine(medicine);
                String tempMedicineName = tempMedicine.getMedicineName();
                int preStock = medicine.getValue(tempMedicineName).getMedicineStock();
                int usedStock = tempMedicine.getMedicineStock();

                int newStock = preStock - usedStock;
                if (medicineList.containsKey(tempMedicineName)) {
                    usedStock += medicineList.getValue(tempMedicineName).getMedicineStock();
                    tempMedicine.setMedicineStock(usedStock);
                    medicineList.put(tempMedicineName, tempMedicine);
                } else {
                    medicineList.put(tempMedicineName, tempMedicine);
                }

                // update medicine stock
                medicine.put(tempMedicineName, new Medicine(tempMedicineName, newStock));

                String continueOption = UI.askToContinue().toUpperCase();
                cont = continueOption.equals("Y");
            }
        } while (cont);
        // call PharmacyManagement function pass in MapInterface<String, Medicine>

        MapInterface<String, Medicine> medicineDetialList = new PharmacyManagementModule().getAvailableMedicine(medicineList);

        // update Medicine Map
        Prescription prescription = new Prescription(prescription_id, medicineDetialList, staff_id, patient_id, treatment_id);
        prescriptionMap.put(prescription_id, prescription);
        Master.setPrescriptionMap(prescriptionMap);
        medicineMap = Master.getMedicineMap();
        
        saveHistory(treatment, prescription, "Create");

        // call payment method to generate payment
//        prescriptionToPayment(prescription);
    }
    
    public void prescriptionToPayment(Prescription prescription){
        
        PharmacyManagementModule pharmacyManagementModule = new PharmacyManagementModule();
        pharmacyManagementModule.payment(prescription);
    }

    // Display all Medical Treatment Record
    @Override
    public void readInstance() {

        // get user input, option 1 = treatment id, 2 = only specific patient, 3 = all treatment,0 or other is exit
        boolean exit = false;
        String staffName, patientName;
        while (!exit) {
            int option = UI.displayMenu();
            switch (option) {
                case 1:
                    String treatmentID = UI.askTreatmentID();
                    treatmentID = treatmentID.toUpperCase();
                    staffName = getStaffName(treatmentMap.getValue(treatmentID).getStaff_id());
                    patientName = getPatientName(treatmentMap.getValue(treatmentID).getPatient_id());
                    UI.displaySpecificTreatmentRecord(treatmentMap.getValue(treatmentID), getPrescription(treatmentID), staffName, patientName);
                    scanner.nextLine();
                    break;
                case 2:
                    String patientID = UI.askPatientID();
                    patientID = patientID.toUpperCase();
                    Object[] temp = treatmentMap.getAllValues();

                    for (Object t : temp) {
                        if (t instanceof Treatment) {
                            Treatment treatment = (Treatment) t;
                            staffName = getStaffName(treatment.getStaff_id());
                            patientName = getPatientName(patientID);
                            if (treatment.getPatient_id().equals(patientID)) {
                                UI.displaySpecificTreatmentRecord(treatment, getPrescription(treatment.getTreatment_id()), staffName, patientName);
                            }
                        }
                    }
                    scanner.nextLine();
                    break;
                case 3:
                    Iterator<Treatment> iterator = treatmentMap.getIterator();
                    while (iterator.hasNext()) {
                        Treatment treatment = iterator.next();
                        staffName = getStaffName(treatment.getStaff_id());
                        patientName = getPatientName(treatment.getPatient_id());
                        UI.displaySpecificTreatmentRecord(treatment, getPrescription(treatment.getTreatment_id()), staffName, patientName);
                    }
                    scanner.nextLine();
                    break;
                default:
                    exit = true;
                    break;
            }
        }
    }

    private String getPatientName(String patientId) {
        String name = null;

        if (patientMap.containsKey(patientId)) {
            name = patientMap.getValue(patientId).getPatient_name();
        }

        return name;
    }

    private String getStaffName(String staffId) {
        String name = null;

        if (staffMap.containsKey(staffId)) {
            name = staffMap.getValue(staffId).getStaffName();
        }

        return name;
    }

    private Prescription getPrescription(String treatmentId) {
        Prescription prescription = new Prescription();
        Object[] obj = prescriptionMap.getAllValues();
        for (Object o : obj) {
            Prescription pre = (Prescription) o;
            if (pre.getTreatment_id().equals(treatmentId)) {
                return pre;
            }
        }
        return prescription;
    }

    // Modify Medical Treament Record
    @Override
    public void updateInstance() {
        UI.updateTreatment();
        String treatmentId = UI.askTreatmentID().toUpperCase();

        Treatment treatment = treatmentMap.getValue(treatmentId);
        Prescription prescription = getPrescription(treatmentId);
        //String treatmentID, String consultation_id, String disease, String treatment_advice, Date treatment_date, String staff_id, String patient_id,boolean isScan, String remark
        Treatment treatmentBackup = new Treatment(treatmentId, treatment.getConsultation_id(),treatment.getDisease(), treatment.getTreatment_advice(),treatment.getTreatment_date(),treatment.getStaff_id(),treatment.getPatient_id(), treatment.getIsScan(), treatment.getRemark());
        //String prescription_id, MapInterface<String, Medicine> medicineList,
//            String staff_id, String patient_id, String treatment_id
        Prescription prescriptionBackup = new Prescription(prescription.getPrescription_id(), prescription.getMedicineList(), prescription.getStaff_id(),prescription.getPatient_id(), prescription.getTreatment_id());
        
        saveHistory(treatmentBackup, prescriptionBackup, "Update");
        UI.showUpdatedTreatment(treatment, prescription, treatmentId, treatmentId);
        boolean con = true;
        do {
            int option = UI.updateMenu(treatmentId);
            switch (option) {
                case 1:
                    String advice = UI.askAdvice();
                    treatment.setTreatment_advice(advice);
                    break;
                case 2:
                    String disease = UI.askDisease();
                    treatment.setDisease(disease);
                    break;
                case 3:
                    String remark = UI.askRemark();
                    treatment.setRemark(remark);
                    break;
                case 4:
                    boolean isScan = UI.askIsScan();
                    treatment.setIsScan(isScan);
                    break;
                case 5:
                    Date date = UI.askTreatmentDate();
                    treatment.setTreatment_date(date);
                    break;
                case 6:
                    String prescription_id = prescription.getPrescription_id();
                    MapInterface<String, Medicine> medicineList = new ChainBucket<>();

                    Iterator<Medicine> iterMedicine = medicineMap.getIterator();
                    MapInterface<String, Medicine> medicine = new ChainBucket<>();
                    while (iterMedicine.hasNext()) {
                        Medicine m = iterMedicine.next();
                        String medicineName = m.getMedicineName();
                        int medicineStock = m.getMedicineStock();

                        if (medicine.containsKey(medicineName)) {
                            int totalStock = medicine.getValue(medicineName).getMedicineStock() + medicineStock;
                            medicine.put(medicineName, new Medicine(medicineName, totalStock));
                        } else {
                            medicine.put(medicineName, new Medicine(medicineName, medicineStock));
                        }
                    }

                    boolean cont = false;
                    do {
                        if (!medicine.isEmpty()) {
                            medicine.sorting();

                            Medicine tempMedicine = UI.askMedicine(medicine);
                            String tempMedicineName = tempMedicine.getMedicineName();
                            int preStock = medicine.getValue(tempMedicineName).getMedicineStock();
                            int usedStock = tempMedicine.getMedicineStock();

                            int newStock = preStock - usedStock;
                            if (medicineList.containsKey(tempMedicineName)) {
                                usedStock += medicineList.getValue(tempMedicineName).getMedicineStock();
                                tempMedicine.setMedicineStock(usedStock);
                                medicineList.put(tempMedicineName, tempMedicine);
                            } else {
                                medicineList.put(tempMedicineName, tempMedicine);
                            }

                            // update medicine stock
                            medicine.put(tempMedicineName, new Medicine(tempMedicineName, newStock));

                            String continueOption = UI.askToContinue().toUpperCase();
                            cont = continueOption.equals("Y");
                        }
                    } while (cont);
                    // call PharmacyManagement function pass in MapInterface<String, Medicine>

                    MapInterface<String, Medicine> medicineDetialList = new PharmacyManagementModule().getAvailableMedicine(medicineList);
                    
                    prescription.setMedicineList(medicineDetialList);
                    prescription.setMedicine_total_cost(prescription.calculateTotalCost());

                    // update Medicine Map
                    prescriptionMap.put(prescription_id, prescription);
                    medicineMap = Master.getMedicineMap();
                    break;
                default:
                    con = false;
                    break;
            }
        } while (con);
        UI.showUpdatedTreatment(treatment, prescription, treatmentId, treatmentId);
        
        treatmentMap.put(treatmentId, treatment);

        Master.setPrescriptionMap(prescriptionMap);
        Master.setTreatmentMap(treatmentMap);
    }

    // Delete Medical Treament Record
    @Override
    public void deleteInstance() {
        Object[] temp = treatmentMap.getAllKeys();
        boolean error = true;

        while (error) {
            String[] treatmentIdList = new String[treatmentMap.size()];
            for (int i = 0; i < treatmentIdList.length; i++) {
                String id = (String) temp[i];
                treatmentIdList[i] = id;
            }
            // show the delete interface
            UI.deleteTreatment();
            UI.showAllTreatmentID(treatmentIdList);
            String treatmentId = UI.askTreatmentID();
            treatmentId = treatmentId.toUpperCase();

            if (treatmentMap.containsKey(treatmentId)) {
                // find out the prescription
                Prescription prescription = getPrescription(treatmentId);
                Treatment treatment = treatmentMap.getValue(treatmentId);
                String staffName = getStaffName(treatment.getStaff_id());
                String patientName = getPatientName(treatment.getPatient_id());
                UI.removedTreatment(treatment, prescription, staffName, patientName);

                saveHistory(treatment, prescription, "Delete");
                // remove treatment and prescription
                treatmentMap.remove(treatmentId);
                prescriptionMap.remove(prescription.getPatient_id());
                error = false;
                scanner.nextLine();
            } else {
                error = true;
                UI.noRecord("Medical Treatment");
                UI.tryAgain();
            }
        }
    }

    private void diseaseAnalysisReport() {

        treatmentMap = Master.getTreatmentMap();
        patientMap = Master.getPatientMap();

        MapInterface<String, Integer> diseaseMap = new ChainBucket<>();
        MapInterface<String, Integer> ageRangeMap = new ChainBucket<>();
        MapInterface<String, Integer> caseCountMap = new ChainBucket<>();

        ageRangeMap.put("0-9", 0);
        ageRangeMap.put("10-19", 0);
        ageRangeMap.put("20-29", 0);
        ageRangeMap.put("30-39", 0);
        ageRangeMap.put("40-49", 0);
        ageRangeMap.put("50+", 0);

        diseaseMap.put("Hypertension", 0);
        diseaseMap.put("Diabetes Type 2", 0);
        diseaseMap.put("COVID-19", 0);
        diseaseMap.put("Asthma", 0);
        diseaseMap.put("Fracture", 0);

        caseCountMap.put("Female", 0);
        caseCountMap.put("Male", 0);

        String high = null, low = null, disease1 = null, disease2 = null, disease3 = null, ageRange1 = null, ageRange2 = null, ageRange3 = null;
        int highCase = 0, lowCase = 0, age1 = 0, age2 = 0, age3 = 0, numDisease1 = 0, numDisease2 = 0, numDisease3 = 0, countMale = 0, countFemale = 0;
        double highPercent = 0, lowPercent = 0, agePercent1 = 0, agePercent2 = 0, agePercent3 = 0, diseasePercent1 = 0, diseasePercent2 = 0, diseasePercent3 = 0;

        Iterator<Treatment> iterTreatment = treatmentMap.getIterator();
        while (iterTreatment.hasNext()) {
            Treatment treatmentTemp = iterTreatment.next();
            String patientId = treatmentTemp.getPatient_id();
            Patient patientTemp = patientMap.getValue(patientId);

            // put in malePatient and femalePatientMap
            if (patientTemp != null) {
                //count case group by gender
                String genderTemp = patientTemp.getPatient_gender();
                int prevNum = caseCountMap.getValue(genderTemp);
                int ageTemp = patientTemp.getAge();
                caseCountMap.put(genderTemp, prevNum + 1);

                if (genderTemp.equals("Female")) {
                    countFemale++;
                } else {
                    countMale++;
                }

                String ageRange;
                if (ageTemp >= 0 && ageTemp <= 9) {
                    ageRange = "0-9";
                } else if (ageTemp >= 10 && ageTemp <= 19) {
                    ageRange = "10-19";
                } else if (ageTemp >= 20 && ageTemp <= 29) {
                    ageRange = "20-29";
                } else if (ageTemp >= 30 && ageTemp <= 39) {
                    ageRange = "30-39";
                } else if (ageTemp >= 40 && ageTemp <= 49) {
                    ageRange = "40-49";
                } else {
                    ageRange = "50+";
                }
                prevNum = ageRangeMap.getValue(ageRange);
                ageRangeMap.put(ageRange, prevNum + 1);

                //count disease
                String diseaseName = treatmentTemp.getDisease();
                prevNum = diseaseMap.getValue(diseaseName);
                diseaseMap.put(diseaseName, prevNum + 1);
            }
        }

        diseaseMap.sorting();
        ageRangeMap.sorting();
        caseCountMap.sorting();

        high = caseCountMap.getFrontKey();
        highCase = caseCountMap.removeFirst();

        low = caseCountMap.getFrontKey();
        lowCase = caseCountMap.removeFirst();

        double totalCases = (double) lowCase + (double) highCase;
        highPercent = ((double) highCase / totalCases) * 100.0;
        lowPercent = ((double) lowCase / totalCases) * 100.0;

        double totalNumPatient = 0.0;
        Iterator<Integer> iterAge = ageRangeMap.getIterator();
        while (iterAge.hasNext()) {
            totalNumPatient += iterAge.next();
        }

        ageRange1 = ageRangeMap.getFrontKey();
        age1 = ageRangeMap.removeFirst();
        agePercent1 = (double) (age1 * 100.0) / totalNumPatient;
        ageRange2 = ageRangeMap.getFrontKey();
        age2 = ageRangeMap.removeFirst();
        agePercent2 = (double) (age2 * 100.0) / totalNumPatient;
        ageRange3 = ageRangeMap.getFrontKey();
        age3 = ageRangeMap.removeFirst();
        agePercent3 = (double) (age3 * 100.0) / totalNumPatient;

        double totalNumDisease = 0;
        Iterator<Integer> iterDisease = diseaseMap.getIterator();
        while (iterDisease.hasNext()) {
            totalNumDisease += iterDisease.next();
        }

        disease1 = diseaseMap.getFrontKey();
        numDisease1 = diseaseMap.removeFirst();
        diseasePercent1 = (double) (numDisease1 * 100.0) / totalNumDisease;
        disease2 = diseaseMap.getFrontKey();
        numDisease2 = diseaseMap.removeFirst();
        diseasePercent2 = (double) (numDisease2 * 100.0) / totalNumDisease;
        disease3 = diseaseMap.getFrontKey();
        numDisease3 = diseaseMap.removeFirst();
        diseasePercent3 = (double) (numDisease3 * 100.0) / totalNumDisease;

        UI.diseaseAnalysisReport(high, highCase, highPercent, low, lowCase, lowPercent,
                ageRange1, age1, agePercent1, ageRange2, age2, agePercent2, ageRange3, age3, agePercent3,
                disease1, numDisease1, diseasePercent1, disease2, numDisease2, diseasePercent2, disease3, numDisease3, diseasePercent3,
                countMale, countFemale);

        scanner.nextLine();
    }

    private void diseasePredictionAndRelationshipReport() {

        MapInterface<String, Treatment> treatmentTempMap;
        //Hypertension
        MapInterface<String, Patient> hypertensionMap;
        treatmentTempMap = treatmentMap.groupBy(new Treatment("Hypertension"));
        hypertensionMap = getPatientByDiseaseMap(treatmentTempMap);
        //Diabetes Type 2
        MapInterface<String, Patient> diabetesMap;
        treatmentTempMap = treatmentMap.groupBy(new Treatment("Diabetes Type 2"));
        diabetesMap = getPatientByDiseaseMap(treatmentTempMap);
        //COVID-19
        MapInterface<String, Patient> covidMap;
        treatmentTempMap = treatmentMap.groupBy(new Treatment("COVID-19"));
        covidMap = getPatientByDiseaseMap(treatmentTempMap);
        // Asthma
        MapInterface<String, Patient> asthmaMap;
        treatmentTempMap = treatmentMap.groupBy(new Treatment("Asthma"));
        asthmaMap = getPatientByDiseaseMap(treatmentTempMap);
        //Fracture
        MapInterface<String, Patient> fractureMap;
        treatmentTempMap = treatmentMap.groupBy(new Treatment("Fracture"));
        fractureMap = getPatientByDiseaseMap(treatmentTempMap);

        //Hypertension intersect Diabetes Type 2
        MapInterface<String, Patient> intersectHypertensionAndDiabetes = hypertensionMap.intersect(diabetesMap);
        //Hypertension intersect COVID-19
        MapInterface<String, Patient> intersectHypertensionAndCovid = hypertensionMap.intersect(covidMap);
        //Hypertension intersect Asthma
        MapInterface<String, Patient> intersectHypertensionAndAsthma = hypertensionMap.intersect(asthmaMap);
        //Hypertension intersect Fracture
        MapInterface<String, Patient> intersectHypertensionAndFracture = hypertensionMap.intersect(fractureMap);
        //Diabetes Type 2 intersect COVID-19
        MapInterface<String, Patient> intersectDiabetesAndCOVID = diabetesMap.intersect(covidMap);
        //Diabetes Type 2 intersect Asthma
        MapInterface<String, Patient> intersectDiabetesAndAsthma = diabetesMap.intersect(asthmaMap);
        //Diabetes Type 2 intersect Fracture
        MapInterface<String, Patient> intersectDiabetesAndFracture = diabetesMap.intersect(fractureMap);
        //COVID-19 intersect Asthma
        MapInterface<String, Patient> intersectCOVIDAndAsthma = covidMap.intersect(asthmaMap);
        //COVID-19 intersect Fracture
        MapInterface<String, Patient> intersectCOVIDAndFracture = covidMap.intersect(fractureMap);
        //Asthma intersect Fracture
        MapInterface<String, Patient> intersectAsthmaAndFracture = asthmaMap.intersect(fractureMap);

        // find the probability P(will X if have Y) = (X intersect Y) / total Y
        MapInterface<String, Double> hyperRelationship = new ChainBucket<>();
        MapInterface<String, Double> diabetesRelationship = new ChainBucket<>();
        MapInterface<String, Double> covidRelationship = new ChainBucket<>();
        MapInterface<String, Double> asthmaRelationship = new ChainBucket<>();
        MapInterface<String, Double> fractureRelationship = new ChainBucket<>();

        // hyper
        hyperRelationship.put("Diabetes Type 2", ralationshipBetweenDisease(hypertensionMap.size(), intersectHypertensionAndDiabetes.size()));
        hyperRelationship.put("COVID-19", ralationshipBetweenDisease(hypertensionMap.size(), intersectHypertensionAndCovid.size()));
        hyperRelationship.put("Asthma", ralationshipBetweenDisease(hypertensionMap.size(), intersectHypertensionAndAsthma.size()));
        hyperRelationship.put("Fracture", ralationshipBetweenDisease(hypertensionMap.size(), intersectHypertensionAndFracture.size()));

        // diabetes
        diabetesRelationship.put("Hypertension", ralationshipBetweenDisease(diabetesMap.size(), intersectHypertensionAndDiabetes.size()));
        diabetesRelationship.put("COVID-19", ralationshipBetweenDisease(diabetesMap.size(), intersectDiabetesAndCOVID.size()));
        diabetesRelationship.put("Asthma", ralationshipBetweenDisease(diabetesMap.size(), intersectDiabetesAndAsthma.size()));
        diabetesRelationship.put("Fracture", ralationshipBetweenDisease(diabetesMap.size(), intersectDiabetesAndFracture.size()));

        // covid
        covidRelationship.put("Hypertension", ralationshipBetweenDisease(covidMap.size(), intersectHypertensionAndCovid.size()));
        covidRelationship.put("Diabetes Type 2", ralationshipBetweenDisease(covidMap.size(), intersectDiabetesAndCOVID.size()));
        covidRelationship.put("Asthma", ralationshipBetweenDisease(covidMap.size(), intersectCOVIDAndAsthma.size()));
        covidRelationship.put("Fracture", ralationshipBetweenDisease(covidMap.size(), intersectCOVIDAndFracture.size()));

        //Asthma
        asthmaRelationship.put("Hypertension", ralationshipBetweenDisease(asthmaMap.size(), intersectHypertensionAndAsthma.size()));
        asthmaRelationship.put("Diabetes Type 2", ralationshipBetweenDisease(asthmaMap.size(), intersectDiabetesAndAsthma.size()));
        asthmaRelationship.put("COVID-19", ralationshipBetweenDisease(asthmaMap.size(), intersectCOVIDAndAsthma.size()));
        asthmaRelationship.put("Fracture", ralationshipBetweenDisease(asthmaMap.size(), intersectAsthmaAndFracture.size()));

        // Fracture
        fractureRelationship.put("Hypertension", ralationshipBetweenDisease(fractureMap.size(), intersectHypertensionAndFracture.size()));
        fractureRelationship.put("Diabetes Type 2", ralationshipBetweenDisease(fractureMap.size(), intersectDiabetesAndFracture.size()));
        fractureRelationship.put("COVID-19", ralationshipBetweenDisease(fractureMap.size(), intersectCOVIDAndFracture.size()));
        fractureRelationship.put("Asthma", ralationshipBetweenDisease(fractureMap.size(), intersectAsthmaAndFracture.size()));

        //Sorting
        hyperRelationship.sorting();
        diabetesRelationship.sorting();
        covidRelationship.sorting();
        asthmaRelationship.sorting();
        fractureRelationship.sorting();

        // Report
        UI.predictionRelationshipReportHeader();
        callRelationshipUI("Hypertension", hypertensionMap, hyperRelationship);
        callRelationshipUI("Diabetes Type 2", diabetesMap, diabetesRelationship);
        callRelationshipUI("COVID-19", covidMap, covidRelationship);
        callRelationshipUI("Asthma", asthmaMap, asthmaRelationship);
        callRelationshipUI("Fracture", fractureMap, fractureRelationship);

        scanner.nextLine();

    }

    private MapInterface<String, Patient> getPatientByDiseaseMap(MapInterface<String, Treatment> treatment) {
        MapInterface<String, Patient> patient = new ChainBucket<>();

        Iterator<Treatment> iter = treatment.getIterator();
        while (iter.hasNext()) {
            Treatment tempTreatment = iter.next();
            String patientId = tempTreatment.getPatient_id();
            if (patientMap.containsKey(patientId)) {
                patient.put(patientId, patientMap.getValue(patientId));
            }
        }

        return patient;
    }

    private void callRelationshipUI(String disease, MapInterface<String, Patient> patientList, MapInterface<String, Double> relationship) {
        UI.diseasePatientHeader(disease);
        while (!patientList.isEmpty()) {
            Patient temp = patientList.removeFirst();
            String id = temp.getPatient_id();
            String name = temp.getPatient_name();
            String contact = temp.getPatient_contact();
            String email = temp.getPatient_email();
//                    String id, String name, String contact, String email
            UI.diseasePatientDetials(id, name, contact, email);
        }
        UI.lineReport();
        while (!relationship.isEmpty()) {
            String tempDisease = relationship.getFrontKey();
            double percent = relationship.removeFirst();
            UI.diseaseRelationship(tempDisease, percent);
        }
        UI.lastLineReport();

    }

    // return the percentage of the probability
    private double ralationshipBetweenDisease(int total, int intersect) {
        return (double) (intersect / (double) total) * 100.0;
    }
}
