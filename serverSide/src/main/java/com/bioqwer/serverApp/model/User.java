package com.bioqwer.serverApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * User @Entity
 */
@Entity
public class User {
    public static final String PASSWORD_PATTERN = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z]).*$";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private long userId;
    private String email;
    private String login;
    private String password;
    @JsonIgnore
    private Collection<Note> notesByUserId;


    public User() {
    }

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "userId", nullable = false, insertable = true, updatable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Pattern(regexp = EMAIL_PATTERN, message = "email not valid")
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 40, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "login", nullable = false, insertable = true, updatable = true, length = 40, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @NotNull
    @Pattern(regexp = PASSWORD_PATTERN, message = "password not valid")
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 40)
    public String getPassword() {
        return password;
    }

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
        return !(password != null ? !password.equals(user.password) : user.password != null);

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

    @OneToMany(mappedBy = "userByUserId", fetch = FetchType.EAGER)
    public Collection<Note> getNotesByUserId() {
        return notesByUserId;
    }

    public void setNotesByUserId(Collection<Note> notesByUserId) {
        this.notesByUserId = notesByUserId;
    }
}
