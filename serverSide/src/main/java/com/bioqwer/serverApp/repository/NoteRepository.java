package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Antony on 18.10.2014.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select b from Note b where b.head = :head")
    Note findByHead(@Param("head") String head);

    @Query("select b from Note b where b.body = :body")
    Note findByBody(@Param("body") String body);
}
