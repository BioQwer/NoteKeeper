package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Antony on 18.10.2014.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select b from User b where b.login = :login")
    User findByLogin(@Param("login") String login);

    @Query("select b from User b where b.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("select b from User b where b.login like :partOfLogin")
    Collection<User> findWhereLogin(@Param("partOfLogin") String partOfLogin);
}
