package com.bioqwer.serverApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Antony on 28.12.2014.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String redirect() {
        return "redirect:index.html";
    }

}
