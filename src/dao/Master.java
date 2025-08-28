package dao;

import dao.Initializer;
import entity.*;
import adt.MapInterface;
import adt.ChainBucket;
import java.time.LocalDate;

public class Master {

    // <Instance ID, Instance>
    private static MapInterface<String, Treatment> treatmentMap = new ChainBucket<>();
    private static MapInterface<String, Patient> patientMap = new ChainBucket<>();
    private static MapInterface<String, Staff> staffMap = new ChainBucket<>();
    private static MapInterface<String, Consultation> consultationMap = new ChainBucket<>();
    private static MapInterface<String, Medicine> medicineMap = new ChainBucket<>();
    private static MapInterface<String, Prescription> prescriptionMap = new ChainBucket<>();
    private static MapInterface<String, Payment> paymentMap = new ChainBucket<>();
    private static MapInterface<LocalDate, MapInterface<Integer, String>> dutyScheduleMap = new ChainBucket<>();
    private static MapInterface<Integer, String> doctorAMap = new ChainBucket<>();
    private static MapInterface<Integer, String> doctorBMap = new ChainBucket<>();
    private static MapInterface<String, String> timeSlotMap = new ChainBucket<>();
    private static String currentPatientId = "";
    private static String currentStaffId = "";
    private static String currentTicket = "";
    private static final Initializer INITIALIZER = new Initializer();
    private static MapInterface<String, Ticket> ticketQueue = new ChainBucket<>();

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
        doctorAMap = INITIALIZER.doctorA_Initializer();
        doctorBMap = INITIALIZER.doctorB_Initializer();
        timeSlotMap = INITIALIZER.timeSlotInitializer();
        ticketQueue = INITIALIZER.ticketInitializer();
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

    public static MapInterface<Integer, String> getDoctorAMap() {
        return doctorAMap;
    }

    public static MapInterface<Integer, String> getDoctorBMap() {
        return doctorBMap;
    }

    public static MapInterface<String, String> getTimeSlotMap() {
        return timeSlotMap;
    }

    public static String getCurrentTicket() {
        return currentTicket;
    }

    public static MapInterface<String, Ticket> getTicketQueue() {
        return ticketQueue;
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

    public static void setDoctorAMap(MapInterface<Integer, String> doctorAMap) {
        Master.doctorAMap = doctorAMap;
    }

    public static void setDoctorBMap(MapInterface<Integer, String> doctorBMap) {
        Master.doctorBMap = doctorBMap;
    }

    public static void setTimeSlotMap(MapInterface<String, String> timeSlotMap) {
        Master.timeSlotMap = timeSlotMap;
    }

    public static void setCurrentTicket(String currentTicket) {
        Master.currentTicket = currentTicket;
    }

    public static void setTicketQueue(MapInterface<String, Ticket> ticketQueue) {
        Master.ticketQueue = ticketQueue;
    }
}
