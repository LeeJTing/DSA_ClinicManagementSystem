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
import utility.IDGenerator;

/**
 *
 * @author User
 */
public class Initializer {
    public MapInterface<String, Treatment> medicalTreatmentInitializer(){
        
        LinkedHashMap<String, Treatment> treatmentMap = new LinkedHashMap<>();
        // String previousTreatmentID,String disease, String treatment_advice, Date treatment_date, String staff_id, boolean isScan, String remark
        treatmentMap.put("T000001", new Treatment("T000000", "Hypertension", "Reduce salt intake, daily morning walk, medication prescribed",
                new Date(2025, 6, 14), "S000001", "P000005", true, "Patient brought previous scan reports"));
        treatmentMap.put("T000002", new Treatment("T000001", "Diabetes Type 2", "Start insulin therapy, monitor blood sugar twice daily",
                new Date(2025, 6, 25), "S000002", "P000004", false, "Blood sugar fluctuating – advised strict diet"));
        treatmentMap.put("T000003", new Treatment("T000002","Asthma", "Inhaler prescribed, avoid allergens",
                new Date(2025, 7, 5), "S000001", "P000003", true, "Chest X-ray scan done"));
        treatmentMap.put("T000004", new Treatment("T000003", "Gastritis", "Antacid medication for 2 weeks, avoid spicy food",
                new Date(2025, 7, 12), "S000003", "P000001", false, "Follow-up in 2 weeks"));
        treatmentMap.put("T000005", new Treatment("T000004", "COVID-19", "Isolation advised, paracetamol for fever, hydration",
                new Date(2025, 7, 20), "S000002", "P000002",true, "CT scan shows mild lung infection"));
        
        return treatmentMap;
    }
}
