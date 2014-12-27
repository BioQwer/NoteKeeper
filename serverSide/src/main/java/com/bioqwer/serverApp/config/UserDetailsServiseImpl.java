package com.bioqwer.serverApp.config;

import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.model.UserRoleEnum;
import com.bioqwer.serverApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Antony on 27.12.2014.
 */
@Service
public class UserDetailsServiseImpl implements UserDetailsService {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByLogin(username);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);

        return userDetails;
    }
}
