package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.repository.NoteRepository;
import com.bioqwer.serverApp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Antony on 24.10.2014.
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Qualifier("noteRepository")
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note addNote(Note note) {
        note.setCreationDate(new Timestamp(System.currentTimeMillis()));
        note.setLastChangeDate(new Timestamp(System.currentTimeMillis()));
        return noteRepository.saveAndFlush(note);
    }

    @Override
    public Note editNote(Note note) {
        note.setLastChangeDate(new Timestamp(System.currentTimeMillis()));
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

    @Override
    public Collection<Note> getAllUserNotes(long userId) {
        return noteRepository.findAll(userId);
    }

    @Override
    public Collection<Note> getNotesWhere(String partOfWord) {
        return noteRepository.findWhereParam("%" + partOfWord + "%");
    }
}
