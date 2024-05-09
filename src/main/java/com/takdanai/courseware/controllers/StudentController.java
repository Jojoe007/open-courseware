package com.takdanai.courseware.controllers;

import com.takdanai.courseware.entities.Student;
import com.takdanai.courseware.exceptions.StudentNotFoundException;
import com.takdanai.courseware.services.StudentService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public record StudentController(StudentService studentService) {

    @GetMapping("/students")
    public String index(Model model) {
        model.addAttribute("students", studentService.all());

        return "students/index";
    }

    @GetMapping("/student/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(String.format("student id: %d not found.", id))));

        return "students/show";
    }

    @GetMapping("/me")
    public String me(Model model, Principal principal) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        Student student = (Student) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());

        model.addAttribute("student", student);

        return "students/show";
    }
}
