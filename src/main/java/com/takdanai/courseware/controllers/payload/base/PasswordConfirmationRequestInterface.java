package com.takdanai.courseware.controllers.payload.base;

import lombok.Data;

@Data
public abstract class PasswordConfirmationRequestInterface {
    protected String password;
    protected String passwordConfirmation;
}
