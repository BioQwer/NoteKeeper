package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by Antony on 26.10.2014.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired(required = true)
    private UserService userService;
    @Autowired(required = true)
    private NoteService noteService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<User> getMyUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable long userId) {
        return userService.getById(userId);
    }

    @RequestMapping(value = "/{userId}/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Note> getNotes(@PathVariable long userId) {
        return noteService.getAllUserNotes(userId);
    }

}
