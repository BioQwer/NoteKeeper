package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Antony on 04.03.2015.
 * Contains {@link com.bioqwer.serverApp.model.Monitoring}s Query for Storage.
 */
@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring,Long> {
    
}
