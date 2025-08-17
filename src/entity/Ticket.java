/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Lwin
 */
public class Ticket {
    private String ticketNumber;
    private String patientId;
    private String staffId;

    public Ticket(String ticketNumber, String patientId, String staffId) {
        this.ticketNumber = ticketNumber;
        this.patientId = patientId;
        this.staffId = staffId;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getStaffId() {
        return staffId;
    }
    
     @Override
    public String toString() {
        return "Ticket{" + "ticketNumber=" + ticketNumber + ", patientId=" + patientId + ", staffId=" + staffId + '}';
    }
}
