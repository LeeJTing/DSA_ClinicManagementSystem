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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private String patient_id;
    private String patient_name;
    private String patient_contact;
    private String patient_email;
    private String patient_gender;
    private int age;
    private Date registration_date;
    private Visit[] visits = new Visit[10]; // allows up to 10 visits
    private int visitCount = 0;

    public Patient() {
        this.patient_id = "";
        this.patient_name = "";
        this.patient_contact = "";
        this.patient_email = "";
        this.patient_gender = "";
        this.age = 0;
        this.registration_date = null;
    }

    public Patient(String patientId, String name, String contact, String email, String gender, int age, Date registrationDate) {
        this.patient_id = patientId;
        this.patient_name = name;
        this.patient_contact = contact;
        this.patient_email = email;
        this.patient_gender = gender;
        this.age = age;
        String newRegisterDate = dateFormat.format(currentDate);
        try {
            this.registration_date = dateFormat.parse(newRegisterDate);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }

    public void addVisit(Date queueStart, Date queueEnd, String ticket) {
        if (visitCount < visits.length) {
            visits[visitCount++] = new Visit(queueStart, queueEnd, ticket);
        }
    }

    public Visit[] getVisits() {
        return visits;
    }

    public void setVisits(Visit[] visits) {
        this.visits = visits;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
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

    @Override
    public String toString() {
        return "Patient{" + "currentDate=" + currentDate + ", dateFormat=" + dateFormat + ", patient_id=" + patient_id + ", patient_name=" + patient_name + ", patient_contact=" + patient_contact + ", patient_email=" + patient_email + ", patient_gender=" + patient_gender + ", age=" + age + ", registration_date=" + registration_date + ", visits=" + visits + ", visitCount=" + visitCount + '}';
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }
}
