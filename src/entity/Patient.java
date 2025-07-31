/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import control.PatientManagement;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author Elwin Koh Soon Yit
 */
public class Patient {

    private Date currentDate = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private String patient_id = "P000001";
    private String patient_name;
    private String patient_contact;
    private String patient_email;
    private String patient_gender;
    private int age;
    private Date registration_date;
    private Date queue_start;
    private Date queue_end;

    public Patient() {
        this.patient_id = "";
        this.patient_name = "";
        this.patient_contact = "";
        this.patient_email = "";
        this.patient_gender = "";
        this.age = 0;
        this.registration_date = null;
        this.queue_start = null;
        this.queue_end= null;
    }

    public Patient(String patient_id, String patient_name, String patient_contact, String patient_email, String patient_gender, int age, Date registration_date, Date queue_start, Date queue_end) {
        this.patient_id = PatientManagement.generateNextPatientId();
        this.patient_name = patient_name;
        this.patient_contact = patient_contact;
        this.patient_email = patient_email;
        this.patient_gender = patient_gender;
        this.age = age;
        String newRegisterDate = dateFormat.format(currentDate);
        try {
            this.registration_date = dateFormat.parse(newRegisterDate);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }        
        this.queue_start = queue_start;
        this.queue_end = queue_end;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_contact() {
        return patient_contact;
    }

    public void setPatient_contact(String patient_contact) {
        this.patient_contact = patient_contact;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public String getPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(String patient_gender) {
        this.patient_gender = patient_gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public Date getQueue_start() {
        return queue_start;
    }

    public void setQueue_start(Date queue_start) {
        this.queue_start = queue_start;
    }

    public Date getQueue_end() {
        return queue_end;
    }

    public void setQueue_end(Date queue_end) {
        this.queue_end = queue_end;
    }

    
    public void displayProfile() {
        System.out.println(String.format("=== Patient Profile ==="));
        System.out.println(String.format("Patient ID      : %s", this.patient_id));
        System.out.println(String.format("Name            : %s", this.patient_name));
        System.out.println(String.format("Contact         : %s", this.patient_contact));
        System.out.println(String.format("Email           : %s", this.patient_email));
        System.out.println(String.format("Gender          : %s", this.patient_gender));
        System.out.println(String.format("Age             : %d years old", this.age));
        System.out.println(String.format("Registration    : %s", this.registration_date));
        System.out.println(String.format("Queue Start     : %s", this.queue_start));
        System.out.println(String.format("Queue End       : %s", this.queue_end));
        System.out.println(String.format("======================="));
    }

    public String toDataString() {
        return String.format("%s,%s,%s,%s,%s,%d,%s,%s,%s",
                patient_id,
                patient_name,
                patient_contact,
                patient_email,
                patient_gender,
                age,
                registration_date,
                queue_start,
                queue_end
        );
    }

    @Override
     public String toString() {
        return String.format(
                "Patient{patient_id='%s', patient_name='%s', patient_contact='%s', patient_email='%s', patient_gender='%s', age=%d, registration_date='%s', queue_start='%s', queue_end='%s'}",
                patient_id,
                patient_name,
                patient_contact,
                patient_email,
                patient_gender,
                age,
                registration_date,
                queue_start,
                queue_end
        );
    }
}
