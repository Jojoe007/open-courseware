package com.takdanai.courseware.controllers.payload.base;

import com.takdanai.courseware.controllers.payload.validators.PasswordConfirmation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordConfirmation
public abstract class PasswordConfirmationRequestInterface {
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotBlank(message = "Password confirmation cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String passwordConfirmation;
}
