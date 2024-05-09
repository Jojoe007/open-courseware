package com.takdanai.courseware.services;

import com.takdanai.courseware.controllers.payload.requests.LoginRequest;
import com.takdanai.courseware.controllers.payload.requests.RegisterRequest;
import com.takdanai.courseware.entities.Storage;
import com.takdanai.courseware.entities.Student;
import com.takdanai.courseware.entities.enums.Role;
import com.takdanai.courseware.exceptions.StorageImageException;
import com.takdanai.courseware.repositories.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@Service
public class AuthenticationService {

    private final StudentRepository studentRepository;
    private final AuthenticationManager authenticationManager;
    private final StorageService storageService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();

    public AuthenticationService(StudentRepository studentRepository, AuthenticationManager authenticationManager, StorageService storageService, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.authenticationManager = authenticationManager;
        this.storageService = storageService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            securityContextRepository.saveContext(context, request, response);

            return true;

        } catch (AuthenticationException exception) {
            return false;
        }
    }

    @Transactional
    public void register(RegisterRequest request) {
        Student student = new Student();
        Storage.Image avatar = new Storage.Image();

        try {
            avatar = storageService.saveImage(request.avatar);
        } catch (IOException exception) {
            log.error(exception.getMessage());
            throw new StorageImageException("unable to store avatar.");
        }

        student.setUsername(request.username);
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setFirstName(request.firstName);
        student.setLastName(request.lastName);
        student.setBirthday(request.birthday);
        student.setGender(request.gender);
        student.setBio(request.bio);
        student.setAvatar(avatar);
        student.setRole(Role.ROLE_USER);

        studentRepository.save(student);
    }

    public boolean logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        securityContextLogoutHandler.logout(request, response, authentication);
        return true;
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Optional<Student> getCurrentStudent() {
        return findByAuthentication(getAuthentication());
    }

    public boolean usernameAlreadyExist(String username) {
        return studentRepository.existsByUsername(username);
    }

    public Optional<Student> findByAuthentication(Authentication authentication) {
        return studentRepository.findByUsername(authentication.getName());
    }
}
