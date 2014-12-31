package com.bioqwer.serverApp.controller;

/**
 * Created by Antony on 28.12.2014.
 */
public class Constraint {
    private String property;
    private String value;
    private String message;

    public Constraint(String property, String value, String message) {
        this.property = property;
        this.value = value;
        this.message = message;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
