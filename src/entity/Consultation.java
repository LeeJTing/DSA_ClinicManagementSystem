/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.*;

/**
 *
 * @author Tan Kok Hong
 */
public class Consultation {

    private String consultation_Id = "C0001";
    private Date consultation_duration;
    private Date consultation_start_date_time;
    private Date consultation_end_date_time;
    private String appointmentStatus;
    private String type;

    public Consultation() {
        this.consultation_duration = null;
        this.consultation_start_date_time = null;
        this.consultation_end_date_time = null;
        this.appointmentStatus = "";
        this.type = "";
    }

    public Consultation(Date consultation_duration, Date consultation_start_date_time, Date consultation_end_date_time, String appointmentStatus, String type) {
        this.consultation_duration = consultation_duration;
        this.consultation_start_date_time = consultation_start_date_time;
        this.consultation_end_date_time = consultation_end_date_time;
        this.appointmentStatus = appointmentStatus;
        this.type = type;
    }

    public String getConsultation_Id() {
        return consultation_Id;
    }

    public Date getConsultation_duration() {
        return consultation_duration;
    }

    public Date getConsultation_start_date_time() {
        return consultation_start_date_time;
    }

    public Date getConsultation_end_date_time() {
        return consultation_end_date_time;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getType() {
        return type;
    }

    public void setConsultation_Id(String consultation_Id) {
        this.consultation_Id = consultation_Id;
    }

    public void setConsultation_duration(Date consultation_duration) {
        this.consultation_duration = consultation_duration;
    }

    public void setConsultation_start_date_time(Date consultation_start_date_time) {
        this.consultation_start_date_time = consultation_start_date_time;
    }

    public void setConsultation_end_date_time(Date consultation_end_date_time) {
        this.consultation_end_date_time = consultation_end_date_time;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Consultation{" + "consultation_Id=" + consultation_Id + ", consultation_duration=" + consultation_duration + ", consultation_start_date_time=" + consultation_start_date_time + ", consultation_end_date_time=" + consultation_end_date_time + ", appointmentStatus=" + appointmentStatus + ", type=" + type + '}';
    }

}
