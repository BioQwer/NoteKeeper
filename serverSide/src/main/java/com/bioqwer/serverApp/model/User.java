package com.bioqwer.serverApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Provide data structure of {@link com.bioqwer.serverApp.model.User} for application logic
 */
@Entity
public class User {
    /**
     * Pattern for {@link com.bioqwer.serverApp.model.User#password}
     */
    public static final String PASSWORD_PATTERN = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z]).*$";
    /**
     * Pattern for {@link com.bioqwer.serverApp.model.User#email}
     */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * {@link com.bioqwer.serverApp.model.User} ID for Storage.
     */
    private long userId;
    /**
     * Email address if {@link com.bioqwer.serverApp.model.User}.
     */
    private String email;
    /**
     * Username of {@link com.bioqwer.serverApp.model.User}.
     * Sing in provide bu this field.
     */
    private String login;
    /**
     * Password of {@link com.bioqwer.serverApp.model.User}.
     */
    private String password;
    /**
     * Collection of {@link com.bioqwer.serverApp.model.User} {@link com.bioqwer.serverApp.model.Note}s.
     */
    @JsonIgnore
    private Collection<Note> notesByUserId;
    private Collection<Monitoring> monitoringsByUserId;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Specific constructor for {@link com.bioqwer.serverApp.model.User}.
     * @param email {@link com.bioqwer.serverApp.model.User#email} of {@link com.bioqwer.serverApp.model.User}.
     * @param login {@link com.bioqwer.serverApp.model.User#login} of {@link com.bioqwer.serverApp.model.User}.
     * @param password {@link com.bioqwer.serverApp.model.User#password} of {@link com.bioqwer.serverApp.model.User}.
     */
    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    /**
     * Allow to get {@link com.bioqwer.serverApp.model.User#userId} of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User#userId}.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "userId", nullable = false, insertable = true, updatable = false)
    public long getUserId() {
        return userId;
    }

    /**
     * Allow to set {@link com.bioqwer.serverApp.model.User#userId}.
     * @param userId new {@link com.bioqwer.serverApp.model.User#userId} of {@link com.bioqwer.serverApp.model.User}.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Allow to get {@link com.bioqwer.serverApp.model.User#email} of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User#email}.
     */
    @Basic
    @Pattern(regexp = EMAIL_PATTERN, message = "email not valid")
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 40, unique = true)
    public String getEmail() {
        return email;
    }

    /**
     * Allow to set {@link com.bioqwer.serverApp.model.User#email}.
     * @param email new {@link com.bioqwer.serverApp.model.User#email} of {@link com.bioqwer.serverApp.model.User}.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Allow to get {@link com.bioqwer.serverApp.model.User#login} of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User#login}.
     */
    @Basic
    @NotNull
    @Size(min = 6, message = "min login size 6")
    @Column(name = "login", nullable = false, insertable = true, updatable = true, length = 40, unique = true)
    public String getLogin() {
        return login;
    }

    /**
     * Allow to set {@link com.bioqwer.serverApp.model.User#login}.
     * @param login new {@link com.bioqwer.serverApp.model.User#login} of {@link com.bioqwer.serverApp.model.User}.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Allow to get {@link com.bioqwer.serverApp.model.User#password} of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User#password}.
     */
    @Basic
    @NotNull
    @Pattern(regexp = PASSWORD_PATTERN, message = "password not valid")
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 40)
    public String getPassword() {
        return password;
    }

    /**
     * Allow to set {@link com.bioqwer.serverApp.model.User#password}.
     * @param password new {@link com.bioqwer.serverApp.model.User#password} of {@link com.bioqwer.serverApp.model.User}.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (notesByUserId != null ? !notesByUserId.equals(user.notesByUserId) : user.notesByUserId != null)
            return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (notesByUserId != null ? notesByUserId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * Allow to get all {@link com.bioqwer.serverApp.model.Note}s of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User#notesByUserId}.
     */
    @OneToMany(mappedBy = "userByUserId", fetch = FetchType.EAGER)
    public Collection<Note> getNotesByUserId() {
        return notesByUserId;
    }

    /**
     * Allow to set {@link java.util.Collection} of {@link com.bioqwer.serverApp.model.Note}s for {@link com.bioqwer.serverApp.model.User}.
     * @param notesByUserId new {@link com.bioqwer.serverApp.model.User#notesByUserId} of {@link com.bioqwer.serverApp.model.User}.
     */
    public void setNotesByUserId(Collection<Note> notesByUserId) {
        this.notesByUserId = notesByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Monitoring> getMonitoringsByUserId() {
        return monitoringsByUserId;
    }

    public void setMonitoringsByUserId(Collection<Monitoring> monitoringsByUserId) {
        this.monitoringsByUserId = monitoringsByUserId;
    }
}
