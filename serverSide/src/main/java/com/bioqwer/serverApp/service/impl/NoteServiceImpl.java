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
 * {@link com.bioqwer.serverApp.service.impl.NoteServiceImpl} implements of {@link com.bioqwer.serverApp.service.NoteService} provide methods for using DataBase.
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Qualifier("noteRepository")
    @Autowired
    private NoteRepository noteRepository;

    /**
     * Methods performs adding specified {@link com.bioqwer.serverApp.model.Note} to DataBase.
     *
     * @param note instance of {@link com.bioqwer.serverApp.model.Note} to be added.
     * @return added {@link Note}.
     */
    @Override
    public Note addNote(Note note) {
        note.setCreationDate(new Timestamp(System.currentTimeMillis()));
        note.setLastChangeDate(new Timestamp(System.currentTimeMillis()));
        return noteRepository.saveAndFlush(note);
    }

    /**
     * Methods performs editing specified {@link com.bioqwer.serverApp.model.Note} to DataBase.
     * @param note instance of {@link com.bioqwer.serverApp.model.Note} to be edit.
     * @return edit {@link com.bioqwer.serverApp.model.Note}
     */
    @Override
    public Note editNote(Note note) {
        note.setLastChangeDate(new Timestamp(System.currentTimeMillis()));
        return noteRepository.saveAndFlush(note);
    }

    /**
     * Methods performs delete specified {@link com.bioqwer.serverApp.model.Note} by id field from DataBase.
     * @param id field of {@link com.bioqwer.serverApp.model.Note}
     */
    @Override
    public void deleteNote(long id) {
        noteRepository.delete(id);
    }

    /**
     * Methods allow get {@link com.bioqwer.serverApp.model.Note} by id field from DataBase.
     * @param id field of {@link com.bioqwer.serverApp.model.Note}
     */
    @Override
    public Note getById(long id) {
        return noteRepository.findOne(id);
    }

    /**
     * Methods allow to search {@link com.bioqwer.serverApp.model.Note} by part of  head value from DataBase.
     * @param partOfHead search parameter
     * @param userId of {@link com.bioqwer.serverApp.model.User}
     * @return Collection of found {@link com.bioqwer.serverApp.model.Note}
     */
    @Override
    public Collection<Note> searchByHead(String partOfHead, long userId) {
        return noteRepository.findByHead("%" + partOfHead + "%", userId);
    }

    /**
     * Methods allow to search {@link com.bioqwer.serverApp.model.Note} by part of  body value from DataBase.
     * @param partOfBody search parameter
     * @param userId of {@link com.bioqwer.serverApp.model.User}
     * @return Collection of found {@link com.bioqwer.serverApp.model.Note}
     */
    @Override
    public Collection<Note> searchByBody(String partOfBody, long userId) {
        return noteRepository.findByBody("%" + partOfBody + "%", userId);
    }

    /**
     * Methods performs get owner {@link com.bioqwer.serverApp.model.User} of  {@link com.bioqwer.serverApp.model.Note} from DataBase.
     * @param noteId of {@link com.bioqwer.serverApp.model.Note}
     * @return {@link com.bioqwer.serverApp.model.User} those create {@link com.bioqwer.serverApp.model.Note} 
     */
    @Override
    public User getUser(long noteId) {
        return noteRepository.findOne(noteId).getUserByUserId();
    }

    /**
     * Methods performs get all {@link com.bioqwer.serverApp.model.Note} of  {@link com.bioqwer.serverApp.model.User} from DataBase. 
     * @param userId of {@link com.bioqwer.serverApp.model.User}
     * @return Collection of {@link com.bioqwer.serverApp.model.Note}
     */
    @Override
    public Collection<Note> getAllUserNotes(long userId) {
        return noteRepository.findAll(userId);
    }

    /**
     * Methods performs search by head and body of {@link com.bioqwer.serverApp.model.Note} created by {@link com.bioqwer.serverApp.model.User} from DataBase.
     * @param partOfWord search parameter
     * @param userId of owner {@link com.bioqwer.serverApp.model.User}
     * @return Collection of founded {@link com.bioqwer.serverApp.model.Note} by parameter
     */
    @Override
    public Collection<Note> searchInAllParamsOfNotes(String partOfWord, long userId) {
        return noteRepository.findWhereParam("%" + partOfWord + "%", userId);
    }
}
