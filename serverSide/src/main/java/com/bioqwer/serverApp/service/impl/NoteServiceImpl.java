package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.repository.NoteRepository;
import com.bioqwer.serverApp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Antony on 24.10.2014.
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note addNote(Note note) {
        return null;
    }

    @Override
    public Note editNote(Note note) {
        return null;
    }

    @Override
    public void deleteNote(long id) {

    }

    @Override
    public Note getById(long id) {
        return null;
    }

    @Override
    public Note getByHead(String head) {
        return null;
    }

    @Override
    public Note getByBody(String body) {
        return null;
    }

    @Override
    public User getByUser(Note note) {
        return null;
    }
}
