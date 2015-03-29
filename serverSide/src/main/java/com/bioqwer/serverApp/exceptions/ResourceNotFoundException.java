package com.bioqwer.serverApp.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throw when {@link com.bioqwer.serverApp.model.User} or {@link com.bioqwer.serverApp.model.Note} not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final Logger logger = Logger.getLogger(ResourceNotFoundException.class);

    public ResourceNotFoundException() {
        logger.error("Call ResourceNotFoundException");
    }
}
