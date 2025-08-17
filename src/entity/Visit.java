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
public class Visit {

    private Date queueStart;
    private Date queueEnd;
    private String ticket;

    public Visit(Date queueStart, Date queueEnd, String ticket) {
        this.queueStart = queueStart;
        this.queueEnd = queueEnd;
        this.ticket = ticket;
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Visit{" + "queueStart=" + queueStart + ", queueEnd=" + queueEnd + ", ticket=" + ticket + '}';
    }
}
