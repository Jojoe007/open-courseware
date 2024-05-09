package com.takdanai.courseware.helpers;

import com.takdanai.courseware.entities.Student;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper {
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Student getPrincipal() {
        return (Student) getAuthentication().getPrincipal();
    }
}
