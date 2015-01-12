package com.bioqwer.serverApp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Note provide data structure for application  
 */
@Entity
public class Note {
    private String head;
    private String body;
    private Timestamp creationDate;
    private Timestamp lastChangeDate;
    private long noteId;
    private User userByUserId;

    /**
     * Default constructor.
     */
    public Note() {
    }

    /**
     * Specific constructor for {@link com.bioqwer.serverApp.model.Note}.
     *
     * @param ownerUser {@link com.bioqwer.serverApp.model.User} who create {@link com.bioqwer.serverApp.model.Note}.
     * @param head      of {@link com.bioqwer.serverApp.model.Note}.
     * @param body      of {@link com.bioqwer.serverApp.model.Note}.
     */
    public Note(User ownerUser, String head, String body) {
        this.userByUserId = ownerUser;
        this.body = body;
        this.head = head;
    }

    /**
     * Allow to get head of {@link com.bioqwer.serverApp.model.Note}.
     * @return head of {@link com.bioqwer.serverApp.model.Note}.
     */
    @Basic
    @Size(min = 1, message = "min head size = 1")
    @Column(name = "head", nullable = false, insertable = true, updatable = true, length = 255)
    public String getHead() {
        return head;
    }

    /**
     * Allow to set head.
     * @param head can't be null.
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * Allow to get body of {@link com.bioqwer.serverApp.model.Note}.
     * @return body of {@link com.bioqwer.serverApp.model.Note}.
     */
    @Basic
    @Column(name = "body", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getBody() {
        return body;
    }

    /**
     * Allow to set body of {@link com.bioqwer.serverApp.model.Note}
     * @param body can be null
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Allow to get time of creation {@link com.bioqwer.serverApp.model.Note}.
     * @return time when {@link com.bioqwer.serverApp.model.Note} been created.
     */
    @Basic
    @Column(name = "creationDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    /**
     * Allow to set time of creation {@link com.bioqwer.serverApp.model.Note}.
     * @param creationDate of {@link com.bioqwer.serverApp.model.Note}.
     */
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Allow to get time of update {@link com.bioqwer.serverApp.model.Note}.
     * @return time when {@link com.bioqwer.serverApp.model.Note} been updated.
     */
    @Basic
    @Column(name = "lastChangeDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastChangeDate() {
        return lastChangeDate;
    }

    /**
     * Allow to set time of update {@link com.bioqwer.serverApp.model.Note}.
     * @param lastChangeDate of {@link com.bioqwer.serverApp.model.Note}.
     */
    public void setLastChangeDate(Timestamp lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    /**
     * Allow to get id of {@link com.bioqwer.serverApp.model.Note}.
     * @return noteId of {@link com.bioqwer.serverApp.model.Note}.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "noteId", nullable = false, insertable = true, updatable = false)
    public long getNoteId() {
        return noteId;
    }

    /**
     * Allow to set id of {@link com.bioqwer.serverApp.model.Note} for Persistence layer.
     * Method does work for calls <code>note.setNoteId(1)</code>.
     * @param noteId noteId of {@link com.bioqwer.serverApp.model.Note}.
     */
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
                ", userByUserId=" + userByUserId.getUserId() +
                ", createTime=" + creationDate +
                "}\n";
    }

    /**
     * Get owner {@link com.bioqwer.serverApp.model.User} of {@link com.bioqwer.serverApp.model.Note}.
     * @return owner {@link com.bioqwer.serverApp.model.User}.
     */
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    /**
     * Set owner {@link com.bioqwer.serverApp.model.User} of {@link com.bioqwer.serverApp.model.Note}.
     * Method does work for calls <code>note.setUserByUserId(user)</code>.
     * @return owner {@link com.bioqwer.serverApp.model.User}.
     */
    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
