package com.takdanai.courseware.controllers.payload.requests;

import com.takdanai.courseware.controllers.payload.base.PasswordConfirmationRequestInterface;
import com.takdanai.courseware.controllers.payload.validators.UsernameUnique;
import com.takdanai.courseware.entities.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisterRequest extends PasswordConfirmationRequestInterface {
    @UsernameUnique
    @NotBlank(message = "Username cannot be blank")
    public String username;

    @NotBlank(message = "First name cannot be blank")
    public String firstName;

    @NotBlank(message = "Last name cannot be blank")
    public String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.TIME)
    public LocalDate birthday;
    public Gender gender;
    public MultipartFile avatar;
    public String bio;

    public Gender[] genders() {
        return Gender.values();
    }
}
