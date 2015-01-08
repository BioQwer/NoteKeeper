package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    public User getCurrentUser(Principal principal) {
        System.out.println("principal.getName() = " + principal.getName());
        System.out.println(" userService.getByLogin(principal.getName()) = " + userService.getByLogin(principal.getName()));
        return userService.getByLogin(principal.getName());
    }

    public String getCurrentUserLogin(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getMe(Principal principal) {
        return getCurrentUser(principal);
    }

    @RequestMapping(value = "/singIn", method = RequestMethod.POST)
    @ResponseBody
    public Object singInUser(@RequestBody User user, HttpServletResponse response) {
        response.setStatus(400);
        if (userService.getByEmail(user.getEmail()) != null) {
            return new Constraint("email", user.getEmail(), "User with email " + user.getEmail() + " is exist");
        } else if (userService.getByLogin(user.getLogin()) != null) {
            return new Constraint("login", user.getLogin(), "User with login " + user.getLogin() + " is exist");
        }
        try {
            response.setStatus(200);
            return userService.addUser(user);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            response.setStatus(400);
            return getInCorrectValues(e);
        }
    }

    /**
     * Edit user method
     * !! if Change user.login you must to re singIn on client !!
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object editUser(Principal principal, @RequestBody User user, HttpServletResponse response) {
        response.setStatus(400);
        // Check user for situation when login or email is busy
        User checker = userService.getByEmail(user.getEmail());
        if ((checker != null) && (checker.getUserId() != user.getUserId())) {
            return new Constraint("email", user.getEmail(), "Email " + user.getEmail() + " is busy");
        } else {
            checker = userService.getByLogin(user.getLogin());
            if ((checker != null) && (checker.getUserId() != user.getUserId())) {
                return new Constraint("login", user.getLogin(), "Login " + user.getLogin() + " is busy");
            }
        }
        //try to edit user
        try {
            response.setStatus(200);
            if (user.getUserId() == getCurrentUser(principal).getUserId())
                return userService.editUser(user);
            else
                throw new BadRequestException();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            response.setStatus(400);
            return getInCorrectValues(e);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteUser(Principal principal, @RequestBody User user, HttpServletResponse response) throws IOException {
        if (user.getUserId() == getCurrentUser(principal).getUserId()) {
            userService.delete(user.getUserId());
            response.sendRedirect("../logout");
        } else
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
        if (!note.getUserByUserId().getLogin().equals(getCurrentUserLogin(principal)))
            throw new BadRequestException();
        else
            return noteService.editNote(note);
    }

    @RequestMapping(value = "/note", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteNote(Principal principal, @RequestBody Note note) {
        if (!note.getUserByUserId().getLogin().equals(getCurrentUserLogin(principal)))
            throw new BadRequestException();
        else
            noteService.deleteNote(note.getNoteId());
    }

    @RequestMapping(value = "/newNote", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Note createNote(Principal principal, @RequestBody Note note) {
        if (!note.getUserByUserId().getLogin().equals(getCurrentUserLogin(principal)))
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

    private List getInCorrectValues(ConstraintViolationException exception) {
        List list = new ArrayList();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            Constraint constraint = new Constraint(cv.getPropertyPath().toString(), cv.getPropertyPath().toString(), cv.getMessage());
            list.add(constraint);
        }
        return list;
    }
}
