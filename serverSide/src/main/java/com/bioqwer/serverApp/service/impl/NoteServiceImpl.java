package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.repository.NoteRepository;
import com.bioqwer.serverApp.service.MonitoringService;
import com.bioqwer.serverApp.service.NoteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Provide implementation of {@link com.bioqwer.serverApp.service.NoteService} logic.
 */
@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger logger = Logger.getLogger(NoteServiceImpl.class);

    @Qualifier("noteRepository")
    @Autowired
    private NoteRepository noteRepository;

    @Qualifier("monitoringServiceImpl")
    @Autowired
    private MonitoringService monitoringService;

    @Override
    public Note addNote(Note note) {
        note.setCreationDate(new Timestamp(System.currentTimeMillis()));
        note.setLastChangeDate(new Timestamp(System.currentTimeMillis()));
        Note result = noteRepository.saveAndFlush(note);
        logger.debug("Success add " + result);
        monitoringService.addNoteMonitoring(result);
        return result;
    }

    @Override
    public Note editNote(Note note) {
        Note before = noteRepository.findOne(note.getNoteId());
        if (note.equals(before)) {
            logger.debug("Don't need persist  editNote " + note);
            return note;
        } else {
            Note result = noteRepository.saveAndFlush(note);
            logger.debug("Success edit " + result);
            monitoringService.addNoteMonitoring(result);
            return result;
        }
    }

    @Override
    public void deleteNote(Note deletedNote) {
        noteRepository.delete(deletedNote);
        logger.debug("Success delete " + deletedNote);
    }

    @Override
    public Note getById(long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public Collection<Note> searchByHead(String partOfHead, long userId) {
        Collection<Note> result = noteRepository.findByHead("%" + partOfHead + "%", userId);
        logger.debug("Success searchByHead Find by partOfHead " + partOfHead + " for userID" + userId + " is " + result);
        return result;
    }

    @Override
    public Collection<Note> searchByBody(String partOfBody, long userId) {
        Collection<Note> result = noteRepository.findByBody("%" + partOfBody + "%", userId);
        logger.debug("Success searchByHead Find by partOfBody " + partOfBody + " for userID" + userId + " is " + result);
        return result;
    }

    @Override
    public User getUser(long noteId) {
        User result = noteRepository.findOne(noteId).getUserByUserId();
        logger.debug("Call getUser " + result);
        return result;
    }

    @Override
    public Collection<Note> getAllUserNotes(long userId) {
        Collection<Note> result = noteRepository.findAll(userId);
        logger.debug("Success getAllUserNotes for userID" + userId + " is " + result);
        return result;
    }

    @Override
    public Collection<Note> searchInAllParamsOfNotes(String partOfWord, long userId) {
        Collection<Note> result = noteRepository.findWhereParam("%" + partOfWord + "%", userId);
        logger.debug("Success searchInAllParamsOfNotes Find by partOfWord " + partOfWord + " for userID" + userId + " is " + result);
        return result;
    }
}
