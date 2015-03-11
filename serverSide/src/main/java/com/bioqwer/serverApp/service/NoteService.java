package com.bioqwer.serverApp.service;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 *  Interface for {@link com.bioqwer.serverApp.model.Note} logic.
 */
@Service
public interface NoteService {

    /**
     * Methods performs adding specified {@link com.bioqwer.serverApp.model.Note} to Storage.
     *
     * @param note instance of {@link com.bioqwer.serverApp.model.Note} to be added.
     * @return added {@link Note}.
     */
    Note addNote(Note note);

    /**
     * Methods performs editing specified {@link com.bioqwer.serverApp.model.Note} to Storage.
     *
     * @param note instance of {@link com.bioqwer.serverApp.model.Note} to be edit.
     * @return edit {@link com.bioqwer.serverApp.model.Note}
     */
    Note editNote(Note note);

    /**
     * Methods performs delete specified {@link com.bioqwer.serverApp.model.Note} by id field from Storage.
     *
     * @param deletedNote field of {@link com.bioqwer.serverApp.model.Note}
     */
    void deleteNote(Note deletedNote);

    /**
     * Methods allow get {@link com.bioqwer.serverApp.model.Note} by id field from Storage.
     * @param id field of {@link com.bioqwer.serverApp.model.Note}
     */
    Note getById(long id);

    /**
     * Methods allow to search {@link com.bioqwer.serverApp.model.Note} by part of  head value from Storage.
     * @param partOfHead search parameter
     * @param userId of {@link com.bioqwer.serverApp.model.User}
     * @return Collection of found {@link com.bioqwer.serverApp.model.Note}
     */
    Collection<Note> searchByHead(String partOfHead, long userId);

    /**
     * Methods allow to search {@link com.bioqwer.serverApp.model.Note} by part of  body value from Storage.
     * @param partOfBody search parameter
     * @param userId of {@link com.bioqwer.serverApp.model.User}
     * @return Collection of found {@link com.bioqwer.serverApp.model.Note}
     */
    Collection<Note> searchByBody(String partOfBody, long userId);

    /**
     * Methods performs get owner {@link com.bioqwer.serverApp.model.User} of  {@link com.bioqwer.serverApp.model.Note} from Storage.
     * @param noteId of {@link com.bioqwer.serverApp.model.Note}.
     * @return {@link com.bioqwer.serverApp.model.Note} those create {@link com.bioqwer.serverApp.model.User}.
     */
    User getUser(long noteId);

    /**
     * Methods performs get all {@link com.bioqwer.serverApp.model.Note} of  {@link com.bioqwer.serverApp.model.User} from Storage. 
     * @param userId of {@link com.bioqwer.serverApp.model.User}
     * @return Collection of {@link com.bioqwer.serverApp.model.Note}
     */
    Collection<Note> getAllUserNotes(long userId);

    /**
     * Methods performs search by head and body of {@link com.bioqwer.serverApp.model.Note} created by {@link com.bioqwer.serverApp.model.User} from Storage.
     * @param partOfWord search parameter.
     * @param userId of owner {@link com.bioqwer.serverApp.model.User}.
     * @return Collection of founded {@link com.bioqwer.serverApp.model.Note} by parameter.
     */
    Collection<Note> searchInAllParamsOfNotes(String partOfWord, long userId);
}
