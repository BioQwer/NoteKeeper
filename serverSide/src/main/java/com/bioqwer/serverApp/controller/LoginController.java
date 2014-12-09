package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.model.UserRoleEnum;
import com.bioqwer.serverApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Antony on 07.11.2014.
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        return userService.addUser(user);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public
    @ResponseBody
    UserDetails login(@RequestBody User userRequest) {

        User user = userService.getByLogin(userRequest.getLogin());
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
