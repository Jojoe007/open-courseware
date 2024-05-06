package com.takdanai.courseware.controllers;

import com.takdanai.courseware.controllers.payload.requests.LoginRequest;
import com.takdanai.courseware.controllers.payload.requests.RegisterRequest;
import com.takdanai.courseware.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authentication")
public record AuthenticationController(AuthenticationService authenticationService) {

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());

        return "authentication/login/new";
    }

    @PostMapping("/login")
    public String postLogin(@Valid LoginRequest loginRequest, BindingResult bindingResult, Model model,
                            HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "authentication/login/new";
        }

        if (!authenticationService.login(loginRequest, request, response)) {
            model.addAttribute("errorMessage", "Bad Credentials");

            return "authentication/login/new";
        }

        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());

        return "authentication/register/new";
    }

    @PostMapping("/register")
    public String postRegister(@Valid RegisterRequest registerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "authentication/register/new";
        }

        if (authenticationService().usernameAlreadyExist(registerRequest.getUsername())) {
            bindingResult.addError(new FieldError("usernameError", "username", "Username Already Exists"));

            return "authentication/register/new";
        }

        authenticationService.register(registerRequest);

        return "redirect:/authentication/login";
    }

    @DeleteMapping("/logout")
    public String postLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(authentication, request, response);

        return "redirect:/";
    }
}
