package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Antony on 18.10.2014.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select b from Note b where b.head = :head")
    Note findByHead(@Param("head") String head);

    @Query("select b from Note b where b.body = :body")
    Note findByBody(@Param("body") String body);

    @Query("select b from Note b where b.userByUserId = :userId")
    Collection<Note> findAll(@Param("userId") long userId);

    @Query("select b from Note b where b.head like :partOfWord or b.body like :partOfWord")
    Collection<Note> findWhereParam(@Param("partOfWord") String partOfWord);
}
