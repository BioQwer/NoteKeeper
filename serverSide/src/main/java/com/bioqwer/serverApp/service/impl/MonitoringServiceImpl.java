package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.model.Monitoring;
import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.repository.MonitoringRepository;
import com.bioqwer.serverApp.service.MonitoringService;
import com.bioqwer.serverApp.service.NoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Antony on 04.03.2015.
 * Provide Implementation for {@link com.bioqwer.serverApp.service.MonitoringService}
 */
@Service
public class MonitoringServiceImpl implements MonitoringService {

    @Qualifier("monitoringRepository")
    @Autowired
    private MonitoringRepository monitoringRepository;

    @Qualifier("noteServiceImpl")
    @Autowired
    private NoteService noteService;


    @Override
    public Collection<Monitoring> getUserAction(User user) {
        return monitoringRepository.getUserAction(user);
    }

    @Override
    public Collection<Monitoring> getUserActionOnNote(Note note) {
        return monitoringRepository.getUserActionOnNote(note);
    }

    @Override
    public Monitoring addUserMonitoring(User user) {
        Monitoring monitoring = new Monitoring();
        monitoring.setNoteByNoteId(null);
        monitoring.setLogTime(new Timestamp(System.currentTimeMillis()));
        monitoring.setUserByUserId(user);
        try {
            monitoring.setLogData(new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            monitoring.setLogData(e.getOriginalMessage());
        }
        monitoringRepository.save(monitoring);
        return monitoring;
    }

    @Override
    public Monitoring addNoteMonitoring(Note note) {
        Monitoring monitoring = new Monitoring();
        monitoring.setNoteByNoteId(note);
        monitoring.setLogTime(note.getLastChangeDate());
        monitoring.setUserByUserId(note.getUserByUserId());
        try {
            monitoring.setLogData(new ObjectMapper().writeValueAsString(note));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            monitoring.setLogData(e.getOriginalMessage());
        }
        monitoringRepository.save(monitoring);
        return monitoring;
    }

    @Override
    public Note revertNoteFromMonitoring(Monitoring monitoring) {
        Note note = null;
        try {
            note = noteService.editNote(new ObjectMapper().readValue(monitoring.getLogData(), Note.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return note;
    }
}
