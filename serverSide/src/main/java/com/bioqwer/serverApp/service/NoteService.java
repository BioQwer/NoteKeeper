package com.bioqwer.serverApp.service;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Antony on 18.10.2014.
 */
@Service
public interface NoteService {

    Note addNote(Note note);

    Note editNote(Note note);

    void deleteNote(long id);

    Note getById(long id);

    Collection<Note> searchByHead(String partOfHead, long userId);

    Collection<Note> searchByBody(String partOfBody, long userId);

    User getUser(long noteId);

    Collection<Note> getAllUserNotes(long userId);

    Collection<Note> searchInAllParamsOfNotes(String partOfWord, long userId);
}
