package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.repository.UserRepository;
import com.bioqwer.serverApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    /**
     * Methods performs adding {@link com.bioqwer.serverApp.model.User} to DataBase.
     *
     * @param user instance of {@link com.bioqwer.serverApp.model.User} to be added.
     * @return added {@link com.bioqwer.serverApp.model.User}.
     */
    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * Methods performs delete {@link com.bioqwer.serverApp.model.User} from DataBase.
     * @param id of {@link com.bioqwer.serverApp.model.User} those be deleted.
     */
    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    /**
     * Methods performs get {@link com.bioqwer.serverApp.model.User} from DataBase.
     * @param userId of {@link com.bioqwer.serverApp.model.User} for query. 
     * @return {@link com.bioqwer.serverApp.model.User} from DataBase.
     */
    @Override
    public User getById(long userId) {
        return userRepository.findOne(userId);
    }

    /**
     * Methods performs get {@link com.bioqwer.serverApp.model.User} by login from DataBase.
     * @param login of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User} from DataBase.
     */
    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    /**
     * Methods performs get {@link com.bioqwer.serverApp.model.User} by email from DataBase.
     * @param email of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User} from DataBase.
     */
    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Methods performs edit {@link com.bioqwer.serverApp.model.User} from DataBase.
     * @param user of {@link com.bioqwer.serverApp.model.User}
     * @return edit {@link com.bioqwer.serverApp.model.User} from DataBase.
     */
    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * Methods performs get all {@link com.bioqwer.serverApp.model.User} from DataBase.
     * @return Collection of {@link com.bioqwer.serverApp.model.User} from DataBase.
     */
    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Methods performs search {@link com.bioqwer.serverApp.model.User} by login from DataBase.
     * @param partOfLogin search parameter
     * @return Collection of {@link com.bioqwer.serverApp.model.User}
     */
    @Override
    public Collection<User> searchByUserLogin(String partOfLogin) {
        return userRepository.findWhereLogin("%" + partOfLogin + "%");
    }
}
