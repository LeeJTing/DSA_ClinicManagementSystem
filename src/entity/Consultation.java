public class Consultation {
    

    private String consultation_Id = "C0001";
    private String consultation_duration;
    private String consultation_start_time;
    private String consultation_end_time;
    private String consultation_start_date;
    private String consultation_end_date;
    private String appointmentStatus;
    private String type;

    public Consultation() {
        this.consultation_Id = "";
        this.consultation_duration = "";
        this.consultation_start_time = "";
        this.consultation_end_time = "";
        this.consultation_start_date = "";
        this.consultation_end_date = "";
        this.appointmentStatus = "";
        this.type = "";
    }

    public Consultation(String consultation_Id, String consultation_duration, String consultation_start_time, String consultation_end_time, String consultation_start_date, String consulation_end_date, String appointmentStatus, String type) {
        this.consultation_Id = consultation_Id;
        this.consultation_duration = consultation_duration;
        this.consultation_start_time = consultation_start_time;
        this.consultation_end_time = consultation_end_time;
        this.consultation_start_date = consultation_start_date;
        this.consultation_end_date = consulation_end_date;
        this.appointmentStatus = appointmentStatus;
        this.type = type;
    }

    public String getConsultation_Id() {
        return consultation_Id;
    }

    public String getConsultation_duration() {
        return consultation_duration;
    }

    public String getConsultation_start_time() {
        return consultation_start_time;
    }

    public String getConsultation_end_time() {
        return consultation_end_time;
    }

    public String getConsultation_start_date() {
        return consultation_start_date;
    }

    public String getConsultation_end_date() {
        return consultation_end_date;
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

    public void setConsultation_duration(String consultation_duration) {
        this.consultation_duration = consultation_duration;
    }

    public void setConsultation_start_time(String consultation_start_time) {
        this.consultation_start_time = consultation_start_time;
    }

    public void setConsultation_end_time(String consultation_end_time) {
        this.consultation_end_time = consultation_end_time;
    }

    public void setConsultation_start_date(String consultation_start_date) {
        this.consultation_start_date = consultation_start_date;
    }

    public void setConsultation_end_date(String consultation_end_date) {
        this.consultation_end_date = consultation_end_date;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Consultation{" + "consultation_Id=" + consultation_Id + ", consultation_duration=" + consultation_duration + ", consultation_start_time=" + consultation_start_time + ", consultation_end_time=" + consultation_end_time + ", consultation_start_date=" + consultation_start_date + ", consultation_end_date=" + consultation_end_date + ", appointmentStatus=" + appointmentStatus + ", type=" + type + '}';
    }

}