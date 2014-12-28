package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Antony on 28.12.2014.
 */
@Component
public class RestAuthenticationExceptionHandler extends SimpleUrlAuthenticationFailureHandler {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        User user = userService.getByLogin((String) exception.getAuthentication().getPrincipal());
        if (user == null) {
            response.sendError(404, "User not Found!");
            return;
        } else {
            response.sendError(403, "Incorrect password for Login " + user.getLogin());
        }
    }
}
