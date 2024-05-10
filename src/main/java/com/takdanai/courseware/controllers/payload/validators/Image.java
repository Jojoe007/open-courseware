package com.takdanai.courseware.controllers.payload.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Image {
    String message() default "you must insert only image type.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
