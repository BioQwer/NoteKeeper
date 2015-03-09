package com.bioqwer.serverApp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Antony on 04.03.2015.
 */
@Entity
public class Monitoring {
    private long id;
    private Timestamp logTime;
    private String logData;
    private Note noteByNoteId;
    private User userByUserId;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_time", nullable = false, insertable = true, updatable = true)
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Basic
    @Column(name = "log_data", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monitoring)) return false;

        Monitoring that = (Monitoring) o;

        if (id != that.id) return false;
        if (logData != null ? !logData.equals(that.logData) : that.logData != null) return false;
        if (logTime != null ? !logTime.equals(that.logTime) : that.logTime != null) return false;
        if (noteByNoteId != null ? !noteByNoteId.equals(that.noteByNoteId) : that.noteByNoteId != null) return false;
        if (userByUserId != null ? !userByUserId.equals(that.userByUserId) : that.userByUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (logData != null ? logData.hashCode() : 0);
        result = 31 * result + (noteByNoteId != null ? noteByNoteId.hashCode() : 0);
        result = 31 * result + (userByUserId != null ? userByUserId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "note_id", referencedColumnName = "noteId")
    public Note getNoteByNoteId() {
        return noteByNoteId;
    }

    public void setNoteByNoteId(Note noteByNoteId) {
        this.noteByNoteId = noteByNoteId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @Override
    public String toString() {
        return "Monitoring{" +
                "id=" + id +
                ", logTime=" + logTime +
                ", logData='" + logData + '\'' +
                ", noteByNoteId=" + noteByNoteId +
                ", userByUserId=" + userByUserId +
                '}';
    }
}
