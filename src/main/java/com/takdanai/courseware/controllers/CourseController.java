package com.takdanai.courseware.controllers;

import com.takdanai.courseware.controllers.payload.requests.CourseRequest;
import com.takdanai.courseware.entities.Course;
import com.takdanai.courseware.exceptions.CourseNotFoundException;
import com.takdanai.courseware.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequestMapping
public record CourseController(CourseService courseService) {
    // TODO: Making pagination for pageable and sorting.

    @GetMapping("/courses")
    public String index(Model model) {
        var courses = courseService.all();
        model.addAttribute("courses", courses);

        return "courses/index";
    }

    @GetMapping("/course/{id}")
    public String show(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course")
        );

        model.addAttribute("course", course);
        return "courses/show";
    }

    @GetMapping("/course")
    public String news(Model model) {
        model.addAttribute("courseRequest", new CourseRequest());
        model.addAttribute("topics", courseService.allTopic());
        model.addAttribute("departments", courseService.allDepartments());

        return "courses/new";
    }

    @PostMapping(value = "/addLecture")
    public String addLecture(CourseRequest courseRequest) {
        courseRequest.addLecture();

        return "courses/new::lectures";
    }

    @PostMapping("/removeLecture")
    public String removeLecture(CourseRequest courseRequest, @RequestParam("removeLecture") Integer lectureIndex) {
        courseRequest.removeLecture(lectureIndex);

        return "courses/new::lectures";
    }

    @PostMapping("/course")
    public String create(@Valid CourseRequest courseRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "courses/new";
        }

        courseService.create(courseRequest);
        return "redirect:/courses";
    }
}
