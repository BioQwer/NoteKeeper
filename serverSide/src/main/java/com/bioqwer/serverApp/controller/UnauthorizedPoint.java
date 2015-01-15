package com.bioqwer.serverApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Provide UNAUTHORIZED point of application.
 */
@Controller
public class UnauthorizedPoint {

    /**
     * Allow redirect to /notLogin and set {@link org.springframework.http.HttpStatus#UNAUTHORIZED}.
     */
    @RequestMapping("/notLogin")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void notLogin() {

    }
}
