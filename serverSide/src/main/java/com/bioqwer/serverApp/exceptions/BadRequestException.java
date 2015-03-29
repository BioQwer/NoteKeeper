package com.bioqwer.serverApp.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throw when {@link com.bioqwer.serverApp.model.User} Unauthorized or Access denied.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final Logger logger = Logger.getLogger(BadRequestException.class);

    public BadRequestException() {
        logger.error("Call BadRequestException");
    }
}
