package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Contains {@link com.bioqwer.serverApp.model.Note}s Query for Storage.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Methods provide SQL Query for searching {@link com.bioqwer.serverApp.model.Note}s in Storage by part of {@link com.bioqwer.serverApp.model.Note#head}.
     *
     * @param partOfHead search parameter
     * @param userId     of {@link com.bioqwer.serverApp.model.User}.
     * @return Collection of found {@link com.bioqwer.serverApp.model.Note}s.
     */
    @Query("select a from Note a fetch all properties where userByUserId.userId = :userId and head like :partOfHead")
    Collection<Note> findByHead(@Param("partOfHead") String partOfHead, @Param("userId") long userId);

    /**
     * Methods provide SQL Query for searching {@link com.bioqwer.serverApp.model.Note}s in Storage by part of {@link com.bioqwer.serverApp.model.Note#body}.
     *
     * @param partOfBody search parameter
     * @param userId     of {@link com.bioqwer.serverApp.model.User}.
     * @return Collection of found {@link com.bioqwer.serverApp.model.Note}s.
     */
    @Query("select a from Note a fetch all properties where userByUserId.userId = :userId and body like :partOfBody")
    Collection<Note> findByBody(@Param("partOfBody") String partOfBody, @Param("userId") long userId);

    /**
     * Methods provide SQL Query for get all {@link com.bioqwer.serverApp.model.Note}s from Storage .
     *
     * @param userId of owner {@link com.bioqwer.serverApp.model.User}.
     * @return Collection of {@link com.bioqwer.serverApp.model.Note}s where owner {@link com.bioqwer.serverApp.model.User}.
     */
    @Query("select a from Note a fetch all properties where userByUserId.userId = :userId")
    Collection<Note> findAll(@Param("userId") long userId);

    /**
     * Methods provide SQL Query for searching {@link com.bioqwer.serverApp.model.Note}s in Storage by part of {@link com.bioqwer.serverApp.model.Note#body} and part of {@link com.bioqwer.serverApp.model.Note#head}.
     * Case not sensitive.
     *
     * @param partOfWord search parameter.
     * @param userId     of owner {@link com.bioqwer.serverApp.model.User#userId}.
     * @return Collection of found {@link com.bioqwer.serverApp.model.Note}s.
     */
    @Query("select a from Note a fetch all properties where userByUserId.userId = :userId and (head like :partOfWord or body like :partOfWord)")
    Collection<Note> findWhereParam(@Param("partOfWord") String partOfWord, @Param("userId") long userId);
}
