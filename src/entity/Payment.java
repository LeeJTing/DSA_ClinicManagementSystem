/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Teh Zhi Qin
 */
public class Payment {

    private String payment_id = "P0001";
    private String student_id;
    private Prescription prescription;
    private double consultation_cost;
    private double total_cost;

    public Payment() {
        this.payment_id = "";
        this.student_id = "";
        this.consultation_cost = 0.0;
        this.total_cost = 0.0;

    }

    public Payment(String paymentID, String studentID, Prescription prescription, double consultationCost, double totalCost) {
        this.payment_id = paymentID;
        this.student_id = studentID;
        this.prescription = prescription;
        this.consultation_cost = consultationCost;
        this.total_cost = totalCost;
    }

    public String getPaymentID() {
        return payment_id;
    }

    public String getStudentID() {
        return student_id;
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

    public void setPaymentID(String paymentID) {
        this.payment_id = paymentID;
    }

    public void setStudentID(String studentID) {
        this.student_id = studentID;
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
    
    public double calTotalCost(int consultationDuration) {
        return calConsultationCost(consultationDuration) + getMedicineCost();
    }
    
    public double calConsultationCost(int consultationDuration) {
        return consultationDuration * 50.0;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-8s %-8s %-6.2f %-6.2f %-6.2f\n", payment_id, student_id, prescription.getPrescription_id(), consultation_cost, prescription.getMedicine_total_cost(), total_cost);
    }
}
