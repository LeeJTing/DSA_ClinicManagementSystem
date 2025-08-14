/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.Initializer;
import entity.*;
import adt.MapInterface;
import adt.LinkedHashMap;
import java.time.LocalDate;

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
    private static MapInterface<String, Prescription> prescriptionMap = new LinkedHashMap<>();
    private static MapInterface<String, Payment> paymentMap = new LinkedHashMap<>();
    private static MapInterface<LocalDate, MapInterface<Integer, String>> dutyScheduleMap = new LinkedHashMap<>();
    private static String currentPatientId = "";
    private static String currentStaffId = "";

    private static final Initializer INITIALIZER = new Initializer();

    public Master() {

    }

    public static void initializer() {
        treatmentMap = INITIALIZER.medicalTreatmentInitializer();
        medicineMap = INITIALIZER.medicineInitializer();
        patientMap = INITIALIZER.patientInitializer();
        consultationMap = INITIALIZER.consultationInitializer();
        paymentMap = INITIALIZER.paymentInitializer();
        staffMap = INITIALIZER.staffInitializer();
        prescriptionMap = INITIALIZER.prescriptionInitializer();
        dutyScheduleMap = INITIALIZER.dutySchedule_Initializer();
    }

    public static String getCurrentPatientId() {
        return currentPatientId;
    }

    public static String getCurrentStaffId() {
        return currentStaffId;
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

    public static MapInterface<String, Payment> getPaymentMap() {
        return paymentMap;
    }

    public static MapInterface<String, Prescription> getPrescriptionMap() {
        return prescriptionMap;
    }

    public static MapInterface<LocalDate, MapInterface<Integer, String>> getDutyScheduleMap() {
        return dutyScheduleMap;
    }

    public static void setCurrentPatientId(String currentPatientId) {
        Master.currentPatientId = currentPatientId;
    }

    public static void setCurrentStaffId(String currentStaffId) {
        Master.currentStaffId = currentStaffId;
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

    public static void setPaymentMap(MapInterface<String, Payment> paymentMap) {
        Master.paymentMap = paymentMap;
    }

    public static void setPrescriptionMap(MapInterface<String, Prescription> prescriptionMap) {
        Master.prescriptionMap = prescriptionMap;
    }

    public static void setDutyScheduleMap(MapInterface<LocalDate, MapInterface<Integer, String>> dutyScheduleMap) {
        Master.dutyScheduleMap = dutyScheduleMap;
    }

}
