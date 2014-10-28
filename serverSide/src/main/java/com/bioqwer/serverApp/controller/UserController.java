package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserService userService;
    private final NoteService noteService;

    @Autowired
    public UserController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getMyData() {
        return "Это ответ метода GET!";
    }


}
