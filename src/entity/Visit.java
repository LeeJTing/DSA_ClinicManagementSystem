package entity;

import java.util.Date;

/**
 *
 * @author Elwin Koh Soon Yit
 */
public class Visit {

    private Date queueStart;
    private Date queueEnd;

    public Visit(Date queueStart, Date queueEnd) {
        this.queueStart = queueStart;
        this.queueEnd = queueEnd;
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
    public String toString() {
        return "Visit{" + "queueStart=" + queueStart + ", queueEnd=" + queueEnd + '}';
    }

}
