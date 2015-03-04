package com.bioqwer.serverApp.service;

import com.bioqwer.serverApp.model.Monitoring;
import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;

import java.util.Collection;

/**
 * Created by Antony on 04.03.2015.
 * Service for Monitoring.
 */
public interface MonitoringService {

    /**
     * Method allow to get all {@link com.bioqwer.serverApp.model.User} actions.
     * @param user instance of {@link com.bioqwer.serverApp.model.User}.
     * @return Monitoring.
     */
    Collection<Monitoring> getUserAction(User user);

    /**
     * Method allow to get all {@link com.bioqwer.serverApp.model.User} actions for {@link com.bioqwer.serverApp.model.Note}.
     * @param user instance of {@link com.bioqwer.serverApp.model.User}.
     * @param note instance of {@link com.bioqwer.serverApp.model.Note}.
     * @return Monitoring.
     */
    Collection<Monitoring> getUserActionOnNote(User user,Note note);
    
    Monitoring addMonitoring(Object o);
    
    Object revertFromMonitoring(Monitoring monitoring);

}
