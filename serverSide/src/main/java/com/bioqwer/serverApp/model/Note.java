package com.bioqwer.serverApp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Note @Entity
 */
@Entity
public class Note {
    private String head;
    private String body;
    private Timestamp creationDate;
    private Timestamp lastChangeDate;
    private long noteId;
    private User userByUserId;

    public Note() {
    }

    public Note(User ownerUser, String head, String body) {
        this.userByUserId = ownerUser;
        this.body = body;
        this.head = head;
    }

    @Basic
    @Size(min = 1, message = "min head size = 1")
    @Column(name = "head", nullable = false, insertable = true, updatable = true, length = 255)
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Basic
    @Column(name = "body", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "creationDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "lastChangeDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Timestamp lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "noteId", nullable = false, insertable = true, updatable = false)
    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (noteId != note.noteId) return false;
        if (body != null ? !body.equals(note.body) : note.body != null) return false;
        if (creationDate != null ? !creationDate.equals(note.creationDate) : note.creationDate != null) return false;
        if (head != null ? !head.equals(note.head) : note.head != null) return false;
        if (lastChangeDate != null ? !lastChangeDate.equals(note.lastChangeDate) : note.lastChangeDate != null)
            return false;
        if (userByUserId != null ? !userByUserId.equals(note.userByUserId) : note.userByUserId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastChangeDate != null ? lastChangeDate.hashCode() : 0);
        result = 31 * result + (int) (noteId ^ (noteId >>> 32));
        result = 31 * result + (userByUserId != null ? userByUserId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ",head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", userByUserId=" + userByUserId +
                ", createTime=" + creationDate +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
