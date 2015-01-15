package com.bioqwer.serverApp.controller;

/**
 * Provide Constraint for {@link com.bioqwer.serverApp.model}.
 */
class Constraint {
    private String property;
    private String value;
    private String message;

    /**
     * Specific constructor for {@link com.bioqwer.serverApp.controller.Constraint}.
     *
     * @param property {@link com.bioqwer.serverApp.controller.Constraint#property} of {@link com.bioqwer.serverApp.controller.Constraint}.
     * @param value    {@link com.bioqwer.serverApp.controller.Constraint#value} of {@link com.bioqwer.serverApp.controller.Constraint}.
     * @param message  {@link com.bioqwer.serverApp.controller.Constraint#message} of {@link com.bioqwer.serverApp.controller.Constraint}.
     */
    public Constraint(String property, String value, String message) {
        this.property = property;
        this.value = value;
        this.message = message;
    }

    /**
     * Allow to get {@link com.bioqwer.serverApp.controller.Constraint#property} of {@link com.bioqwer.serverApp.controller.Constraint}.
     * @return {@link com.bioqwer.serverApp.controller.Constraint#property}.
     */
    public String getProperty() {
        return property;
    }

    /**
     * Allow to set {@link com.bioqwer.serverApp.controller.Constraint#property}.
     * @param property new {@link com.bioqwer.serverApp.controller.Constraint#property} of {@link com.bioqwer.serverApp.controller.Constraint}.
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * Allow to get {@link com.bioqwer.serverApp.controller.Constraint#value} of {@link com.bioqwer.serverApp.controller.Constraint}.
     * @return {@link com.bioqwer.serverApp.controller.Constraint#value}.
     */
    public String getValue() {
        return value;
    }

    /**
     * Allow to set {@link com.bioqwer.serverApp.controller.Constraint#value}.
     * @param value new {@link com.bioqwer.serverApp.controller.Constraint#value} of {@link com.bioqwer.serverApp.controller.Constraint}.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Allow to get {@link com.bioqwer.serverApp.controller.Constraint#message} of {@link com.bioqwer.serverApp.controller.Constraint}.
     * @return {@link com.bioqwer.serverApp.controller.Constraint#message}.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Allow to set {@link com.bioqwer.serverApp.controller.Constraint#message}.
     * @param message new {@link com.bioqwer.serverApp.controller.Constraint#message} of {@link com.bioqwer.serverApp.controller.Constraint}.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
