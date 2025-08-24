/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Lwin
 */
public class Ticket implements Comparable<Ticket> {

    private String ticketNumber;
    private String ticketStatus;
    private Date queueStart;
    private Date queueEnd;
    private String patientID;

    public Ticket() {
    }

    public Ticket(String ticketNumber, String ticketStatus) {
        this.ticketNumber = ticketNumber;
        this.ticketStatus = ticketStatus;
    }

    public Ticket(String ticketNumber, String ticketStatus, Date queueStart, Date queueEnd, String patientID) {
        this.ticketNumber = ticketNumber;
        this.ticketStatus = ticketStatus;
        this.queueStart = queueStart;
        this.queueEnd = queueEnd;
        this.patientID = patientID;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public Date getQueueStart() {
        return queueStart;
    }

    public void setQueueStart(Date queueStart) {
        this.queueStart = queueStart;
    }

    public Date getQueueEnd() {
        return queueEnd;
    }

    public void setQueueEnd(Date queueEnd) {
        this.queueEnd = queueEnd;
    }

    @Override
    public int compareTo(Ticket other) {
        return this.ticketNumber.compareTo(other.ticketNumber);
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketNumber=" + ticketNumber + ", ticketStatus=" + ticketStatus + ", queueStart=" + queueStart + ", queueEnd=" + queueEnd + '}';
    }
}
