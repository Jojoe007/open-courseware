package com.takdanai.courseware.controllers.payload.requests;

import com.takdanai.courseware.controllers.payload.base.PasswordConfirmationRequestInterface;
import com.takdanai.courseware.controllers.payload.validators.PasswordConfirmation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordConfirmation
public class RegisterRequest extends PasswordConfirmationRequestInterface {
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String passwordConfirmation;
}
