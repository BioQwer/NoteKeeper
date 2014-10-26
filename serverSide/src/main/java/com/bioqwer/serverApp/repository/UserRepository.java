package com.bioqwer.serverApp.repository;

import com.bioqwer.serverApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Antony on 18.10.2014.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select b from User b where b.login = :name")
    User findByLogin(@Param("name") String login);

    @Query("select b from User b where b.email = :name")
    User findByEmail(@Param("name") String email);
}
