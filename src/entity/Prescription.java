package entity;
import utility.IDGenerator;
import java.util.LinkedHashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lee Jun Ting
 */
public class Prescription {
    private String prescription_id = "Ph000001"; //Start from Ph000001
    private double medicine_total_cost;
    private LinkedHashMap<Integer, Medicine> medicineLIst;
    private String staff_id;
    private String student_id;
    private String treatment_id;
    
     public Prescription() {
    }
    
    public Prescription(String prescription_id, double medicine_total_cost, LinkedHashMap<Integer, Medicine> medicineLIst, String staff_id, String student_id, String treatment_id) {
        this.prescription_id = prescription_id;
        this.medicine_total_cost = medicine_total_cost;
        this.medicineLIst = medicineLIst;
        this.staff_id = staff_id;
        this.student_id = student_id;
        this.treatment_id = treatment_id;
    }

    public void setPrescription_id(String prescription_id) {
        this.prescription_id = prescription_id;
    }

    public void setMedicine_total_cost(double medicine_total_cost) {
        this.medicine_total_cost = medicine_total_cost;
    }

    public void setMedicineLIst(LinkedHashMap<Integer, Medicine> medicineLIst) {
        this.medicineLIst = medicineLIst;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setTreatment_id(String treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getPrescription_id() {
        return prescription_id;
    }

    public double getMedicine_total_cost() {
        return medicine_total_cost;
    }

    public LinkedHashMap<Integer, Medicine> getMedicineLIst() {
        return medicineLIst;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getTreatment_id() {
        return treatment_id;
    }    
    
}
