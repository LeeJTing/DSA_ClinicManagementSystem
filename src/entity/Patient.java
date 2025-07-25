/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import control.PatientManagement;

/**
 *
 * @author Elwin Koh Soon Yit
 */
public class Patient {

    private String patient_id = "P000001";
    private String patient_name;
    private String patient_contact;
    private String patient_email;
    private Boolean patient_gender;
    private int age;
    private String registration_date;
    private String queue_start_time;
    private String queue_end_time;
    private String queue_start_date;
    private String queue_end_date;

    public Patient() {
        this.patient_id = "";
        this.patient_name = "";
        this.patient_contact = "";
        this.patient_email = "";
        this.patient_gender = null;
        this.age = 0;
        this.registration_date = "";
        this.queue_start_time = "";
        this.queue_end_time = "";
        this.queue_start_date = "";
        this.queue_end_date = "";
    }

    public Patient(String patient_id, String patient_name, String patient_contact, String patient_email, Boolean patient_gender, int age, String registration_date, String queue_start_time, String queue_end_time, String queue_start_date, String queue_end_date) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_contact = patient_contact;
        this.patient_email = patient_email;
        this.patient_gender = patient_gender;
        this.age = age;
        this.registration_date = registration_date;
        this.queue_start_time = queue_start_time;
        this.queue_end_time = queue_end_time;
        this.queue_start_date = queue_start_date;
        this.queue_end_date = queue_end_date;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getPatient_contact() {
        return patient_contact;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public Boolean getPatient_gender() {
        return patient_gender;
    }

    public int getAge() {
        return age;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public String getQueue_start_time() {
        return queue_start_time;
    }

    public String getQueue_end_time() {
        return queue_end_time;
    }

    public String getQueue_start_date() {
        return queue_start_date;
    }

    public String getQueue_end_date() {
        return queue_end_date;
    }

    // Setters
    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setPatient_contact(String patient_contact) {
        this.patient_contact = patient_contact;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public void setPatient_gender(Boolean patient_gender) {
        this.patient_gender = patient_gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public void setQueue_start_time(String queue_start_time) {
        this.queue_start_time = queue_start_time;
    }

    public void setQueue_end_time(String queue_end_time) {
        this.queue_end_time = queue_end_time;
    }

    public void setQueue_start_date(String queue_start_date) {
        this.queue_start_date = queue_start_date;
    }

    public void setQueue_end_date(String queue_end_date) {
        this.queue_end_date = queue_end_date;
    }

    public void register() {
        this.patient_id = PatientManagement.generateNextPatientId();

        java.time.LocalDate currentDate = java.time.LocalDate.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.registration_date = currentDate.format(formatter);

        System.out.println(String.format("Patient registered successfully!"));
        System.out.println(String.format("Patient ID: %s", this.patient_id));
        System.out.println(String.format("Patient Name: %s", this.patient_name));
        System.out.println(String.format("Registration Date: %s", this.registration_date));

    }

    public void deleteAccount() {
        System.out.println("Patient account deleted: " + this.patient_id);
    }

    public void editProfile() {
        System.out.println("Editing profile for patient: " + this.patient_name);
    }

    public void displayProfile() {
        System.out.println(String.format("=== Patient Profile ==="));
        System.out.println(String.format("Patient ID      : %s", this.patient_id));
        System.out.println(String.format("Name            : %s", this.patient_name));
        System.out.println(String.format("Contact         : %s", this.patient_contact));
        System.out.println(String.format("Email           : %s", this.patient_email));
        System.out.println(String.format("Gender          : %s", this.patient_gender ? "Male" : "Female"));
        System.out.println(String.format("Age             : %d years old", this.age));
        System.out.println(String.format("Registration    : %s", this.registration_date));
        System.out.println(String.format("Queue Start     : %s %s", this.queue_start_date, this.queue_start_time));
        System.out.println(String.format("Queue End       : %s %s", this.queue_end_date, this.queue_end_time));
        System.out.println(String.format("======================="));
    }

    public void searchPatient() {
        System.out.println("Searching for patient...");
    }

    public void realTimeQueueDisplay() {
        System.out.println("Displaying real-time queue for patient: " + this.patient_name);
    }

    public String toDataString() {
    return String.format("%s,%s,%s,%s,%s,%d,%s,%s,%s,%s,%s",
        patient_id,
        patient_name,
        patient_contact,
        patient_email,
        patient_gender,
        age,
        registration_date,
        queue_start_time,
        queue_end_time,
        queue_start_date,
        queue_end_date
    );
}
    @Override
    public String toString() {
        return String.format("Patient{patient_id='%-8s', patient_name='%s20', patient_contact='%17s', patient_email='%s', patient_gender=%s, age=%d, registration_date='%s', queue_start_time='%s', queue_start_date='%s', queue_end_date='%s', queue_end_time='%s'}",
                patient_id, patient_name, patient_contact, patient_email, patient_gender ? "Male" : "Female", age, registration_date, queue_start_time, queue_start_date, queue_end_date, queue_end_time);
    }
}
