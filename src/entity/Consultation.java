/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

/**
 *
 * @author Tan Kok Hong
 */
public class Consultation {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private String consultation_Id = "C0001";
    private int consultation_duration;
    private Date appointment_date;
    private Date consultation_date;
    private Date consultation_start_time;
    private Date consultation_end_time;
    private String appointmentStatus;
    private String type;
    private String patient_Id;
    private String staff_Id;

    public Consultation() {
        this.consultation_Id = "";
        this.appointment_date = null;
        this.consultation_date = null;
        this.consultation_start_time = null;
        this.consultation_end_time = null;
        this.appointmentStatus = "";
        this.type = "";
        this.patient_Id = "";
        this.staff_Id = "";
    }

    public Consultation(String consultation_Id, String appointmentDate, String consultationDate, String consultationStartTime, String consultationEndTime, String appointmentStatus, String type, String patient_Id, String staff_Id) {
        this.consultation_Id = consultation_Id;
        try {
            this.appointment_date = dateFormat.parse(appointmentDate);
            this.consultation_date = dateFormat.parse(consultationDate);
            this.consultation_start_time = timeFormat.parse(consultationStartTime);
            this.consultation_end_time = timeFormat.parse(consultationEndTime);
        } catch (ParseException e) {
            System.out.println("Error parsing date/time strings: " + e.getMessage());
        }
        this.appointmentStatus = appointmentStatus;
        this.type = type;
        this.patient_Id = patient_Id;
        this.staff_Id = staff_Id;
    }

    public String getConsultation_Id() {
        return consultation_Id;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public Date getConsultation_date() {
        return consultation_date;
    }

    public Date getConsultation_start_time() {
        return consultation_start_time;
    }

    public Date getConsultation_end_time() {
        return consultation_end_time;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getType() {
        return type;
    }

    public String getPatient_Id() {
        return patient_Id;
    }

    public String getStaff_Id() {
        return staff_Id;
    }

    public void setConsultation_Id(String consultation_Id) {
        this.consultation_Id = consultation_Id;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public void setConsultation_date(Date consultation_date) {
        this.consultation_date = consultation_date;
    }

    public void setConsultation_start_time(Date consultation_start_time) {
        this.consultation_start_time = consultation_start_time;
    }

    public void setConsultation_end_time(Date consultation_end_time) {
        this.consultation_end_time = consultation_end_time;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPatient_Id(String patient_Id) {
        this.patient_Id = patient_Id;
    }

    public void setStaff_Id(String staff_Id) {
        this.staff_Id = staff_Id;
    }

    public int calculationDurationTimeInMinutes() {
        if (consultation_start_time != null && consultation_end_time != null) {
            long durationInMillis = consultation_end_time.getTime() - consultation_start_time.getTime();
            long durationInMinutes = durationInMillis / (1000 * 60);
            return (int) durationInMinutes;
        }
        return 0;
    }
}
