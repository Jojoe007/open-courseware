package com.takdanai.courseware.controllers.payload.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {
    @Override
    public void initialize(Image constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.equals(Objects.requireNonNull(file.getContentType()).split("/")[0], "image");
    }
}
