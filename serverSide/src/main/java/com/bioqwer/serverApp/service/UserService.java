package com.bioqwer.serverApp.service;

import com.bioqwer.serverApp.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 *  Interface for {@link com.bioqwer.serverApp.model.User} logic.
 */
@Service
public interface UserService {

    /**
     * Methods performs adding {@link com.bioqwer.serverApp.model.User} to Storage.
     *
     * @param user instance of {@link com.bioqwer.serverApp.model.User} to be added.
     * @return added {@link com.bioqwer.serverApp.model.User}.
     */
    User addUser(User user);

    /**
     * Methods performs delete {@link com.bioqwer.serverApp.model.User} from Storage.
     *
     * @param deleteUser of {@link com.bioqwer.serverApp.model.User} those be deleted.
     */
    void delete(User deleteUser);

    /**
     * Methods performs get {@link com.bioqwer.serverApp.model.User} from Storage.
     *
     * @param userId of {@link com.bioqwer.serverApp.model.User} for query.
     * @return {@link com.bioqwer.serverApp.model.User} from Storage.
     */
    User getById(long userId);

    /**
     * Methods performs get {@link com.bioqwer.serverApp.model.User} by login from Storage.
     *
     * @param login of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User} from Storage.
     */
    User getByLogin(String login);

    /**
     * Methods performs get {@link com.bioqwer.serverApp.model.User} by email from Storage.
     * @param email of {@link com.bioqwer.serverApp.model.User}.
     * @return {@link com.bioqwer.serverApp.model.User} from Storage.
     */
    User getByEmail(String email);

    /**
     * Methods performs edit {@link com.bioqwer.serverApp.model.User} from Storage.
     * @param user of {@link com.bioqwer.serverApp.model.User}
     * @return edit {@link com.bioqwer.serverApp.model.User} from Storage.
     */
    User editUser(User user);

    /**
     * Methods performs get all {@link com.bioqwer.serverApp.model.User} from Storage.
     * @return Collection of {@link com.bioqwer.serverApp.model.User} from Storage.
     */
    Collection<User> getAll();

    /**
     * Methods performs search {@link com.bioqwer.serverApp.model.User} by login from Storage.
     * @param partOfLogin search parameter
     * @return Collection of {@link com.bioqwer.serverApp.model.User}
     */
    Collection<User> searchByUserLogin(String partOfLogin);
}
