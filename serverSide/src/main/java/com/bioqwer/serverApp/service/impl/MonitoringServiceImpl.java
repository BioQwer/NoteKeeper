package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.model.Monitoring;
import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.repository.MonitoringRepository;
import com.bioqwer.serverApp.service.MonitoringService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    @Override
    public Collection<Monitoring> getUserAction(User user) {
        return null;
    }

    @Override
    public Collection<Monitoring> getUserActionOnNote(User user, Note note) {
        return null;
    }

    @Override
    public Monitoring addMonitoring(Object o)  {
        Monitoring result = new Monitoring();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result.setLogData(objectMapper.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        result.setLogTime(new Timestamp(System.currentTimeMillis()));
        result.setUserId(o.getUserId());
        result.setNoteId(null);

        return monitoringRepository.saveAndFlush(result);
    }

    @Override
    public Object revertFromMonitoring(Monitoring monitoring) {
        return null;
    }
}
