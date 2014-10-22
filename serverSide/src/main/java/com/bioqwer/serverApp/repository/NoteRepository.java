package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Antony on 18.10.2014.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

}
