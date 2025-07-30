/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import java.text.SimpleDateFormat;
import utility.IDGenerator;

/**
 *
 * @author Lee Jun Ting
 */
public class Treatment {
    
    private SimpleDateFormat dateForm = new SimpleDateFormat("dd-MM-yyyy");
    
    private String treatment_id; // start from T000001
    private String consultation_id;
    private String disease;
    private String treatment_advice;
    private Date treatment_date;
    private String staff_id;
    private String patient_id;
    private boolean isScan;
    private String remark;

    public Treatment() {
        this.treatment_id = "T000000";
        this.consultation_id = "";
        this.disease = "";
        this.treatment_advice = "";
        this.treatment_date = new Date();
        this.staff_id = "";
        this.patient_id = "";
        this.isScan = false;
        this.remark = "";
    }

    public Treatment(String previousTreatmentID, String consultation_id, String disease, String treatment_advice, Date treatment_date, String staff_id, String patient_id,boolean isScan, String remark) {
        this.treatment_id = IDGenerator.generateNextID(previousTreatmentID);
        this.consultation_id = consultation_id;
        this.disease = disease;
        this.treatment_advice = treatment_advice;
        this.treatment_date = treatment_date;
        this.staff_id = staff_id;
        this.patient_id = patient_id;
        this.isScan = isScan;
        this.remark = remark;
    }

    public String getTreatment_id() {
        return treatment_id;
    }

    public String getConsultation_id() {
        return consultation_id;
    }
    
    public String getDisease() {
        return disease;
    }

    public String getTreatment_advice() {
        return treatment_advice;
    }

    public Date getTreatment_date() {
        return treatment_date;
    }

    public String getStaff_id() {
        return staff_id;
    }
    
    public String getPatient_id(){
        return patient_id;
    }

    public boolean isIsScan() {
        return isScan;
    }

    public String getRemark() {
        return remark;
    }

    public void setTreatment_id(String treatment_id) {
        this.treatment_id = treatment_id;
    }

    public void setConsultation_id(String consultation_id) {
        this.consultation_id = consultation_id;
    }
    
    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setTreatment_advice(String treatment_advice) {
        this.treatment_advice = treatment_advice;
    }

    public void setTreatment_date(Date treatment_date) {
        this.treatment_date = treatment_date;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }
    
    public void setPatient_id(String patient_id){
        this.patient_id = patient_id;
    }

    public void setIsScan(boolean isScan) {
        this.isScan = isScan;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Override
    public String toString(){
        return "\nTreatment ID: " + treatment_id 
                + "\nDisease: " + disease 
                + "\nTreatment Advice: " + treatment_advice
                + "\nTreatment Date: " + treatment_date
                + "\nStaff ID: " + staff_id
                + "\nPatient ID: " + patient_id
                + "\nScan: " + (isScan ? "Yes" : "No")
                + "\nRemark: " + remark;
    }
    
}
