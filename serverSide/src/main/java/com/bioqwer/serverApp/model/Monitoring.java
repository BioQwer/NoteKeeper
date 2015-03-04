package com.bioqwer.serverApp.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Antony on 04.03.2015.
 */
@Entity
public class Monitoring {
    private long id;
    private long userId;
    private Long noteId;
    private Timestamp logTime;
    private String logData;
    private Note noteByNoteId;
    private User userByUserId;

    @Id
    @Column(name = "id", nullable = false, insertable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = true)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "note_id", nullable = true, insertable = true)
    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
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
        if (o == null || getClass() != o.getClass()) return false;

        Monitoring that = (Monitoring) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (logData != null ? !logData.equals(that.logData) : that.logData != null) return false;
        if (logTime != null ? !logTime.equals(that.logTime) : that.logTime != null) return false;
        if (noteId != null ? !noteId.equals(that.noteId) : that.noteId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (noteId != null ? noteId.hashCode() : 0);
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (logData != null ? logData.hashCode() : 0);
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
}
