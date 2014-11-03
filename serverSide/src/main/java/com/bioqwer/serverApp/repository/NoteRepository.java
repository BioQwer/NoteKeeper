package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Contains Notes @Query for DB
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select a from Note a fetch all properties where userByUserId.userId = :userId and head like :partOfHead")
    Collection<Note> findByHead(@Param("partOfHead") String partOfHead, @Param("userId") long userId);

    @Query("select a from Note a fetch all properties where userByUserId.userId = :userId and body like :partOfBody")
    Collection<Note> findByBody(@Param("partOfBody") String body, @Param("userId") long userId);

    @Query("select a from Note a fetch all properties where userByUserId.userId = :userId")
    Collection<Note> findAll(@Param("userId") long userId);

    @Query("select a from Note a fetch all properties where userByUserId.userId = :userId and (head like :partOfWord or body like :partOfWord)")
    Collection<Note> findWhereParam(@Param("partOfWord") String partOfWord, @Param("userId") long userId);
}
