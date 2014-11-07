package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.model.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Qualifier("userServiceImpl")
    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // с помощью нашего сервиса UserService получаем User
        User user = userService.getByLogin(username);
        // указываем роли для этого пользователя
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        // на основании полученныйх даных формируем объект UserDetails
        // который позволит проверить введеный пользователем логин и пароль
        // и уже потом аутентифицировать пользователя
        ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(),
                        shaPasswordEncoder.encodePassword(user.getPassword(), null),
                        roles);

        return userDetails;
    }
}
