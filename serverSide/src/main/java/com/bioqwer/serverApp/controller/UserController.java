package com.bioqwer.serverApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Antony on 26.10.2014.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getMyData() {
        return "Это ответ метода GET!";
    }


}
