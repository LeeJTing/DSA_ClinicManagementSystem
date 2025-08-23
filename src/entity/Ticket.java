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
    private String status;

    public Ticket(String ticketNumber, String status) {
        this.ticketNumber = ticketNumber;
        this.status = status;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public int compareTo(Ticket other) {
        return this.ticketNumber.compareTo(other.ticketNumber);
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketNumber=" + ticketNumber + ", status=" + status + '}';
    }
}