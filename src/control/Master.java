/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.Initializer;
import entity.*;
import boundary.menu;
import adt.MapInterface;
import adt.LinkedHashMap;

/**
 *
 * @author User
 */
public class Master {
    // <Instance ID, Instance>
    private static MapInterface<String, Treatment> treatmentMap = new LinkedHashMap<>();
    private static MapInterface<String, Patient> patientMap = new LinkedHashMap<>();
    private static MapInterface<String, Staff> staffMap = new LinkedHashMap<>();
    private static MapInterface<String, Consultation> consultationMap = new LinkedHashMap<>();
    private static MapInterface<String, Medicine> medicineMap = new LinkedHashMap<>();
    private static MapInterface<String, Prescription> perscriptionMap = new LinkedHashMap<>();
    private static MapInterface<String, Payment> paymentMap = new LinkedHashMap<>();
    
    private static final Initializer INITIALIZER = new Initializer();
    
    public static void main(String[] args) {
        treatmentMap = INITIALIZER.medicalTreatmentInitializer();
        perscriptionMap = INITIALIZER.prescriptionInitializer();
        System.out.println(perscriptionMap.getValue(perscriptionMap.getLastKey()).toString());
        
    }
    
    public Master(){
        
        treatmentMap = INITIALIZER.medicalTreatmentInitializer();
        medicineMap = INITIALIZER.medicineInitializer();
        patientMap = INITIALIZER.patientInitializer();
        consultationMap = INITIALIZER.consultationInitializer();
        paymentMap = INITIALIZER.paymentInitializer();
        staffMap = INITIALIZER.staffInitializer();
        perscriptionMap = INITIALIZER.prescriptionInitializer();
    }

    public static MapInterface<String, Treatment> getTreatmentMap() {
        return treatmentMap;
    }

    public static MapInterface<String, Patient> getPatientMap() {
        return patientMap;
    }

    public static MapInterface<String, Staff> getStaffMap() {
        return staffMap;
    }

    public static MapInterface<String, Consultation> getConsultationMap() {
        return consultationMap;
    }

    public static MapInterface<String, Medicine> getMedicineMap() {
        return medicineMap;
    }

    public static void setTreatmentMap(MapInterface<String, Treatment> treatmentMap) {
        Master.treatmentMap = treatmentMap;
    }

    public static void setPatientMap(MapInterface<String, Patient> patientMap) {
        Master.patientMap = patientMap;
    }

    public static void setStaffMap(MapInterface<String, Staff> staffMap) {
        Master.staffMap = staffMap;
    }

    public static void setConsultationMap(MapInterface<String, Consultation> consultationMap) {
        Master.consultationMap = consultationMap;
    }

    public static void setMedicineMap(MapInterface<String, Medicine> medicineMap) {
        Master.medicineMap = medicineMap;
    }
    
    
}
