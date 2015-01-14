package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Contains {@link com.bioqwer.serverApp.model.User}s Query for Storage.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Methods provide SQL Query for get {@link com.bioqwer.serverApp.model.User} from Storage by {@link com.bioqwer.serverApp.model.User#login}.
     *
     * @param login of {@link com.bioqwer.serverApp.model.User}.
     * @return specific {@link com.bioqwer.serverApp.model.User}.
     */
    @Query("select b from User b where b.login = :login")
    User findByLogin(@Param("login") String login);

    /**
     * Methods provide SQL Query for get {@link com.bioqwer.serverApp.model.User} from Storage by {@link com.bioqwer.serverApp.model.User#email}.
     *
     * @param email of {@link com.bioqwer.serverApp.model.User}.
     * @return specific {@link com.bioqwer.serverApp.model.User}.
     */
    @Query("select b from User b where b.email = :email")
    User findByEmail(@Param("email") String email);

    /**
     * Methods provide SQL Query for searching {@link com.bioqwer.serverApp.model.User}s in Storage by part of {@link com.bioqwer.serverApp.model.User#login}.
     * @param partOfLogin search parameter
     * @return Collection of found {@link com.bioqwer.serverApp.model.User}s.
     */
    @Query("select b from User b where b.login like :partOfLogin")
    Collection<User> findWhereLogin(@Param("partOfLogin") String partOfLogin);
}
