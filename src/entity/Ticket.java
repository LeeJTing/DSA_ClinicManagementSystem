/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Lwin
 */
public class Ticket implements Comparable<Ticket> {
    private String ticketNumber;
    private String ticketStatus;

    public Ticket(String ticketNumber, String ticketStatus) {
        this.ticketNumber = ticketNumber;
        this.ticketStatus = ticketStatus;
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

    @Override
    public int compareTo(Ticket other) {
        return this.ticketNumber.compareTo(other.ticketNumber);
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketNumber=" + ticketNumber + ", ticketStatus=" + ticketStatus + '}';
    }
}