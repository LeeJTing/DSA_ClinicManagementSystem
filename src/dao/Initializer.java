/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import adt.LinkedHashMap;
import adt.MapInterface;
import entity.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.IDGenerator;

/**
 *
 * @author User
 */
public class Initializer {

    public MapInterface<String, Treatment> medicalTreatmentInitializer() {

        LinkedHashMap<String, Treatment> treatmentMap = new LinkedHashMap<>();
        // String previousTreatmentID,String disease, String treatment_advice, Date treatment_date, String staff_id, boolean isScan, String remark
        treatmentMap.put("T000001", new Treatment("T000000", "C000001", "Hypertension", "Reduce salt intake, daily morning walk, medication prescribed",
                new Date(2025, 6, 14), "S000001", "P000005", true, "Patient brought previous scan reports"));
        treatmentMap.put("T000002", new Treatment("T000001", "C000002", "Diabetes Type 2", "Start insulin therapy, monitor blood sugar twice daily",
                new Date(2025, 6, 25), "S000002", "P000004", false, "Blood sugar fluctuating – advised strict diet"));
        treatmentMap.put("T000003", new Treatment("T000002", "C000003", "Asthma", "Inhaler prescribed, avoid allergens",
                new Date(2025, 7, 5), "S000001", "P000003", true, "Chest X-ray scan done"));
        treatmentMap.put("T000004", new Treatment("T000003", "C000004", "Gastritis", "Antacid medication for 2 weeks, avoid spicy food",
                new Date(2025, 7, 12), "S000003", "P000001", false, "Follow-up in 2 weeks"));
        treatmentMap.put("T000005", new Treatment("T000004", "C000005", "COVID-19", "Isolation advised, paracetamol for fever, hydration",
                new Date(2025, 7, 20), "S000002", "P000002", true, "CT scan shows mild lung infection"));
        treatmentMap.put("T000006", new Treatment("T000005", "C000006", "Migraine", "Painkillers prescribed, advised to reduce screen time",
                new Date(2025, 7, 22), "S000004", "P000006", false, "Referred to neurologist if pain persists"));
        treatmentMap.put("T000007", new Treatment("T000006", "C000007", "Anemia", "Iron supplements prescribed, increase iron-rich foods",
                new Date(2025, 7, 23), "S000006", "P000007", false, "Patient advised to return for blood test"));
        treatmentMap.put("T000008", new Treatment("T000007", "C000008", "Fracture", "Arm cast applied, x-ray review in 3 weeks",
                new Date(2025, 7, 24), "S000005", "P000008", true, "X-ray confirmed non-displaced fracture"));
        treatmentMap.put("T000009", new Treatment("T000008", "C000009", "Tonsillitis", "Antibiotics prescribed for 7 days, warm saline gargle",
                new Date(2025, 7, 25), "S000003", "P000009", false, "Mild swelling observed, no scan needed"));
        treatmentMap.put("T000010", new Treatment("T000009", "C000010", "High Cholesterol", "Start statins, low-fat diet advised",
                new Date(2025, 7, 26), "S000007", "P000010", true, "Lipid profile test results attached"));

        return treatmentMap;
    }

