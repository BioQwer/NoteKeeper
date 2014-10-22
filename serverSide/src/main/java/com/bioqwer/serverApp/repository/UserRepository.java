package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Antony on 18.10.2014.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
