package com.bioqwer.serverApp.service;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;

/**
 * Created by Antony on 18.10.2014.
 */
public interface NoteService {

    Note addNote(Note note);

    Note editNote(Note note);

    void deleteNote(long id);

    Note getById(long id);

    Note getByHead(String head);

    Note getByBody(String body);

    User getByUser(Note note);

}
