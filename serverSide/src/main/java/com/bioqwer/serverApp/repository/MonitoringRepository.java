package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.Monitoring;
import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Antony on 04.03.2015.
 * Contains {@link com.bioqwer.serverApp.model.Monitoring}s Query for Storage.
 */
@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {

    @Query("select a from Monitoring a where a.userByUserId = :user and a.noteByNoteId is null order by a.logData desc")
    Collection<Monitoring> getUserAction(@Param("user") User user);

    @Query("select a from Monitoring a where a.noteByNoteId = :note order by a.logData desc")
    Collection<Monitoring> getUserActionOnNote(@Param("note") Note note);
}
