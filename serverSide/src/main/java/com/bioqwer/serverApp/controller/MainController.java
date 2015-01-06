package com.bioqwer.serverApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Antony on 28.12.2014.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String redirect() {
        return "redirect:index.html";
    }

    @RequestMapping("/notLogin")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void notLogin() {

    }
}
