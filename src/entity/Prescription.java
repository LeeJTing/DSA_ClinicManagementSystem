package entity;

import utility.IDGenerator;

import adt.ChainBucket;
import adt.MapInterface;
import java.util.Objects;
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
    private MapInterface<String, Medicine> medicineList;
    private String staff_id;
    private String patient_id;
    private String treatment_id;

    public Prescription() {
        this.prescription_id = "PH000001";
        this.medicine_total_cost = 0.0;
        this.medicineList = new ChainBucket<>();
        this.staff_id = "";
        this.patient_id = "";
        this.treatment_id = "";
    }

    public Prescription(String prescription_id, MapInterface<String, Medicine> medicineList,
            String staff_id, String patient_id, String treatment_id) {
        this.prescription_id = prescription_id;
        this.medicineList = medicineList;
        this.medicine_total_cost = this.calculateTotalCost();
        this.staff_id = staff_id;
        this.patient_id = patient_id;
        this.treatment_id = treatment_id;
    }

    public double calculateTotalCost() {
        double total = 0.0;
        Object[] values = medicineList.getAllValues(); 
        for (Object obj : values) {
            Medicine medicine = (Medicine) obj; 
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

    public void setMedicineList(MapInterface<String, Medicine> medicineLIst) {
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

    public MapInterface<String, Medicine> getMedicineList() {
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
        return "\nrescription ID:" + prescription_id
                + "\nMedicine total cost:" + medicine_total_cost
                + "\nMedicineList:" + medicineList
                + "\nStaff ID:" + staff_id
                + "\nPatient ID:" + patient_id
                + "\nTreatment ID:" + treatment_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prescription other = (Prescription) obj;
        if (Double.doubleToLongBits(this.medicine_total_cost) != Double.doubleToLongBits(other.medicine_total_cost)) {
            return false;
        }
        if (!Objects.equals(this.prescription_id, other.prescription_id)) {
            return false;
        }
        if (!Objects.equals(this.staff_id, other.staff_id)) {
            return false;
        }
        if (!Objects.equals(this.patient_id, other.patient_id)) {
            return false;
        }
        if (!Objects.equals(this.treatment_id, other.treatment_id)) {
            return false;
        }
        return Objects.equals(this.medicineList, other.medicineList);
    }
    
    

}
