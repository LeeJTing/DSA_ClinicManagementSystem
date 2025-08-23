/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Wong Wei Xin
 */
public class Staff implements Comparable<Staff> {

    private String staff_id;
    private String staff_password;
    private String staff_name;
    private String staff_position;
    private String staff_contact;
    private String staff_email;
    private int education_level;
    private int service_duration;
    private String dutyStatus;
    private LocalDate joined_date;
    private int clinic_years;
    private int score;
    private int patient_count;
    private int consultation_duration;
    private String compare;

    //Default Constuctor
    public Staff() {
        this.staff_id = "";
        this.staff_password = "";
        this.staff_name = "";
        this.staff_position = "";
        this.staff_contact = "";
        this.staff_email = "";
        this.education_level = 0;
        this.service_duration = 0;
        this.dutyStatus = "";
    }

    //parameterized constructor
    public Staff(String staff_id, String staff_password, String staff_name, String staff_position, String staff_contact, String staff_email, int education_level, int service_duration, String dutyStatus, String joined_dateStr) {
        this.staff_id = staff_id;
        this.staff_password = staff_password;
        this.staff_name = staff_name;
        this.staff_position = staff_position;
        this.staff_contact = staff_contact;
        this.staff_email = staff_email;
        this.education_level = education_level;
        this.service_duration = service_duration;
        this.dutyStatus = dutyStatus;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.joined_date = LocalDate.parse(joined_dateStr, formatter);
    }

    public Staff(Staff staff, int firstValue, int secondValue, String compare) {
        this.staff_id = staff.getStaffID();
        this.staff_password = staff.getStaffPassword();
        this.staff_name = staff.getStaffName();
        this.staff_position = staff.getStaffPosition();
        this.staff_contact = staff.getStaffContact();
        this.staff_email = staff.getStaffEmail();
        this.education_level = staff.getEducationalLevel();
        this.service_duration = staff.getServiceDuration();
        this.dutyStatus = staff.getDutyStatus();
        this.joined_date = staff.getJoinedDate();
        this.compare = compare;

        if ("score".equals(compare)) {
            this.clinic_years = firstValue;
            this.score = secondValue;
        } else if ("patients".equals(compare)) {
            this.patient_count = firstValue;
            this.consultation_duration = secondValue;
        }
    }

    public Staff(Staff other) {
        this.staff_id = other.staff_id;
        this.staff_password = other.staff_password;
        this.staff_name = other.staff_name;
        this.staff_position = other.staff_position;
        this.staff_contact = other.staff_contact;
        this.staff_email = other.staff_email;
        this.education_level = other.education_level;
        this.service_duration = other.service_duration;
        this.dutyStatus = other.dutyStatus;
        this.joined_date = other.joined_date;
    }

    //getter
    public String getStaffID() {
        return staff_id;
    }

    public String getStaffPassword() {
        return staff_password;
    }

    public String getStaffName() {
        return staff_name;
    }

    public String getStaffPosition() {
        return staff_position;
    }

    public String getStaffContact() {
        return staff_contact;
    }

    public String getStaffEmail() {
        return staff_email;
    }

    public int getEducationalLevel() {
        return education_level;
    }

    public int getServiceDuration() {
        return service_duration;
    }

    public String getDutyStatus() {
        return dutyStatus;
    }

    public LocalDate getJoinedDate() {
        return joined_date;
    }

    public int getClinicYears() {
        return clinic_years;
    }

    public int getScore() {
        return score;
    }

    public int getPatientCount() {
        return patient_count;
    }

    public int getConsultationDuration() {
        return consultation_duration;
    }

    //setter
    public void setStaffID(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setStaffPassword(String staff_password) {
        this.staff_password = staff_password;
    }

    public void setStaffName(String staff_name) {
        this.staff_name = staff_name;
    }

    public void setStaffPosition(String staff_position) {
        this.staff_position = staff_position;
    }

    public void setStaffContact(String staff_contact) {
        this.staff_contact = staff_contact;
    }

    public void setStaffEmail(String staff_email) {
        this.staff_email = staff_email;
    }

    public void setEducationalLevel(int education_level) {
        this.education_level = education_level;
    }

    public void setServiceDuration(int service_duration) {
        this.service_duration = service_duration;
    }

    public void setDutyStatus(String dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    @Override
    public String toString() {
//        staffId, staffName, clinicYrs, industryYrs, education, score)
        return String.format("\t\t\t\t| %-10s | %-20s | %-13d | %-13d | %-14d | %-15d |", staff_id, staff_name, clinic_years, service_duration, education_level, score);
    }

    @Override
    public int compareTo(Staff s) {
        if (this.compare == null) {
            return 0;
        }
        if (this.compare.equals("clinic_years")) {
            return Integer.compare(this.clinic_years, s.clinic_years);
        } else if (this.compare.equals("score")) {
            return Integer.compare(this.score, s.score);
        } else if (this.compare.equals("patients")) {
            return Integer.compare(this.patient_count, s.patient_count);
        } else if (this.compare.equals("duration")) {
            return Integer.compare(this.consultation_duration, s.consultation_duration);
        } else if (this.compare.equalsIgnoreCase("position")) {
            return this.staff_position.compareToIgnoreCase(s.staff_position);
        } else {
            return 0;
        }
    }

}
