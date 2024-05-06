package com.takdanai.courseware.controllers.payload.validators;

import com.takdanai.courseware.controllers.payload.base.PasswordConfirmationRequestInterface;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, PasswordConfirmationRequestInterface> {
    @Override
    public boolean isValid(PasswordConfirmationRequestInterface password, ConstraintValidatorContext context) {
        boolean isValid = Objects.equals(password.getPassword(), password.getPasswordConfirmation());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("passwordConfirmation")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
