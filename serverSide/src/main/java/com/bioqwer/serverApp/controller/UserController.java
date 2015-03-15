package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Provide REST Service of Application.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Qualifier("noteServiceImpl")
    @Autowired
    private NoteService noteService;

    /**
     * Allow to get {@link com.bioqwer.serverApp.model.User} which caused the request.
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User} which caused the request.
     */
    public User getCurrentUser(Principal principal) {
        logger.debug("getCurrentUser for" + principal.getName());
        return userService.getByLogin(principal.getName());
    }

    /**
     * Allow to get {@link com.bioqwer.serverApp.model.User#login} which caused the request.
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User#login} which caused the request.
     */
    public String getCurrentUserLogin(Principal principal) {
        logger.debug("getCurrentUserLogin for" + principal.getName());
        return principal.getName();
    }

    /**
     * REST method.<p>
     * Allow to get {@link com.bioqwer.serverApp.model.User} which caused the request.
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User} which caused the request.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getMe(Principal principal) {
        logger.debug("getMe for " + principal.getName());
        return getCurrentUser(principal);
    }

    /**
     * SingIn REST method.<p>
     * Call {@link com.bioqwer.serverApp.service.UserService#addUser(com.bioqwer.serverApp.model.User)}<p>
     * If all {@link com.bioqwer.serverApp.model.User} fields valid by patterns.
     *
     * @param user     new {@link com.bioqwer.serverApp.model.User} data.
     * @param response used for set {@link org.springframework.http.HttpStatus}. For not valid data 400.
     * @return {@link com.bioqwer.serverApp.model.User} if fields valid by patterns.<p>
     * {@link java.util.Collection} of {@link com.bioqwer.serverApp.controller.Constraint} if not valid request.
     */
    @RequestMapping(value = "/singIn", method = RequestMethod.POST)
    @ResponseBody
    public Object singInUser(@RequestBody User user, HttpServletResponse response) {
        response.setStatus(400);
        if (userService.getByEmail(user.getEmail()) != null) {
            Constraint constraint = new Constraint("email", user.getEmail(), "User with email " + user.getEmail() + " is exist");
            logger.info("singInUser " + user + " with " + constraint);
            return constraint;
        } else if (userService.getByLogin(user.getLogin()) != null) {
            Constraint constraint = new Constraint("login", user.getLogin(), "User with login " + user.getLogin() + " is exist");
            logger.info("singInUser " + user + " with " + constraint);
            return constraint;
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
     * Edit User REST method.<p>
     * Call {@link com.bioqwer.serverApp.service.UserService#editUser(com.bioqwer.serverApp.model.User)}<p>
     * If all {@link com.bioqwer.serverApp.model.User} fields valid by patterns.
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @param user      edited {@link com.bioqwer.serverApp.model.User} data.
     * @param response  used for set {@link org.springframework.http.HttpStatus}. For not valid data 400.
     * @return {@link com.bioqwer.serverApp.model.User} if fields valid by patterns.<p>
     * {@link java.util.Collection} of {@link com.bioqwer.serverApp.controller.Constraint} if not valid request.
     * @throws com.bioqwer.serverApp.controller.UserController.BadRequestException
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object editUser(Principal principal, @RequestBody User user, HttpServletResponse response) {
        response.setStatus(400);
        // Check user for situation when login or email is busy
        User checker = userService.getByEmail(user.getEmail());
        if ((checker != null) && (checker.getUserId() != user.getUserId())) {
            Constraint constraint = new Constraint("email", user.getEmail(), "Email " + user.getEmail() + " is busy");
            logger.info("singInUser " + user + " with " + constraint);
            return constraint;
        } else {
            checker = userService.getByLogin(user.getLogin());
            if ((checker != null) && (checker.getUserId() != user.getUserId())) {
                Constraint constraint = new Constraint("login", user.getLogin(), "Login " + user.getLogin() + " is busy");
                logger.info("singInUser " + user + " with " + constraint);
                return constraint;
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

    /**
     * Delete User REST method.<p>
     * Call {@link com.bioqwer.serverApp.service.UserService#editUser(com.bioqwer.serverApp.model.User)}<p>
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @param user      Delete {@link com.bioqwer.serverApp.model.User} data.
     * @param response  used for set {@link org.springframework.http.HttpStatus}. For not valid data 400.
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteUser(Principal principal, @RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        if (user.getUserId() == getCurrentUser(principal).getUserId()) {
            logger.info("Call deleteUser");
            userService.delete(user);
            request.logout();
        } else
            throw new BadRequestException();
    }

    /**
     * Get All User Notes REST method.<p>
     * Call {@link com.bioqwer.serverApp.service.NoteService#getAllUserNotes(long)}.
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @return {@link java.util.Collection} of {@link com.bioqwer.serverApp.model.Note}s there owner logged {@link com.bioqwer.serverApp.model.User}.
     */
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Note> getNotes(Principal principal) {
        logger.info("Call getNotes");
        return noteService.getAllUserNotes(getCurrentUser(principal).getUserId());
    }

    /**
     * Search by head and body Notes REST method.<p>
     * Methods performs search by head and body of {@link com.bioqwer.serverApp.model.Note}.
     * Call {@link com.bioqwer.serverApp.service.NoteService#searchInAllParamsOfNotes(String, long)}<p>
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @param value     search variable.
     * @return Collection of founded {@link com.bioqwer.serverApp.model.Note}s by parameter.
     */
    @RequestMapping(value = "/search/{searchValue}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Note> searchNotes(Principal principal, @PathVariable("searchValue") String value) {
        logger.info("Call searchNotes");
        return noteService.searchInAllParamsOfNotes(value, getCurrentUser(principal).getUserId());
    }

    /**
     * Get {@link com.bioqwer.serverApp.model.Note} by {@link com.bioqwer.serverApp.model.Note#noteId} REST method.<p>
     * Call {@link com.bioqwer.serverApp.service.NoteService#getById(long)}<p>
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @param noteId    of {@link com.bioqwer.serverApp.model.Note}.
     * @return {@link com.bioqwer.serverApp.model.Note} those create {@link com.bioqwer.serverApp.model.User}.
     */
    @RequestMapping(value = "/note/{noteId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Note getNote(Principal principal, @PathVariable("noteId") long noteId) {
        logger.info("Call getNote");
        Note resNote = noteService.getById(noteId);
        if (resNote == null)
            throw new ResourceNotFoundException();
        else if (!getCurrentUserLogin(principal).equals(resNote.getUserByUserId().getLogin()))
            throw new BadRequestException();
        else
            return resNote;
    }

    /**
     * Edit {@link com.bioqwer.serverApp.model.Note} REST method.<p>
     * Call {@link com.bioqwer.serverApp.service.NoteService#editNote(com.bioqwer.serverApp.model.Note)}<p>
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @param note      edited {@link com.bioqwer.serverApp.model.Note}.
     * @return edited {@link com.bioqwer.serverApp.model.Note}.
     */
    @RequestMapping(value = "/note", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Note editNote(Principal principal, @RequestBody Note note) {
        logger.info("Call editNote");
        if (!note.getUserByUserId().getLogin().equals(getCurrentUserLogin(principal)))
            throw new BadRequestException();
        else
            return noteService.editNote(note);
    }

    /**
     * Delete {@link com.bioqwer.serverApp.model.Note} REST method.<p>
     * Call {@link com.bioqwer.serverApp.service.NoteService#deleteNote(com.bioqwer.serverApp.model.Note)}<p>
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @param note      delete {@link com.bioqwer.serverApp.model.Note} data.
     */
    @RequestMapping(value = "/note", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteNote(Principal principal, @RequestBody Note note) {
        logger.info("Call deleteNote");
        if (!note.getUserByUserId().getLogin().equals(getCurrentUserLogin(principal)))
            throw new BadRequestException();
        else
            noteService.deleteNote(note);
    }

    /**
     * Create {@link com.bioqwer.serverApp.model.Note} REST method.<p>
     * Call {@link com.bioqwer.serverApp.service.NoteService#addNote(com.bioqwer.serverApp.model.Note)}<p>
     *
     * @param principal security variable {@link java.security.Principal} which contain {@link com.bioqwer.serverApp.model.User#login} of logged {@link com.bioqwer.serverApp.model.User}.
     * @param note      created {@link com.bioqwer.serverApp.model.Note} data.
     * @return created {@link com.bioqwer.serverApp.model.Note}.
     */
    @RequestMapping(value = "/newNote", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Note createNote(Principal principal, @RequestBody Note note) {
        logger.info("Call createNote");
        if (!note.getUserByUserId().getLogin().equals(getCurrentUserLogin(principal)))
            throw new BadRequestException();
        else
            return noteService.addNote(note);
    }

    /**
     * Methods performs parse {@link javax.validation.ConstraintViolationException}. Uses for response in friendly format.
     *
     * @param exception thrown {@link javax.validation.ConstraintViolationException}.
     * @return {@link java.util.List} of {@link com.bioqwer.serverApp.controller.Constraint}.
     */
    private List getInCorrectValues(ConstraintViolationException exception) {
        List list = new ArrayList();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            Constraint constraint = new Constraint(cv.getPropertyPath().toString(), cv.getPropertyPath().toString(), cv.getMessage());
            list.add(constraint);
        }
        logger.info("Call getInCorrectValues " + list);
        return list;
    }

    /**
     * Throw when {@link com.bioqwer.serverApp.model.User} Unauthorized or Access denied.
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public class BadRequestException extends RuntimeException {
        public BadRequestException() {
            logger.info("Call BadRequestException");
        }
    }

    /**
     * Throw when {@link com.bioqwer.serverApp.model.User} or {@link com.bioqwer.serverApp.model.Note} not found.
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException() {
            logger.info("Call BadRequestException");
        }
    }
}
