package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Antony on 07.11.2014.
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    @Qualifier("noteServiceImpl")
    @Autowired
    private NoteService noteService;

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    Note editNote(@RequestBody Note note) {
        return noteService.editNote(note);
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
    @ResponseBody
    public Note getNote(@PathVariable long noteId) {
        return noteService.getById(noteId);
    }
}
