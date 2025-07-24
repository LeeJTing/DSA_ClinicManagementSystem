/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import utility.IDGenerator;

/**
 *
 * @author Lee Jun Ting
 */
public class Treatment {
    private static String treatment_id = "T000001"; // start from T000001
    private String disease;
    private String treatment_advice;
    private Date treatment_period;
    private String staff_id;
    private boolean isScan;
    private String remark;

    public Treatment() {
        
    }

    public Treatment(String disease, String treatment_advice, Date treatment_period, String staff_id, boolean isScan, String remark) {
        this.disease = disease;
        this.treatment_advice = treatment_advice;
        this.treatment_period = treatment_period;
        this.staff_id = staff_id;
        this.isScan = isScan;
        this.remark = remark;
    }

    public String getTreatment_id() {
        return treatment_id;
    }

    public String getDisease() {
        return disease;
    }

    public String getTreatment_advice() {
        return treatment_advice;
    }

    public Date getTreatment_period() {
        return treatment_period;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public boolean isIsScan() {
        return isScan;
    }

    public String getRemark() {
        return remark;
    }

    public void setTreatment_id(String treatment_id) {
        Treatment.treatment_id = treatment_id;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setTreatment_advice(String treatment_advice) {
        this.treatment_advice = treatment_advice;
    }

    public void setTreatment_period(Date treatment_period) {
        this.treatment_period = treatment_period;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setIsScan(boolean isScan) {
        this.isScan = isScan;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
    
}
