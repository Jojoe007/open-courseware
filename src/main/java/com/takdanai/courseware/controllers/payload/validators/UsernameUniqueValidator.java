package com.takdanai.courseware.controllers.payload.validators;

import com.takdanai.courseware.services.AuthenticationService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

    private final AuthenticationService authenticationService;

    public UsernameUniqueValidator(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !authenticationService.usernameAlreadyExist(username);
    }
}
