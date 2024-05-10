package com.takdanai.courseware.controllers;

import com.takdanai.courseware.controllers.payload.requests.CommentRequest;
import com.takdanai.courseware.controllers.payload.requests.CourseRequest;
import com.takdanai.courseware.entities.Course;
import com.takdanai.courseware.exceptions.CourseNotFoundException;
import com.takdanai.courseware.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequestMapping
public class CourseController {
    // TODO: Making pagination for pageable and sorting.

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String index(Model model) {
        var courses = courseService.all();
        model.addAttribute("courses", courses);

        return "courses/index";
    }

    @GetMapping("/course/{id:\\d+}")
    public String show(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id).orElseThrow(
                () -> new CourseNotFoundException(String.format("Course %d not found.", id))
        );

        model.addAttribute("course", course);
        model.addAttribute("commentRequest", new CommentRequest());
        return "courses/show";
    }


    @GetMapping("/course")
    @PreAuthorize("isAuthenticated()")
    public String news(Model model) {
        model.addAttribute("courseRequest", new CourseRequest());
        model.addAttribute("topics", courseService.allTopics());
        model.addAttribute("departments", courseService.allDepartments());

        return "courses/new";
    }

    @PostMapping(value = "/addLecture")
    @PreAuthorize("isAuthenticated()")
    public String addLecture(CourseRequest courseRequest) {
        if (courseRequest.lectures.size() <= 16) {
            courseRequest.addLecture();
        }

        return "courses/new::lectures";
    }

    @PostMapping("/removeLecture")
    @PreAuthorize("isAuthenticated()")
    public String removeLecture(CourseRequest courseRequest, @RequestParam("removeLecture") Integer lectureIndex) {
        courseRequest.removeLecture(lectureIndex);

        return "courses/new::lectures";
    }

    @PostMapping("/course")
    @PreAuthorize("isAuthenticated()")
    public String create(@Valid CourseRequest courseRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("topics", courseService.allTopics());
            model.addAttribute("departments", courseService.allDepartments());
            return "courses/new";
        }

        courseService.create(courseRequest);
        return "redirect:/courses";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/course/{id}/comment")
    public String comment(@PathVariable Long id, CommentRequest commentRequest, UsernamePasswordAuthenticationToken authenticationToken) {
        System.out.println(authenticationToken.getPrincipal());
        courseService.comment(id, commentRequest);

        return "redirect:/course/" + id;
    }
}
