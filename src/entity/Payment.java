/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Teh Zhi Qin
 */
public class Payment {

    private String payment_id = "PM0001";
    private String patient_id;
    private Prescription prescription;
    private double consultation_cost;
    private double total_cost;
    private Date payment_time;

    public Payment() {
        this.payment_id = "";
        this.patient_id = "";
        this.consultation_cost = 0.0;
        this.total_cost = 0.0;
        this.payment_time = null;
    }

    public Payment(String paymentID, String patientID, Prescription prescription, double consultationCost, double totalCost, Date paymentTime) {
        this.payment_id = paymentID;
        this.patient_id = patientID;
        this.prescription = prescription;
        this.consultation_cost = consultationCost;
        this.total_cost = totalCost;
        this.payment_time = paymentTime;
    }

    public String getPaymentID() {
        return payment_id;
    }

    public String getPatientID() {
        return patient_id;
    }

    public String getPrescriptionID() {
        return prescription.getPrescription_id();
    }

    public double getConsultationCost() {
        return consultation_cost;
    }

    public double getMedicineCost() {
        return prescription.getMedicine_total_cost();
    }

    public double getTotalCost() {
        return total_cost;
    }
    
    public Date getPaymentTime() {
        return payment_time;
    }

    public void setPaymentID(String paymentID) {
        this.payment_id = paymentID;
    }

    public void setPatientID(String patientID) {
        this.patient_id = patientID;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public void setConsultationCost(double consultationCost) {
        this.consultation_cost = consultationCost;
    }

    public void setTotalCost(double totalCost) {
        this.total_cost = totalCost;
    }
    
    public void setPaymentTime(Date paymentTime) {
        this.payment_time = paymentTime;
    }
    
    public double calTotalCost(int consultationDuration) {
        return calConsultationCost(consultationDuration) + getMedicineCost();
    }
    
    public double calConsultationCost(int consultationDuration) {
        return consultationDuration * 50.0;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-8s %-8s %-6.2f %-6.2f %-6.2f %-25s\n", payment_id, patient_id, prescription.getPrescription_id(), consultation_cost, prescription.getMedicine_total_cost(), total_cost, payment_time);
    }
}
