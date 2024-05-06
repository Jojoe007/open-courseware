package com.takdanai.courseware.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.takdanai.courseware.controllers")
public class AuthenticationExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
    public String badCredentials(Model model, Exception exception) {
        System.out.println(exception.getLocalizedMessage());
        model.addAttribute("errorMessage", exception.getLocalizedMessage());

        return "authentication/login/new :: toast";
    }
}
