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
    private String param;

    public User getCurrentUser(Principal principal) {
        User user = userService.getByLogin(principal.getName());
        return user;
    }

    public String getCurrentUserLogin(Principal principal) {
        return principal.getName();
    }

    @RequestMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getMe(Principal principal) {
        return getCurrentUser(principal);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User editUser(Principal principal, @RequestBody User user) {
        if (user.getUserId() == getCurrentUser(principal).getUserId())
            return userService.editUser(user);
        else
            throw new BadRequestException();
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Note> getNotes(Principal principal) {
        return noteService.getAllUserNotes(getCurrentUser(principal).getUserId());
    }

    @RequestMapping(value = "/search/{searchValue}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Note> searchNotes(Principal principal, @PathVariable("searchValue") String value) {
        return noteService.searchInAllParamsOfNotes(value, getCurrentUser(principal).getUserId());
    }

    @RequestMapping(value = "/note/{noteId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Note getNote(Principal principal, @PathVariable("noteId") long noteId) {
        Note resNote = noteService.getById(noteId);
        if (resNote == null)
            throw new ResourceNotFoundException();
        else if (!getCurrentUserLogin(principal).equals(resNote.getUserByUserId().getLogin()))
            throw new BadRequestException();
        else
            return resNote;
    }

    @RequestMapping(value = "/note", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Note editNote(Principal principal, @RequestBody Note note) {
        if (note.getUserByUserId().getLogin() != getCurrentUserLogin(principal))
            throw new BadRequestException();
        else
            return noteService.editNote(note);
    }

    @RequestMapping(value = "/newNote", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Note createNote(Principal principal, @RequestBody Note note) {
        if (note.getUserByUserId().getLogin() != getCurrentUserLogin(principal))
            throw new BadRequestException();
        else
            return noteService.addNote(note);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public class BadRequestException extends RuntimeException {
        //
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        //
    }
}
