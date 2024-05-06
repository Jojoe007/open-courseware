package com.takdanai.courseware.controllers.payload.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameUniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUnique {
    String message() default "username already taken.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
