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
import utility.IDGenerator;

/**
 *
 * @author User
 */
public class Initializer {

    public MapInterface<String, Treatment> medicalTreatmentInitializer() {

        LinkedHashMap<String, Treatment> treatmentMap = new LinkedHashMap<>();
        // String previousTreatmentID,String disease, String treatment_advice, Date treatment_date, String staff_id, boolean isScan, String remark
        treatmentMap.put("T000001", new Treatment("T000000", "Hypertension", "Reduce salt intake, daily morning walk, medication prescribed",
                new Date(2025, 6, 14), "S000001", "P000005", true, "Patient brought previous scan reports"));
        treatmentMap.put("T000002", new Treatment("T000001", "Diabetes Type 2", "Start insulin therapy, monitor blood sugar twice daily",
                new Date(2025, 6, 25), "S000002", "P000004", false, "Blood sugar fluctuating – advised strict diet"));
        treatmentMap.put("T000003", new Treatment("T000002", "Asthma", "Inhaler prescribed, avoid allergens",
                new Date(2025, 7, 5), "S000001", "P000003", true, "Chest X-ray scan done"));
        treatmentMap.put("T000004", new Treatment("T000003", "Gastritis", "Antacid medication for 2 weeks, avoid spicy food",
                new Date(2025, 7, 12), "S000003", "P000001", false, "Follow-up in 2 weeks"));
        treatmentMap.put("T000005", new Treatment("T000004", "COVID-19", "Isolation advised, paracetamol for fever, hydration",
                new Date(2025, 7, 20), "S000002", "P000002", true, "CT scan shows mild lung infection"));

        return treatmentMap;
    }

    // initialize the medicine dummy data
    public MapInterface<String, Medicine> medicineInitializer() {
        LinkedHashMap<String, Medicine> medicineMap = new LinkedHashMap<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date expiryDate = sdf.parse("2030-10-10");

            medicineMap.put("M0001", new Medicine("M0001", "Paracetamol", "Analgesics (Painkillers)", expiryDate, 100, 5.0));
            medicineMap.put("M0002", new Medicine("M0002", "Ibuprofen", "Analgesics (Painkillers)", expiryDate, 100, 5.2));
            medicineMap.put("M0003", new Medicine("M0003", "Amoxicillin", "Antibiotics", expiryDate, 80, 13.5));
            medicineMap.put("M0004", new Medicine("M0004", "Paracetamol", "Antipyretics (Fever Reducers)", expiryDate, 200, 5.0));
            medicineMap.put("M0005", new Medicine("M0005", "Hydrogen Peroxide", "Antiseptics & Disinfectants", expiryDate, 110, 18.3));
            medicineMap.put("M0006", new Medicine("M0006", "Iodine", "Antiseptics & Disinfectants", expiryDate, 90, 4.0));
            medicineMap.put("M0007", new Medicine("M0007", "Larotadine", "Antihistamines (Allergy Relief)", expiryDate, 220, 15.0));
            medicineMap.put("M0008", new Medicine("M0008", "Cetirizine", "Antihistamines (Allergy Relief)", expiryDate, 190, 13.0));
            medicineMap.put("M0009", new Medicine("M0009", "Dextromethorphan", "Cough & Cold Remedies", expiryDate, 300, 7.0));
            medicineMap.put("M0010", new Medicine("M0010", "Naproxen", "Anti-inflammatory Drugs", expiryDate, 100, 9.7));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return medicineMap;
    }

    //initialize the patient dummy data
    public MapInterface<String, Patient> patientInitializer() {
        LinkedHashMap<String, Patient> patientMap = new LinkedHashMap<>();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            patientMap.put("P000001", new Patient("P000001", "Ali bin Ahmad", "0123456789", "ali@example.com", "Male", 30,
                    dateFormat.parse("20/07/2025"), timeFormat.parse("08:30"), timeFormat.parse("08:50"),
                    dateFormat.parse("20/07/2025"), dateFormat.parse("20/07/2025")));

            patientMap.put("P000002", new Patient("P000002", "Lim Mei Ling", "0198765432", "lim@example.com", "Female", 25,
                    dateFormat.parse("18/07/2025"), timeFormat.parse("09:00"), timeFormat.parse("09:25"),
                    dateFormat.parse("18/07/2025"), dateFormat.parse("18/07/2025")));

            patientMap.put("P000003", new Patient("P000003", "Ravi a/l Kumar", "0172233445", "ravi@example.com", "Male", 40,
                    dateFormat.parse("15/07/2025"), timeFormat.parse("10:15"), timeFormat.parse("10:45"),
                    dateFormat.parse("15/07/2025"), dateFormat.parse("15/07/2025")));

            patientMap.put("P000004", new Patient("P000004", "Tan Siew Ling", "0135566778", "tan@example.com", "Female", 35,
                    dateFormat.parse("22/07/2025"), timeFormat.parse("11:00"), timeFormat.parse("11:20"),
                    dateFormat.parse("22/07/2025"), dateFormat.parse("22/07/2025")));

            patientMap.put("P000005", new Patient("P000005", "Muhammad Zaki", "0169988776", "zaki@example.com", "Male", 28,
                    dateFormat.parse("25/07/2025"), timeFormat.parse("08:45"), timeFormat.parse("09:10"),
                    dateFormat.parse("25/07/2025"), dateFormat.parse("25/07/2025")));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return patientMap;
    }

}
