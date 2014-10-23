package com.bioqwer.serverApp.exception;

/**
 * Created by Antony on 24.10.2014.
 */
public class UserNotFoundException extends Throwable {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
