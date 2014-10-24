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
        return noteRepository.saveAndFlush(note);
    }

    @Override
    public Note editNote(Note note) {
        return noteRepository.saveAndFlush(note);
    }

    @Override
    public void deleteNote(long id) {
        noteRepository.delete(id);
    }

    @Override
    public Note getById(long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public Note getByHead(String head) {
        return noteRepository.findByHead(head);
    }

    @Override
    public Note getByBody(String body) {
        return noteRepository.findByBody(body);
    }

    @Override
    public User getUser(long noteId) {
        return noteRepository.findOne(noteId).getUserByUserId();
    }
}
