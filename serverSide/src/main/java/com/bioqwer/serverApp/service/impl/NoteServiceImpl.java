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
    public Collection<Note> searchByHead(String partOfHead, long userId) {
        return noteRepository.findByHead("%" + partOfHead + "%", userId);
    }

    @Override
    public Collection<Note> searchByBody(String partOfBody, long userId) {
        return noteRepository.findByBody("%" + partOfBody + "%", userId);
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
    public Collection<Note> searchInAllParamsOfNotes(String partOfWord, long userId) {
        return noteRepository.findWhereParam("%" + partOfWord + "%", userId);
    }
}
