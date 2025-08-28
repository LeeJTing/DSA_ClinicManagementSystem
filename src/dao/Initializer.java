/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import adt.ChainBucket;
import adt.MapInterface;
import entity.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Initializer {

    public MapInterface<String, Treatment> medicalTreatmentInitializer() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        MapInterface<String, Treatment> treatmentMap = new ChainBucket<>();
        try {
            // String previousTreatmentID,String disease, String treatment_advice, Date treatment_date, String staff_id, boolean isScan, String remark
            treatmentMap.put("T000001", new Treatment("T000001", "C000001", "Hypertension", "Reduce salt intake, daily morning walk, medication prescribed",
                    sdf.parse("14-06-2025"), "S000001", "P000005", true, "Patient brought previous scan reports"));
            treatmentMap.put("T000002", new Treatment("T000002", "C000002", "Diabetes Type 2", "Start insulin therapy, monitor blood sugar twice daily",
                    sdf.parse("25-06-2025"), "S000002", "P000004", false, "Blood sugar fluctuating – advised strict diet"));
            treatmentMap.put("T000003", new Treatment("T000003", "C000003", "Asthma", "Inhaler prescribed, avoid allergens",
                    sdf.parse("05-07-2025"), "S000001", "P000003", true, "Chest X-ray scan done"));
            treatmentMap.put("T000004", new Treatment("T000004", "C000004", "COVID-19", "Antacid medication for 2 weeks, avoid spicy food",
                    sdf.parse("12-07-2025"), "S000003", "P000001", false, "Follow-up in 2 weeks"));
            treatmentMap.put("T000005", new Treatment("T000005", "C000005", "COVID-19", "Isolation advised, paracetamol for fever, hydration",
                    sdf.parse("20-07-2025"), "S000002", "P000002", true, "CT scan shows mild lung infection"));
            treatmentMap.put("T000006", new Treatment("T000006", "C000006", "Fracture", "Painkillers prescribed, advised to reduce screen time",
                    sdf.parse("22-07-2025"), "S000004", "P000006", false, "Referred to neurologist if pain persists"));
            treatmentMap.put("T000007", new Treatment("T000007", "C000007", "Asthma", "Iron supplements prescribed, increase iron-rich foods",
                    sdf.parse("23-07-2025"), "S000006", "P000007", false, "Patient advised to return for blood test"));
            treatmentMap.put("T000008", new Treatment("T000008", "C000008", "Fracture", "Arm cast applied, x-ray review in 3 weeks",
                    sdf.parse("24-07-2025"), "S000005", "P000008", true, "X-ray confirmed non-displaced fracture"));
            treatmentMap.put("T000009", new Treatment("T000009", "C000009", "Fracture", "Antibiotics prescribed for 7 days, warm saline gargle",
                    sdf.parse("25-07-2025"), "S000003", "P000009", false, "Mild swelling observed, no scan needed"));
            treatmentMap.put("T000010", new Treatment("T000010", "C000010", "Hypertension", "Start statins, low-fat diet advised",
                    sdf.parse("26-07-2025"), "S000007", "P000010", true, "Lipid profile test results attached"));
            treatmentMap.put("T000011", new Treatment("T000011", "C000011", "Diabetes Type 2", "Adjust insulin dosage, continue monitoring blood sugar",
                    sdf.parse("27-07-2025"), "S000002", "P000004", true, "HbA1c test scheduled"));
            treatmentMap.put("T000012", new Treatment("T000012", "C000012", "Asthma", "Prescribed steroid inhaler, advised to avoid dust exposure",
                    sdf.parse("28-07-2025"), "S000006", "P000003", false, "Peak flow meter reading taken"));
            treatmentMap.put("T000013", new Treatment("T000013", "C000013", "Fracture", "Plaster cast reapplied, follow-up after 1 month",
                    sdf.parse("29-07-2025"), "S000005", "P000009", true, "Bone healing progress observed in X-ray"));
            treatmentMap.put("T000014", new Treatment("T000014", "C000014", "COVID-19", "Isolation extended, vitamins prescribed",
                    sdf.parse("30-07-2025"), "S000003", "P000001", true, "RT-PCR test still positive"));
            treatmentMap.put("T000015", new Treatment("T000015", "C000015", "Hypertension", "Blood pressure medication dosage increased",
                    sdf.parse("31-07-2025"), "S000001", "P000010", false, "Regular monitoring advised"));
            treatmentMap.put("T000016", new Treatment("T000016", "C000016", "Diabetes Type 2", "Oral medication prescribed, diet reinforced",
                    sdf.parse("01-08-2025"), "S000002", "P000002", false, "Patient reluctant to use insulin"));
            treatmentMap.put("T000017", new Treatment("T000017", "C000017", "Asthma", "Nebulization therapy provided, avoid cold drinks",
                    sdf.parse("02-08-2025"), "S000001", "P000007", true, "Wheezing reduced after therapy"));
            treatmentMap.put("T000018", new Treatment("T000018", "C000018", "Hypertension", "Encouraged weight reduction, exercise plan given",
                    sdf.parse("03-08-2025"), "S000007", "P000005", true, "Cholesterol test scheduled"));
            treatmentMap.put("T000019", new Treatment("T000019", "C000019", "COVID-19", "Advised hydration, mild symptoms monitored",
                    sdf.parse("04-08-2025"), "S000004", "P000008", false, "Patient recovering, oxygen level stable"));
            treatmentMap.put("T000020", new Treatment("T000020", "C000020", "Fracture", "Physiotherapy sessions recommended",
                    sdf.parse("05-08-2025"), "S000005", "P000006", true, "Strengthening exercises demonstrated"));

        } catch (ParseException ex) {
            Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return treatmentMap;
    }

    public MapInterface<String, Prescription> prescriptionInitializer() {
        MapInterface<String, Prescription> prescriptionMap = new ChainBucket<>();

        MapInterface<String, Medicine> medicineMap1 = new ChainBucket<>();
        MapInterface<String, Medicine> medicineMap2 = new ChainBucket<>();
        MapInterface<String, Medicine> medicineMap3 = new ChainBucket<>();
        MapInterface<String, Medicine> medicineMap4 = new ChainBucket<>();
        MapInterface<String, Medicine> medicineMap5 = new ChainBucket<>();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date expiryDate = sdf.parse("10-10-2030");

            //String medicineID, String medicineName, String medicineCategory, Date expiryDate, int medicineStock, double medicineUnitPrice
            medicineMap1.put("M000001", new Medicine("M000001", "Paracetamol", "Analgesics (Painkillers)", expiryDate, 2, 5.0));
            medicineMap2.put("M000002", new Medicine("M000002", "Ibuprofen", "Analgesics (Painkillers)", expiryDate, 2, 5.2));
            medicineMap3.put("M000003", new Medicine("M000003", "Amoxicillin", "Antibiotics", expiryDate, 2, 13.5));
            medicineMap4.put("M000004", new Medicine("M000004", "Paracetamol", "Antipyretics (Fever Reducers)", expiryDate, 2, 5.0));
            medicineMap5.put("M000005", new Medicine("M000005", "Hydrogen Peroxide", "Antiseptics & Disinfectants", expiryDate, 1, 18.3));
            medicineMap1.put("M000006", new Medicine("M000006", "Iodine", "Antiseptics & Disinfectants", expiryDate, 9, 4.0));
            medicineMap2.put("M000007", new Medicine("M000007", "Larotadine", "Antihistamines (Allergy Relief)", expiryDate, 2, 15.0));
            medicineMap3.put("M000008", new Medicine("M000008", "Cetirizine", "Antihistamines (Allergy Relief)", expiryDate, 1, 13.0));
            medicineMap4.put("M000009", new Medicine("M000009", "Dextromethorphan", "Cough & Cold Remedies", expiryDate, 3, 7.0));
            medicineMap5.put("M000010", new Medicine("M000010", "Naproxen", "Anti-inflammatory Drugs", expiryDate, 1, 9.7));

            //String prescription_id, LinkedHashMap<String, Medicine> medicineList, String staff_id, String patient_id, String treatment_id
            prescriptionMap.put("PH000001", new Prescription("PH000001", medicineMap1, "S000001", "P000005", "T000001"));
            prescriptionMap.put("PH000002", new Prescription("PH000002", medicineMap2, "S000002", "P000004", "T000002"));
            prescriptionMap.put("PH000003", new Prescription("PH000003", medicineMap3, "S000001", "P000003", "T000003"));
            prescriptionMap.put("PH000004", new Prescription("PH000004", medicineMap4, "S000003", "P000001", "T000004"));
            prescriptionMap.put("PH000005", new Prescription("PH000005", medicineMap5, "S000002", "P000002", "T000005"));
            prescriptionMap.put("PH000006", new Prescription("PH000006", medicineMap1, "S000004", "P000006", "T000006"));
            prescriptionMap.put("PH000007", new Prescription("PH000007", medicineMap2, "S000006", "P000007", "T000007"));
            prescriptionMap.put("PH000008", new Prescription("PH000008", medicineMap3, "S000005", "P000008", "T000008"));
            prescriptionMap.put("PH000009", new Prescription("PH000009", medicineMap4, "S000003", "P000009", "T000009"));
            prescriptionMap.put("PH000010", new Prescription("PH000010", medicineMap5, "S000007", "P000010", "T000010"));
            prescriptionMap.put("PH000011", new Prescription("PH000011", medicineMap1, "S000002", "P000004", "T000011"));
            prescriptionMap.put("PH000012", new Prescription("PH000012", medicineMap2, "S000006", "P000003", "T000012"));
            prescriptionMap.put("PH000013", new Prescription("PH000013", medicineMap3, "S000005", "P000009", "T000013"));
            prescriptionMap.put("PH000014", new Prescription("PH000014", medicineMap4, "S000003", "P000001", "T000014"));
            prescriptionMap.put("PH000015", new Prescription("PH000015", medicineMap5, "S000001", "P000010", "T000015"));
            prescriptionMap.put("PH000016", new Prescription("PH000016", medicineMap1, "S000002", "P000002", "T000016"));
            prescriptionMap.put("PH000017", new Prescription("PH000017", medicineMap2, "S000001", "P000007", "T000017"));
            prescriptionMap.put("PH000018", new Prescription("PH000018", medicineMap3, "S000007", "P000005", "T000018"));
            prescriptionMap.put("PH000019", new Prescription("PH000019", medicineMap4, "S000004", "P000008", "T000019"));
            prescriptionMap.put("PH000020", new Prescription("PH000020", medicineMap5, "S000005", "P000006", "T000020"));

        } catch (ParseException ex) {
            Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prescriptionMap;
    }

    // initialize the medicine dummy data
    public MapInterface<String, Medicine> medicineInitializer() {
        MapInterface<String, Medicine> medicineMap = new ChainBucket<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date expiryDate = sdf.parse("10-10-2030");
            Date expiryDate2 = sdf.parse("20-09-2025");
            Date expiryDate3 = sdf.parse("20-07-2025");

            medicineMap.put("M000001", new Medicine("M000001", "Paracetamol", "Analgesics (Painkillers)", expiryDate2, 100, 5.0));
            medicineMap.put("M000002", new Medicine("M000002", "Ibuprofen", "Analgesics (Painkillers)", expiryDate, 100, 5.2));
            medicineMap.put("M000003", new Medicine("M000003", "Amoxicillin", "Antibiotics", expiryDate, 20, 13.5));
            medicineMap.put("M000004", new Medicine("M000004", "Paracetamol", "Antipyretics (Fever Reducers)", expiryDate, 200, 5.0));
            medicineMap.put("M000005", new Medicine("M000005", "Hydrogen Peroxide", "Antiseptics & Disinfectants", expiryDate, 110, 18.3));
            medicineMap.put("M000006", new Medicine("M000006", "Iodine", "Antiseptics & Disinfectants", expiryDate3, 90, 4.0));
            medicineMap.put("M000007", new Medicine("M000007", "Larotadine", "Antihistamines (Allergy Relief)", expiryDate, 220, 15.0));
            medicineMap.put("M000008", new Medicine("M000008", "Cetirizine", "Antihistamines (Allergy Relief)", expiryDate, 190, 13.0));
            medicineMap.put("M000009", new Medicine("M000009", "Dextromethorphan", "Cough & Cold Remedies", expiryDate, 300, 7.0));
            medicineMap.put("M000010", new Medicine("M000010", "Naproxen", "Anti-inflammatory Drugs", expiryDate, 100, 9.7));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return medicineMap;
    }

    // initialize the payment dummy data
    public MapInterface<String, Payment> paymentInitializer() {
        MapInterface<String, Payment> paymentMap = new ChainBucket<>();
        MapInterface<String, Prescription> prescriptionMap = prescriptionInitializer();
        MapInterface<String, Consultation> consultationMap = consultationInitializer();
        MapInterface<String, Treatment> treatmentMap = medicalTreatmentInitializer();
        MapInterface<String, Medicine> medicineMap = medicineInitializer();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
//            String paymentID, String patientID, Prescription prescription, double consultationCost, double totalCost, Date paymentTime
            paymentMap.put("PM000001", new Payment("PM000001", "P000001", prescriptionMap.getValue("PH000001"), 0.0, 0.0, sdf.parse("01-07-2025 12:00:06")));
            paymentMap.put("PM000002", new Payment("PM000002", "P000002", prescriptionMap.getValue("PH000002"), 0.0, 0.0, sdf.parse("02-07-2025 17:10:10")));
            paymentMap.put("PM000003", new Payment("PM000003", "P000003", prescriptionMap.getValue("PH000003"), 0.0, 0.0, sdf.parse("03-07-2025 14:25:03")));
            paymentMap.put("PM000004", new Payment("PM000004", "P000004", prescriptionMap.getValue("PH000004"), 0.0, 0.0, sdf.parse("04-07-2025 15:56:30")));
            paymentMap.put("PM000005", new Payment("PM000005", "P000005", prescriptionMap.getValue("PH000005"), 0.0, 0.0, sdf.parse("05-07-2025 10:04:45")));
            paymentMap.put("PM000006", new Payment("PM000006", "P000006", prescriptionMap.getValue("PH000006"), 0.0, 0.0, sdf.parse("05-07-2025 11:19:22")));
            paymentMap.put("PM000007", new Payment("PM000007", "P000007", prescriptionMap.getValue("PH000007"), 0.0, 0.0, sdf.parse("07-07-2025 13:05:14")));
            paymentMap.put("PM000008", new Payment("PM000008", "P000008", prescriptionMap.getValue("PH000008"), 0.0, 0.0, sdf.parse("08-07-2025 14:30:31")));
            paymentMap.put("PM000009", new Payment("PM000009", "P000009", prescriptionMap.getValue("PH000009"), 0.0, 0.0, sdf.parse("09-07-2025 16:29:44")));
            paymentMap.put("PM000010", new Payment("PM000010", "P000010", prescriptionMap.getValue("PH000010"), 0.0, 0.0, sdf.parse("10-07-2025 14:40:02")));
            paymentMap.put("PM000011", new Payment("PM000011", "P000004", prescriptionMap.getValue("PH000011"), 0.0, 0.0, sdf.parse("27-07-2025 12:15:33")));
            paymentMap.put("PM000012", new Payment("PM000012", "P000003", prescriptionMap.getValue("PH000012"), 0.0, 0.0, sdf.parse("28-07-2025 15:20:18")));
            paymentMap.put("PM000013", new Payment("PM000013", "P000009", prescriptionMap.getValue("PH000013"), 0.0, 0.0, sdf.parse("29-07-2025 11:45:27")));
            paymentMap.put("PM000014", new Payment("PM000014", "P000001", prescriptionMap.getValue("PH000014"), 0.0, 0.0, sdf.parse("30-07-2025 16:10:39")));
            paymentMap.put("PM000015", new Payment("PM000015", "P000010", prescriptionMap.getValue("PH000015"), 0.0, 0.0, sdf.parse("31-07-2025 09:30:55")));
            paymentMap.put("PM000017", new Payment("PM000017", "P000007", prescriptionMap.getValue("PH000017"), 0.0, 0.0, sdf.parse("02-08-2025 13:20:48")));
            paymentMap.put("PM000018", new Payment("PM000018", "P000005", prescriptionMap.getValue("PH000018"), 0.0, 0.0, sdf.parse("03-08-2025 14:35:21")));
            paymentMap.put("PM000019", new Payment("PM000019", "P000008", prescriptionMap.getValue("PH000019"), 0.0, 0.0, sdf.parse("04-08-2025 15:50:09")));
            paymentMap.put("PM000020", new Payment("PM000020", "P000006", prescriptionMap.getValue("PH000020"), 0.0, 0.0, sdf.parse("05-08-2025 16:25:37")));

            Object[] paymentObjects = paymentMap.getAllValues();
            MapInterface<String, Medicine> medicineListInPrescription;
            for (Object paymentObj : paymentObjects) {
                Payment pm = (Payment) paymentObj;

                Prescription p = prescriptionMap.getValue(pm.getPrescriptionID());
                if (p == null) {
                    continue;
                }

                Treatment t = treatmentMap.getValue(p.getTreatment_id());
                if (t == null) {
                    continue;
                }

                Consultation c = consultationMap.getValue(t.getConsultation_id());
                if (c == null || !"Completed".equals(c.getAppointmentStatus())) {
                    continue;
                }

                medicineListInPrescription = p.getMedicineList();
                Object[] prescriptionMedicine = medicineListInPrescription.getAllValues();
                Object[] medicineMapMedicine = medicineMap.getAllValues();

                for (Object pMedicine : prescriptionMedicine) {
                    for (Object mMedicine : medicineMapMedicine) {
                        Medicine m = (Medicine) pMedicine;
                        Medicine mm = (Medicine) mMedicine;
                        if (m.getMedicineID().equals(mm.getMedicineID())) {
                            mm.updateMedicineStock(m.getMedicineStock());
                        }
                    }

                }

                double consultationCost = c.calConsultationCost();
                pm.calTotalCost(consultationCost);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return paymentMap;
    }

    //initialize the patient dummy data
    public MapInterface<String, Patient> patientInitializer() {
        MapInterface<String, Patient> patientMap = new ChainBucket<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        try {
            Patient p1 = new Patient("P000001", "Adam Lee", "0123456789", "adam@gmail.com", "Male", 25, sdf.parse("24-07-2025 12:00"));
            p1.addVisit(sdf.parse("26-08-2025 09:00"), sdf.parse("26-08-2025 09:30"));
            p1.addVisit(sdf.parse("17-08-2025 11:00"), sdf.parse("17-08-2025 12:00"));

            Patient p2 = new Patient("P000002", "Betty Tan", "0112233445", "betty@gmail.com", "Female", 30, sdf.parse("24-07-2025 13:30"));
            p2.addVisit(sdf.parse("26-08-2025 09:25"), sdf.parse("26-08-2025 10:00"));
            p2.addVisit(sdf.parse("17-08-2025 14:25"), sdf.parse("17-08-2025 15:00"));

            Patient p3 = new Patient("P000003", "Charlie Goh", "0103344556", "charlie@gmail.com", "Male", 22, sdf.parse("25-07-2025 10:20"));
            p3.addVisit(sdf.parse("27-08-2025 10:15"), sdf.parse("27-08-2025 11:23"));

            Patient p4 = new Patient("P000004", "Diana Lim", "0167788990", "diana@gmail.com", "Female", 28, sdf.parse("25-07-2025 09:50"));
            p4.addVisit(sdf.parse("27-08-2025 13:35"), sdf.parse("27-08-2025 14:12"));

            Patient p5 = new Patient("P000005", "Ethan Yong", "0188899776", "ethan@gmail.com", "Male", 27, sdf.parse("25-07-2025 11:10"));
            p5.addVisit(sdf.parse("27-08-2025 15:10"), sdf.parse("27-08-2025 15:30"));

            Patient p6 = new Patient("P000006", "Fiona Cheah", "0198877665", "fiona@gmail.com", "Female", 35, sdf.parse("26-07-2025 14:00"));
            p6.addVisit(sdf.parse("28-08-2025 09:30"), sdf.parse("28-08-2025 09:50"));

            Patient p7 = new Patient("P000007", "Gavin Ong", "0177766554", "gavin@gmail.com", "Male", 26, sdf.parse("26-07-2025 14:10"));
            p7.addVisit(sdf.parse("28-08-2025 09:20"), sdf.parse("28-08-2025 09:30"));

            Patient p8 = new Patient("P000008", "Hannah Yap", "0135566778", "hannah@gmail.com", "Female", 24, sdf.parse("26-07-2025 15:45"));
            p8.addVisit(sdf.parse("28-08-2025 09:40"), sdf.parse("28-08-2025 10:00"));

            Patient p9 = new Patient("P000009", "Ivan Lim", "0129988776", "ivan@gmail.com", "Male", 29, sdf.parse("27-07-2025 11:35"));
            p9.addVisit(sdf.parse("17-08-2025 10:45"), sdf.parse("17-08-2025 11:00"));

            Patient p10 = new Patient("P000010", "Joanne Teo", "0117788665", "joanne@gmail.com", "Female", 31, sdf.parse("27-07-2025 12:10"));
            p10.addVisit(sdf.parse("17-08-2025 11:45"), sdf.parse("17-08-2025 12:00"));

            Patient p11 = new Patient("P000011", "Monday Patient 1", "0111111111", "monday1@clinic.com", "Male", 40, sdf.parse("30-07-2025 08:00"));
            p11.addVisit(sdf.parse("04-08-2025 09:00"), sdf.parse("04-08-2025 09:30"));

            Patient p12 = new Patient("P000012", "Monday Patient 2", "0111111112", "monday2@clinic.com", "Female", 35, sdf.parse("30-07-2025 09:00"));
            p12.addVisit(sdf.parse("04-08-2025 09:30"), sdf.parse("04-08-2025 10:30"));

            Patient p13 = new Patient("P000013", "Kelvin Chia", "0123451122", "kelvin@gmail.com", "Male", 33, sdf.parse("30-07-2025 10:20"));
            p13.addVisit(sdf.parse("28-08-2025 10:15"), sdf.parse("28-08-2025 10:45"));
            p13.addVisit(sdf.parse("18-08-2025 09:40"), sdf.parse("18-08-2025 10:10"));

            Patient p14 = new Patient("P000014", "Laura Ng", "0112211445", "laura@gmail.com", "Female", 29, sdf.parse("30-07-2025 11:00"));
            p14.addVisit(sdf.parse("28-08-2025 11:20"), sdf.parse("28-08-2025 11:50"));

            Patient p15 = new Patient("P000015", "Marcus Tan", "0164455778", "marcus@gmail.com", "Male", 27, sdf.parse("31-07-2025 09:40"));
            p15.addVisit(sdf.parse("29-08-2025 09:00"), sdf.parse("29-08-2025 09:15"));

            Patient p16 = new Patient("P000016", "Natalie Wong", "0177788112", "natalie@gmail.com", "Female", 24, sdf.parse("31-07-2025 10:15"));
            p16.addVisit(sdf.parse("29-08-2025 09:50"), sdf.parse("29-08-2025 10:30"));
            p16.addVisit(sdf.parse("20-08-2025 14:20"), sdf.parse("20-08-2025 14:55"));

            Patient p17 = new Patient("P000017", "Oscar Lim", "0193344556", "oscar@gmail.com", "Male", 30, sdf.parse("31-07-2025 11:35"));
            p17.addVisit(sdf.parse("29-08-2025 11:00"), sdf.parse("29-08-2025 11:30"));

            Patient p18 = new Patient("P000018", "Priscilla Yeo", "0135566442", "priscilla@gmail.com", "Female", 28, sdf.parse("01-08-2025 09:25"));
            p18.addVisit(sdf.parse("30-08-2025 09:00"), sdf.parse("30-08-2025 09:20"));

            Patient p19 = new Patient("P000019", "Quincy Chan", "0128899774", "quincy@gmail.com", "Male", 34, sdf.parse("01-08-2025 10:50"));
            p19.addVisit(sdf.parse("30-08-2025 09:50"), sdf.parse("30-08-2025 10:25"));
            p19.addVisit(sdf.parse("21-08-2025 15:10"), sdf.parse("21-08-2025 15:40"));

            Patient p20 = new Patient("P000020", "Rachel Lee", "0147788990", "rachel@gmail.com", "Female", 26, sdf.parse("01-08-2025 11:15"));
            p20.addVisit(sdf.parse("30-08-2025 11:30"), sdf.parse("30-08-2025 12:00"));

            Patient p21 = new Patient("P000021", "Samuel Koh", "0187766553", "samuel@gmail.com", "Male", 32, sdf.parse("02-08-2025 09:40"));
            p21.addVisit(sdf.parse("31-08-2025 09:00"), sdf.parse("31-08-2025 09:35"));

            Patient p22 = new Patient("P000022", "Tiffany Lau", "0193344776", "tiffany@gmail.com", "Female", 23, sdf.parse("02-08-2025 10:25"));
            p22.addVisit(sdf.parse("31-08-2025 09:50"), sdf.parse("31-08-2025 10:20"));

            patientMap.put(p1.getPatient_id(), p1);
            patientMap.put(p2.getPatient_id(), p2);
            patientMap.put(p3.getPatient_id(), p3);
            patientMap.put(p4.getPatient_id(), p4);
            patientMap.put(p5.getPatient_id(), p5);
            patientMap.put(p6.getPatient_id(), p6);
            patientMap.put(p7.getPatient_id(), p7);
            patientMap.put(p8.getPatient_id(), p8);
            patientMap.put(p9.getPatient_id(), p9);
            patientMap.put(p10.getPatient_id(), p10);
            patientMap.put(p11.getPatient_id(), p11);
            patientMap.put(p12.getPatient_id(), p12);
            patientMap.put(p13.getPatient_id(), p13);
            patientMap.put(p14.getPatient_id(), p14);
            patientMap.put(p15.getPatient_id(), p15);
            patientMap.put(p16.getPatient_id(), p16);
            patientMap.put(p17.getPatient_id(), p17);
            patientMap.put(p18.getPatient_id(), p18);
            patientMap.put(p19.getPatient_id(), p19);
            patientMap.put(p20.getPatient_id(), p20);
            patientMap.put(p21.getPatient_id(), p21);
            patientMap.put(p22.getPatient_id(), p22);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return patientMap;
    }

    //initialize the Consultation dummy data 
    public MapInterface<String, Consultation> consultationInitializer() {
        MapInterface<String, Consultation> consultMap = new ChainBucket<>();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

        try {
            consultMap.put("C000001", new Consultation("C000001", sdf1.parse("22-08-2025"), sdf1.parse("22-08-2025"), sdf2.parse("16:30"), sdf2.parse("17:00"), "Pending", "Online", "P000001", "S000006"));
            consultMap.put("C000002", new Consultation("C000002", sdf1.parse("22-08-2025"), sdf1.parse("22-08-2025"), sdf2.parse("10:00"), sdf2.parse("11:00"), "Pending", "Online", "P000002", "S000005"));
            consultMap.put("C000003", new Consultation("C000003", sdf1.parse("22-08-2025"), sdf1.parse("22-08-2025"), sdf2.parse("11:00"), sdf2.parse("11:30"), "Pending", "Online", "P000003", "S000005"));
            consultMap.put("C000004", new Consultation("C000004", sdf1.parse("22-08-2025"), sdf1.parse("22-08-2025"), sdf2.parse("14:00"), sdf2.parse("15:00"), "Pending", "Online", "P000004", "S000004"));
            consultMap.put("C000005", new Consultation("C000005", sdf1.parse("22-08-2025"), sdf1.parse("22-08-2025"), sdf2.parse("15:30"), sdf2.parse("16:00"), "Completed", "Online", "P000005", "S000005"));
            consultMap.put("C000006", new Consultation("C000006", sdf1.parse("22-08-2025"), sdf1.parse("23-08-2025"), sdf2.parse("10:00"), sdf2.parse("11:00"), "Completed", "Walk-In", "P000006", "S000001"));
            consultMap.put("C000007", new Consultation("C000007", sdf1.parse("22-08-2025"), sdf1.parse("23-08-2025"), sdf2.parse("09:30"), sdf2.parse("10:00"), "Completed", "Walk-In", "P000007", "S000002"));
            consultMap.put("C000008", new Consultation("C000008", sdf1.parse("22-08-2025"), sdf1.parse("24-08-2025"), sdf2.parse("10:00"), sdf2.parse("11:00"), "Completed", "Walk-In", "P000008", "S000004"));
            consultMap.put("C000009", new Consultation("C000009", sdf1.parse("22-08-2025"), sdf1.parse("24-08-2025"), sdf2.parse("11:00"), sdf2.parse("12:00"), "Completed", "Walk-In", "P000009", "S000005"));
            consultMap.put("C000010", new Consultation("C000010", sdf1.parse("22-08-2025"), sdf1.parse("24-08-2025"), sdf2.parse("12:00"), sdf2.parse("12:30"), "Completed", "Walk-In", "P000010", "S000006"));
            consultMap.put("C000011", new Consultation("C000011", sdf1.parse("23-08-2025"), sdf1.parse("25-08-2025"), sdf2.parse("13:00"), sdf2.parse("13:30"), "Completed", "Online", "P000004", "S000001"));
            consultMap.put("C000012", new Consultation("C000012", sdf1.parse("23-08-2025"), sdf1.parse("25-08-2025"), sdf2.parse("14:00"), sdf2.parse("14:30"), "Completed", "Walk-In", "P000003", "S000002"));
            consultMap.put("C000013", new Consultation("C000013", sdf1.parse("23-08-2025"), sdf1.parse("25-08-2025"), sdf2.parse("15:00"), sdf2.parse("15:30"), "Completed", "Online", "P000009", "S000003"));
            consultMap.put("C000014", new Consultation("C000014", sdf1.parse("23-08-2025"), sdf1.parse("25-08-2025"), sdf2.parse("16:00"), sdf2.parse("16:30"), "Completed", "Walk-In", "P000001", "S000001"));
            consultMap.put("C000015", new Consultation("C000015", sdf1.parse("23-08-2025"), sdf1.parse("25-08-2025"), sdf2.parse("09:00"), sdf2.parse("09:30"), "Completed", "Online", "P000010", "S000002"));
            consultMap.put("C000016", new Consultation("C000016", sdf1.parse("24-08-2025"), sdf1.parse("26-08-2025"), sdf2.parse("10:00"), sdf2.parse("10:30"), "Completed", "Walk-In", "P000002", "S000004"));
            consultMap.put("C000017", new Consultation("C000017", sdf1.parse("24-08-2025"), sdf1.parse("26-08-2025"), sdf2.parse("11:00"), sdf2.parse("11:30"), "Completed", "Online", "P000007", "S000005"));
            consultMap.put("C000018", new Consultation("C000018", sdf1.parse("24-08-2025"), sdf1.parse("26-08-2025"), sdf2.parse("14:00"), sdf2.parse("14:30"), "Completed", "Walk-In", "P000005", "S000005"));
            consultMap.put("C000019", new Consultation("C000019", sdf1.parse("24-08-2025"), sdf1.parse("26-08-2025"), sdf2.parse("15:00"), sdf2.parse("15:30"), "Completed", "Online", "P000008", "S000004"));
            consultMap.put("C000020", new Consultation("C000020", sdf1.parse("24-08-2025"), sdf1.parse("27-08-2025"), sdf2.parse("16:00"), sdf2.parse("16:30"), "Completed", "Walk-In", "P000006", "S000005"));
            consultMap.put("C000021", new Consultation("C000021", sdf1.parse("25-08-2025"), sdf1.parse("27-08-2025"), sdf2.parse("09:00"), sdf2.parse("00:00"), "On-going", "Walk-In", "P000003", "S000001"));
            consultMap.put("C000022", new Consultation("C000022", sdf1.parse("25-08-2025"), sdf1.parse("27-08-2025"), sdf2.parse("10:00"), sdf2.parse("11:00"), "Pending", "Online", "P000002", "S000002"));
            consultMap.put("C000023", new Consultation("C000023", sdf1.parse("26-08-2025"), sdf1.parse("28-08-2025"), sdf2.parse("09:00"), sdf2.parse("00:00"), "On-going", "Walk-In", "P000004", "S000004"));
            consultMap.put("C000024", new Consultation("C000024", sdf1.parse("26-08-2025"), sdf1.parse("28-08-2025"), sdf2.parse("10:00"), sdf2.parse("11:00"), "Pending", "Online", "P000002", "S000005"));
            consultMap.put("C000025", new Consultation("C000025", sdf1.parse("27-08-2025"), sdf1.parse("29-08-2025"), sdf2.parse("10:00"), sdf2.parse("11:00"), "Pending", "Flw-up", "P000003", "S000001"));
            consultMap.put("C000026", new Consultation("C000026", sdf1.parse("27-08-2025"), sdf1.parse("29-08-2025"), sdf2.parse("11:00"), sdf2.parse("12:00"), "Pending", "Flw-up", "P000004", "S000002"));
            consultMap.put("C000027", new Consultation("C000027", sdf1.parse("27-08-2025"), sdf1.parse("29-08-2025"), sdf2.parse("12:00"), sdf2.parse("14:00"), "Pending", "Flw-up", "P000005", "S000003"));
            consultMap.put("C000028", new Consultation("C000028", sdf1.parse("28-08-2025"), sdf1.parse("30-08-2025"), sdf2.parse("10:00"), sdf2.parse("11:00"), "Pending", "Flw-up", "P000003", "S000004"));
            consultMap.put("C000029", new Consultation("C000029", sdf1.parse("28-08-2025"), sdf1.parse("30-08-2025"), sdf2.parse("11:00"), sdf2.parse("12:00"), "Pending", "Flw-up", "P000004", "S000005"));
            consultMap.put("C000030", new Consultation("C000030", sdf1.parse("28-08-2025"), sdf1.parse("30-08-2025"), sdf2.parse("12:00"), sdf2.parse("14:00"), "Pending", "Flw-up", "P000005", "S000006"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return consultMap;
    }

    public MapInterface<String, Staff> staffInitializer() {
        MapInterface<String, Staff> staffMap = new ChainBucket<>();

        // staff_id, staff_password, staff_name, staff_position, staff_contact, staff_email,
        // education_level, service_duration, dutyStatus, joined_date, pendingLeaveDate
        staffMap.put("S000001", new Staff("S000001", "pass123", "John Smith", "Doctor", "012-3456789", "john.smith@hospital.com", 1, 10, "Work", "12-12-2018"));
        staffMap.put("S000002", new Staff("S000002", "pass123", "Emily Davis", "Doctor", "012-3456789", "emily.davis@hospital.com", 3, 10, "Work", "20-07-2020"));
        staffMap.put("S000003", new Staff("S000003", "pass789", "Michael Lee", "Doctor", "011-2233445", "michael.lee@hospital.com", 3, 12, "Work", "11-05-2018"));
        staffMap.put("S000004", new Staff("S000004", "pass987", "Sophia Wong", "Doctor", "014-5566778", "sophia.wong@hospital.com", 4, 8, "Work", "25-04-2014"));
        staffMap.put("S000005", new Staff("S000005", "pass654", "David Tan", "Doctor", "010-9988776", "david.tan@hospital.com", 2, 5, "Work", "15-09-2015"));
        staffMap.put("S000006", new Staff("S000006", "pass155", "Jacksong Tee", "Doctor", "018-7988776", "jacksonTee@hospital.com", 2, 5, "Work", "30-12-2015"));
        staffMap.put("S000007", new Staff("S000007", "pass111", "Esther Yong", "Pharmacist", "012-5988776", "Esther@hospital.com", 2, 5, "Leave", "12-01-2015"));

        return staffMap;
    }

    public MapInterface<Integer, String> doctorA_Initializer() {
        MapInterface<Integer, String> doctorAMap = new ChainBucket<>();
        doctorAMap.put(1, "S000001");
        doctorAMap.put(2, "S000002");
        doctorAMap.put(3, "S000003");

        return doctorAMap;
    }

    public MapInterface<Integer, String> doctorB_Initializer() {
        MapInterface<Integer, String> doctorBMap = new ChainBucket<>();
        doctorBMap.put(1, "S000004");
        doctorBMap.put(2, "S000005");
        doctorBMap.put(3, "S000006");

        return doctorBMap;
    }

    public MapInterface<LocalDate, MapInterface<Integer, String>> dutySchedule_Initializer() {
        MapInterface<LocalDate, MapInterface<Integer, String>> dutyScheduleMap = new ChainBucket<>();

        int year = 2025;
        int month = 9; // August
        int daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);

            if (day % 2 != 0) {
                dutyScheduleMap.put(date, doctorA_Initializer()); //Assign group A doctor (odd day)
            } else {
                dutyScheduleMap.put(date, doctorB_Initializer());//Assign Group B doctor (even day)
            }
        }

        return dutyScheduleMap;
    }

    public MapInterface<String, String> timeSlotInitializer() {
        MapInterface<String, String> timeSlots = new ChainBucket<>();

        timeSlots.put("09:00", "09:00");
        timeSlots.put("09:30", "09:30");
        timeSlots.put("10:00", "10:00");
        timeSlots.put("10:30", "10:30");
        timeSlots.put("11:00", "11:00");
        timeSlots.put("11:30", "11:30");
        timeSlots.put("12:00", "12:00");
        timeSlots.put("12:30", "12:30");
        timeSlots.put("13:00", "13:00");
        timeSlots.put("13:30", "13:30");
        timeSlots.put("14:00", "14:00");
        timeSlots.put("14:30", "14:30");
        timeSlots.put("15:00", "15:00");
        timeSlots.put("15:30", "15:30");
        timeSlots.put("16:00", "16:00");
        timeSlots.put("16:30", "16:30");
        timeSlots.put("17:00", "17:00");
        timeSlots.put("17:30", "17:30");
        timeSlots.put("18:00", "18:00");

        return timeSlots;
    }

    public MapInterface<String, Ticket> ticketInitializer() {
        MapInterface<String, Ticket> ticketMap = new ChainBucket<>();
//ticket id, ticketStatus,queueStart,queueEnd,patientID
        ticketMap.put("TK001", new Ticket("TK001", "", null, null, ""));
        ticketMap.put("TK002", new Ticket("TK002", "", null, null, ""));
        ticketMap.put("TK003", new Ticket("TK003", "", null, null, ""));
        ticketMap.put("TK004", new Ticket("TK004", "", null, null, ""));
        ticketMap.put("TK005", new Ticket("TK005", "", null, null, ""));
        ticketMap.put("TK006", new Ticket("TK006", "", null, null, ""));
        ticketMap.put("TK007", new Ticket("TK007", "", null, null, ""));
        ticketMap.put("TK008", new Ticket("TK008", "", null, null, ""));
        ticketMap.put("TK009", new Ticket("TK009", "", null, null, ""));
        ticketMap.put("TK010", new Ticket("TK010", "", null, null, ""));
        ticketMap.put("TK011", new Ticket("TK011", "", null, null, ""));
        ticketMap.put("TK012", new Ticket("TK012", "", null, null, ""));
        ticketMap.put("TK013", new Ticket("TK013", "", null, null, ""));
        ticketMap.put("TK014", new Ticket("TK014", "", null, null, ""));
        ticketMap.put("TK015", new Ticket("TK015", "", null, null, ""));
        ticketMap.put("TK016", new Ticket("TK016", "", null, null, ""));
        ticketMap.put("TK017", new Ticket("TK017", "", null, null, ""));
        ticketMap.put("TK018", new Ticket("TK018", "", null, null, ""));
        ticketMap.put("TK019", new Ticket("TK019", "", null, null, ""));
        ticketMap.put("TK020", new Ticket("TK020", "", null, null, ""));
        ticketMap.put("TK021", new Ticket("TK021", "", null, null, ""));
        ticketMap.put("TK022", new Ticket("TK022", "", null, null, ""));
        ticketMap.put("TK023", new Ticket("TK023", "", null, null, ""));
        ticketMap.put("TK024", new Ticket("TK024", "", null, null, ""));
        ticketMap.put("TK025", new Ticket("TK025", "", null, null, ""));
        ticketMap.put("TK026", new Ticket("TK026", "", null, null, ""));
        ticketMap.put("TK027", new Ticket("TK027", "", null, null, ""));
        ticketMap.put("TK028", new Ticket("TK028", "", null, null, ""));
        ticketMap.put("TK029", new Ticket("TK029", "", null, null, ""));
        ticketMap.put("TK030", new Ticket("TK030", "", null, null, ""));
        return ticketMap;
    }
}
