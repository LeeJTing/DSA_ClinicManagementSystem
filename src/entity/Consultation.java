package entity;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Tan Kok Hong
 */
public class Consultation implements Comparable<Consultation> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private String consultation_Id = "C000001";
    private Date appointment_date;
    private Date consultation_date;
    private Date consultation_start_time;
    private Date consultation_end_time;
    private String appointmentStatus;
    private String type;
    private String patient_Id;
    private String staff_Id;
    private static String compare = "appointmentStatus";

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

    public Consultation(String consultation_Id, Date appointmentDate, Date consultationDate, Date consultationStartTime, Date consultationEndTime, String appointmentStatus, String type, String patient_Id, String staff_Id) {
        this.consultation_Id = consultation_Id;
        this.appointment_date = appointmentDate;
        this.consultation_date = consultationDate;
        this.consultation_start_time = consultationStartTime;
        this.consultation_end_time = consultationEndTime;
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

    public static String getCompare() {
        return compare;
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

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public int calculationDurationTimeInMinutes() {
        if (consultation_start_time != null && consultation_end_time != null) {
            long durationInMillis = consultation_end_time.getTime() - consultation_start_time.getTime();
            long durationInMinutes = durationInMillis / (1000 * 60);
            return (int) durationInMinutes;
        }
        return 0;
    }

    public double calConsultationCost() {
        int durationInMinutes = calculationDurationTimeInMinutes();
        int durationInHour;

        if (durationInMinutes < 60) {
            durationInHour = 1;
        } else if (durationInMinutes < 120) {
            durationInHour = 2;
        } else {
            durationInHour = 3;
        }
        return durationInHour * 50.0;
    }

    @Override
    public String toString() {

        String consultationTime = String.format("%s-%s",
                timeFormat.format(consultation_start_time),
                timeFormat.format(consultation_end_time)
        );

        return String.format("\t\t | %-14s |  %-15s | %-17s | %-17s | %-10s | %-7s |",
                consultation_Id,
                dateFormat.format(appointment_date),
                dateFormat.format(consultation_date),
                consultationTime,
                appointmentStatus,
                type
        );
    }

    public String toStaffString() {
        int tableWidth = 110; // total width including borders
        String border = "-".repeat(tableWidth);

        return toString() + String.format(" %7s |", patient_Id) + String.format(" \n\t\t %s", border);
    }

    public String toAllString() {
        int tableWidth = 122; // total width including borders
        String border = "-".repeat(tableWidth);

        return toString() + String.format(" %7s | %7s   |", staff_Id, patient_Id) + String.format(" \n\t\t %s", border);
    }

    @Override
    public int compareTo(Consultation other) {

        if (this.compare.equals("appointmentStatus")) {
            int th = this.getStatusRank(this.appointmentStatus);
            int ot = other.getStatusRank(other.getAppointmentStatus());
            return Integer.compare(ot, th);

//            return th - ot;
        } else if (this.compare.equals("consultation_date")) {
            int dateCompare = other.consultation_date.compareTo(this.consultation_date);
            if (dateCompare != 0) {
                return dateCompare; // different dates, sort by date
            }
            // Same date → compare by start time
            return other.consultation_start_time.compareTo(this.consultation_start_time);
        } else if (this.compare.equals("year")) {
            if (this.consultation_date == null || other.consultation_date == null) {
                return 0;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String thisYear = sdf.format(this.consultation_date);
            String otherYear = sdf.format(other.consultation_date);
            return thisYear.compareTo(otherYear);

        } else if (this.compare.equals("month")) {
            if (this.consultation_date == null || other.consultation_date == null) {
                return 0;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String thisMonth = sdf.format(this.consultation_date);
            String otherMonth = sdf.format(other.consultation_date);
            return thisMonth.compareTo(otherMonth);

        } else if (this.compare.equals("day")) {
            if (this.consultation_date == null || other.consultation_date == null) {
                return 0;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String thisDay = sdf.format(this.consultation_date);
            String otherDay = sdf.format(other.consultation_date);
            return thisDay.compareTo(otherDay);
        } else {
            return 0;
        }
    }

    public int getStatusRank(String status) {
        if (status == null) {
            return 4;
        } else {
            return status.length();
        }
    }

}