    public MapInterface<String, Prescription> prescriptionInitializer() {
        LinkedHashMap<String, Prescription> prescriptionMap = new LinkedHashMap<>();

        LinkedHashMap<String, Medicine> medicineMap1 = new LinkedHashMap<>();
        LinkedHashMap<String, Medicine> medicineMap2 = new LinkedHashMap<>();
        LinkedHashMap<String, Medicine> medicineMap3 = new LinkedHashMap<>();
        LinkedHashMap<String, Medicine> medicineMap4 = new LinkedHashMap<>();
        LinkedHashMap<String, Medicine> medicineMap5 = new LinkedHashMap<>();

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

            //String previous_prescription_id, LinkedHashMap<String, Medicine> medicineList, String staff_id, String patient_id, String treatment_id
            prescriptionMap.put("PH000001", new Prescription("PH000000", medicineMap1, "S000001", "P000005", "T000001"));
            prescriptionMap.put("PH000002", new Prescription("PH000001", medicineMap2, "S000002", "P000004", "T000002"));
            prescriptionMap.put("PH000003", new Prescription("PH000002", medicineMap3, "S000001", "P000003", "T000003"));
            prescriptionMap.put("PH000004", new Prescription("PH000003", medicineMap4, "S000003", "P000001", "T000004"));
            prescriptionMap.put("PH000005", new Prescription("PH000004", medicineMap5, "S000002", "P000002", "T000005"));
            prescriptionMap.put("PH000006", new Prescription("PH000005", medicineMap1, "S000004", "P000006", "T000006"));
            prescriptionMap.put("PH000007", new Prescription("PH000006", medicineMap2, "S000006", "P000007", "T000007"));
            prescriptionMap.put("PH000008", new Prescription("PH000007", medicineMap3, "S000005", "P000008", "T000008"));
            prescriptionMap.put("PH000009", new Prescription("PH000008", medicineMap4, "S000003", "P000009", "T000009"));
            prescriptionMap.put("PH000010", new Prescription("PH000009", medicineMap5, "S000007", "P000010", "T000010"));
        } catch (ParseException ex) {
            Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prescriptionMap;
    }

