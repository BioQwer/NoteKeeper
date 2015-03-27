package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.exceptions.BadRequestException;
import com.bioqwer.serverApp.exceptions.ResourceNotFoundException;
import com.bioqwer.serverApp.model.Monitoring;
import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.MonitoringService;
import com.bioqwer.serverApp.service.NoteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

/**
 * Created by Antony on 23.03.2015.
 */
@Controller
@RequestMapping(value = "/monitoring")
public class MonitoringController {

    private static final Logger logger = Logger.getLogger(MonitoringController.class);

    @Qualifier("monitoringServiceImpl")
    @Autowired
    private MonitoringService monitoringService;
    @Qualifier("noteServiceImpl")
    @Autowired
    private NoteService noteService;
    @Qualifier("userController")
    @Autowired
    private UserController userController;

    @RequestMapping(value = "/{searchValue}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Monitoring> getNoteMonitoringById(Principal principal,@PathVariable("searchValue") Long value){
        Note note = noteService.getById(value);
        if(note==null)
            throw new ResourceNotFoundException();
        Collection<Monitoring> res = null;
        if(userController.getCurrentUserLogin(principal).equals(note.getUserByUserId().getLogin())) {
            res = monitoringService.getUserActionOnNote(note);
        }
        else
            throw new BadRequestException();
        logger.debug("Call getNoteMonitoringById = "+ res);
        return res;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Monitoring> getMonitoringForUser(Principal principal)
    {
        User user = userController.getCurrentUser(principal);
        System.out.println("user = " + user);
        Collection<Monitoring> result = monitoringService.getUserAction(user);
        logger.debug("Call getMonitoringForUser = " +result);
        return monitoringService.getUserAction(user);
    }
}
