package com.takdanai.courseware.controllers.payload.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmationValidator.class)
public @interface PasswordConfirmation {
    String message() default "The password confirmation must match the password.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
