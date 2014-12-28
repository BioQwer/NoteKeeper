package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

/**
 * Created by Antony on 26.10.2014.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Qualifier("noteServiceImpl")
    @Autowired
    private NoteService noteService;

    @RequestMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getMe(Principal principal) {
        User user = userService.getByLogin(principal.getName());
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public User editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<User> getMyUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable long userId) {
        User result = userService.getById(userId);
        return result;
    }

    @RequestMapping(value = "/{userId}/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Note> getNotes(@PathVariable long userId) {
        return noteService.getAllUserNotes(userId);
    }

}
