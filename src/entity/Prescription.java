package entity;
import utility.IDGenerator;

import adt.LinkedHashMap;
import utility.IDGenerator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lee Jun Ting
 */
public class Prescription {
    private String prescription_id; //Start from PH000001
    private double medicine_total_cost;
    private LinkedHashMap<String, Medicine> medicineList;
    private String staff_id;
    private String patient_id;
    private String treatment_id;
    
     public Prescription() {
        this.prescription_id = "PH000001";
        this.medicine_total_cost = 0.0;
        this.medicineList = null;
        this.staff_id = "";
        this.patient_id = "";
        this.treatment_id = "";
    }
    
    public Prescription(String previous_prescription_id, LinkedHashMap<String, Medicine> medicineList,
            String staff_id, String patient_id, String treatment_id) {
        this.prescription_id = IDGenerator.generateNextID(previous_prescription_id);
        this.medicineList = medicineList;
        this.medicine_total_cost = this.calculateTotalCost();
        this.staff_id = staff_id;
        this.patient_id = patient_id;
        this.treatment_id = treatment_id;
    }
    
    public double calculateTotalCost(){
        double total = 0.0;
        for(Medicine medicine: medicineList.getAllValues()){
            total += medicine.getMedicineStock() * medicine.getMedicineUnitPrice();
        }
        return total;
    }

    public void setPrescription_id(String prescription_id) {
        this.prescription_id = prescription_id;
    }

    public void setMedicine_total_cost(double medicine_total_cost) {
        this.medicine_total_cost = medicine_total_cost;
    }

    public void setMedicineLIst(LinkedHashMap<String, Medicine> medicineLIst) {
        this.medicineList = medicineLIst;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
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

    public LinkedHashMap<String, Medicine> getMedicineLIst() {
        return medicineList;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getTreatment_id() {
        return treatment_id;
    }    

    @Override
    public String toString() {
        return "\nrescription ID:" + prescription_id + 
               "\nMedicine total cost:" + medicine_total_cost +
                "\nMedicineList:" + medicineList +
                "\nStaff ID:" + staff_id +
                "\nPatient ID:" + patient_id +
                "\nTreatment ID:" + treatment_id;
    }
    
    
}