    // initialize the medicine dummy data
    public MapInterface<String, Medicine> medicineInitializer() {
        LinkedHashMap<String, Medicine> medicineMap = new LinkedHashMap<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date expiryDate = sdf.parse("10-10-2030");

            medicineMap.put("M000001", new Medicine("M000001", "Paracetamol", "Analgesics (Painkillers)", expiryDate, 100, 5.0));
            medicineMap.put("M000002", new Medicine("M000002", "Ibuprofen", "Analgesics (Painkillers)", expiryDate, 100, 5.2));
            medicineMap.put("M000003", new Medicine("M000003", "Amoxicillin", "Antibiotics", expiryDate, 80, 13.5));
            medicineMap.put("M000004", new Medicine("M000004", "Paracetamol", "Antipyretics (Fever Reducers)", expiryDate, 200, 5.0));
            medicineMap.put("M000005", new Medicine("M000005", "Hydrogen Peroxide", "Antiseptics & Disinfectants", expiryDate, 110, 18.3));
            medicineMap.put("M000006", new Medicine("M000006", "Iodine", "Antiseptics & Disinfectants", expiryDate, 90, 4.0));
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
        LinkedHashMap<String, Payment> paymentMap = new LinkedHashMap<>();
        MapInterface<String, Prescription> prescriptionMap = prescriptionInitializer();
        MapInterface<String, Consultation> consultationMap = consultationInitializer();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
//            String paymentID, String patientID, Prescription prescription, double consultationCost, double totalCost, Date paymentTime
            paymentMap.put("PM000001", new Payment("PM000001", "P000001", prescriptionMap.getValue("PH000001"), 20.0, 40.0, sdf.parse("01-07-2025 12:00:06")));
            paymentMap.put("PM000002", new Payment("PM000002", "P000002", prescriptionMap.getValue("PH000002"), 20.0, 40.0, sdf.parse("02-07-2025 17:10:10")));
            paymentMap.put("PM000003", new Payment("PM000003", "P000003", prescriptionMap.getValue("PH000003"), 20.0, 40.0, sdf.parse("03-07-2025 14:25:03")));
            paymentMap.put("PM000004", new Payment("PM000004", "P000004", prescriptionMap.getValue("PH000004"), 20.0, 40.0, sdf.parse("04-07-2025 15:56:30")));
            paymentMap.put("PM000005", new Payment("PM000005", "P000005", prescriptionMap.getValue("PH000005"), 20.0, 40.0, sdf.parse("05-07-2025 10:04:45")));
            paymentMap.put("PM000006", new Payment("PM000006", "P000006", prescriptionMap.getValue("PH000006"), 20.0, 40.0, sdf.parse("05-07-2025 11:19:22")));
            paymentMap.put("PM000007", new Payment("PM000007", "P000007", prescriptionMap.getValue("PH000007"), 20.0, 40.0, sdf.parse("07-07-2025 13:05:14")));
            paymentMap.put("PM000008", new Payment("PM000008", "P000008", prescriptionMap.getValue("PH000008"), 20.0, 40.0, sdf.parse("08-07-2025 14:30:31")));
            paymentMap.put("PM000009", new Payment("PM000009", "P000009", prescriptionMap.getValue("PH000009"), 20.0, 40.0, sdf.parse("09-07-2025 16:29:44")));
            paymentMap.put("PM000010", new Payment("PM000010", "P000010", prescriptionMap.getValue("PH000010"), 20.0, 40.0, sdf.parse("10-07-2025 14:40:02")));

            Object[] paymentValues = paymentMap.getAllValues();

            for (Object obj : paymentValues) {
                Payment pm = (Payment) obj;
                String patientID = pm.getPatientID();

                // Consultation retrieval
                Consultation c = (Consultation) consultationMap.getValue(patientID); // This returns Object
                if (c != null && "Completed".equals(c.getAppointmentStatus())) {
                    int duration = c.calculationDurationTimeInMinutes();
                    pm.calTotalCost(duration);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return paymentMap;
    }

    //initialize the patient dummy data
    public MapInterface<String, Patient> patientInitializer() {
        LinkedHashMap<String, Patient> patientMap = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            patientMap.put("P000001", new Patient("P000001", "Adam Lee", "0123456789", "adam@gmail.com", "Male", 25, sdf.parse("24-07-2025 12:00:00"), sdf.parse("26-07-2025 08:45:00"), sdf.parse("26-07-2025 09:00:00")));
            patientMap.put("P000002", new Patient("P000002", "Betty Tan", "0112233445", "betty@gmail.com", "Female", 30, sdf.parse("24-07-2025 13:30:00"), sdf.parse("26-07-2025 09:25:00"), sdf.parse("26-07-2025 10:00:00")));
            patientMap.put("P000003", new Patient("P000003", "Charlie Goh", "0103344556", "charlie@gmail.com", "Male", 22, sdf.parse("25-07-2025 10:20:00"), sdf.parse("27-07-2025 10:15:00"), sdf.parse("27-07-2025 11:23:00")));
            patientMap.put("P000004", new Patient("P000004", "Diana Lim", "0167788990", "diana@gmail.com", "Female", 28, sdf.parse("25-07-2025 09:50:00"), sdf.parse("27-07-2025 13:35:00"), sdf.parse("27-07-2025 14:12:00")));
            patientMap.put("P000005", new Patient("P000005", "Ethan Yong", "0188899776", "ethan@gmail.com", "Male", 27, sdf.parse("25-07-2025 11:10:00"), sdf.parse("27-07-2025 15:10:00"), sdf.parse("27-07-2025 15:30:00")));
            patientMap.put("P000006", new Patient("P000006", "Fiona Cheah", "0198877665", "fiona@gmail.com", "Female", 35, sdf.parse("26-07-2025 14:00:00"), sdf.parse("28-07-2025 08:50:00"), sdf.parse("28-07-2025 09:00:00")));
            patientMap.put("P000007", new Patient("P000007", "Gavin Ong", "0177766554", "gavin@gmail.com", "Male", 26, sdf.parse("26-07-2025 14:10:00"), sdf.parse("28-07-2025 08:40:00"), sdf.parse("28-07-2025 09:30:00")));
            patientMap.put("P000008", new Patient("P000008", "Hannah Yap", "0135566778", "hannah@gmail.com", "Female", 24, sdf.parse("26-07-2025 15:45:00"), sdf.parse("28-07-2025 09:40:00"), sdf.parse("28-07-2025 10:00:00")));
            patientMap.put("P000009", new Patient("P000009", "Ivan Lim", "0129988776", "ivan@gmail.com", "Male", 29, sdf.parse("27-07-2025 11:35:00"), sdf.parse("29-07-2025 10:45:00"), sdf.parse("29-07-2025 11:00:00")));
            patientMap.put("P000010", new Patient("P000010", "Joanne Teo", "0117788665", "joanne@gmail.com", "Female", 31, sdf.parse("27-07-2025 12:10:00"), sdf.parse("29-07-2025 11:45:00"), sdf.parse("29-07-2025 12:00:00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return patientMap;
    }

    //initialize the Consultation dummy data 
    public MapInterface<String, Consultation> consultationInitializer() {
        LinkedHashMap<String, Consultation> consultMap = new LinkedHashMap<>();
//        String consultation_Id, Date consultation_duration, String appointmentDate, String consultationDate, String consultationStartTime,
//        String consultationEndTime, String appointmentStatus, String type
        consultMap.put("C000001", new Consultation("C000001", "25/07/2025", "26/07/2025", "09:00", "10:00", "Completed", "Online", "P000001", "S000001"));
        consultMap.put("C000002", new Consultation("C000002", "25/07/2025", "26/07/2025", "10:00", "11:00", "Completed", "Online", "P000002", "S000002"));
        consultMap.put("C000003", new Consultation("C000003", "26/07/2025", "27/07/2025", "11:00", "11:30", "Completed", "Online", "P000003", "S000003"));
        consultMap.put("C000004", new Consultation("C000004", "26/07/2025", "27/07/2025", "14:00", "15:00", "Completed", "Online", "P000004", "S000004"));
        consultMap.put("C000005", new Consultation("C000005", "26/07/2025", "27/07/2025", "15:30", "16:00", "Completed", "Online", "P000005", "S000005"));
        consultMap.put("C000006", new Consultation("C000006", "27/07/2025", "28/07/2025", "09:00", "10:00", "Completed", "Walk-In", "P000006", "S000006"));
        consultMap.put("C000007", new Consultation("C000007", "27/07/2025", "28/07/2025", "09:30", "10:00", "Completed", "Walk-In", "P000007", "S000007"));
        consultMap.put("C000008", new Consultation("C000008", "27/07/2025", "28/07/2025", "10:00", "11:00", "Completed", "Walk-In", "P000008", "S000001"));
        consultMap.put("C000009", new Consultation("C000009", "28/07/2025", "29/07/2025", "11:00", "12:00", "Completed", "Walk-In", "P000009", "S000002"));
        consultMap.put("C000010", new Consultation("C000010", "28/07/2025", "29/07/2025", "12:00", "12:30", "Completed", "Walk-In", "P000010", "S000003"));
        consultMap.put("C000010", new Consultation("C000011", "28/07/2025", "29/07/2025", "12:00", "00:00", "On-going", "Walk-In", "P000010", "S000003"));
        consultMap.put("C000010", new Consultation("C000012", "28/07/2025", "29/07/2025", "15:00", "15:30", "Pending", "Online", "P000010", "S000003"));

        return consultMap;
    }

    public MapInterface<String, Staff> staffInitializer() {
        LinkedHashMap<String, Staff> staffMap = new LinkedHashMap<>();

        // staff_id, staff_password, staff_name, staff_position, staff_contact, staff_email,
        // education_level, service_duration, dutyStatus, joined_date, pendingLeaveDate
        staffMap.put("S000001", new Staff("S000001", "pass123", "John Smith", "Doctor", "012-3456789", "john.smith@hospital.com", 5, 10, "Work", "12-12-2015"));
        staffMap.put("S000002", new Staff("S000002", "pass123", "Emily Davis", "Doctor", "012-3456789", "emily.davis@hospital.com", 5, 10, "Work", "20-07-2018"));
        staffMap.put("S000003", new Staff("S000003", "pass789", "Michael Lee", "Doctor", "011-2233445", "michael.lee@hospital.com", 6, 12, "Work", "11-05-2013"));
        staffMap.put("S000004", new Staff("S000004", "pass987", "Sophia Wong", "Doctor", "014-5566778", "sophia.wong@hospital.com", 4, 8, "Work", "25-04-2014"));
        staffMap.put("S000005", new Staff("S000005", "pass654", "David Tan", "Doctor", "010-9988776", "david.tan@hospital.com", 2, 5, "Work", "15-09-2015"));
        staffMap.put("S000006", new Staff("S000006", "pass155", "Jacksong Tee", "Doctor", "018-7988776", "jacksonTee@hospital.com", 2, 5, "Work", "30-12-2015"));
        staffMap.put("S000007", new Staff("S000007", "pass111", "Esther Yong", "Pharmacist", "012-5988776", "Esther@hospital.com", 2, 5, "Work", "12-01-2015"));

        return staffMap;
    }

}
