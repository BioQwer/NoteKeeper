package com.bioqwer.serverApp.service;

import com.bioqwer.serverApp.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Antony on 21.10.2014.
 */
@Service
public interface UserService {

    User addUser(User user);

    void delete(long id);

    User getById(long id);

    User getByLogin(String login);

    User getByEmail(String email);

    User editUser(User user);

    Collection<User> getAll();

}
