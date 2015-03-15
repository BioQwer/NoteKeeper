package com.bioqwer.serverApp.controller;

import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.UserService;
import org.apache.log4j.Logger;
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
 * Provide Exception Handler for Authentication Failure.
 */
@Component
public class RestAuthenticationExceptionHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOGGER = Logger.getLogger(RestAuthenticationExceptionHandler.class);
    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    /**
     * {@inheritDoc}
     * <p>
     * <tt>when "User not Found in Storage" set {@link org.springframework.http.HttpStatus} 404 </tt>
     * <p>
     * <tt>when "Incorrect password for Login" set {@link org.springframework.http.HttpStatus} 403 </tt>
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        User user = userService.getByLogin((String) exception.getAuthentication().getPrincipal());
        if (user == null) {
            String message = "User not Found!";
            response.sendError(404, message);
            LOGGER.debug("Send error response with status " + response.getStatus() + " and message " + message);
            return;
        } else {
            String message = "Incorrect password for Login " + user.getLogin();
            response.sendError(403, message);
            LOGGER.debug("Send error response with status " + response.getStatus() + " and message " + message);
        }
    }
}
