package com.takdanai.courseware.controllers;

import com.takdanai.courseware.entities.Course;
import com.takdanai.courseware.entities.Lecture;
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
        model.addAttribute("course", courseService.findById(id));
        return "courses/show";
    }

    @GetMapping("/course")
    public String news(Model model) {
        model.addAttribute("course", new Course());

        return "courses/new";
    }

    @PostMapping(value = "/addLecture")
    public String addLecture(Course course) {
        course.addLecture();

        return "courses/new :: lectures";
    }

    @PostMapping("/removeLecture")
    public String removeLecture(Course course, @RequestParam("removeLecture") Integer lectureIndex) {
        course.removeLecture(lectureIndex);

        return "courses/new :: lectures";
    }

    @PostMapping("/course")
    public String create(@Valid Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "courses/new";
        } else {
            //        courseService.create(course);
            return "redirect:/courses";
        }
    }


//    @GetMapping("/courses")
//    public ResponseEntity<?> index() {
//        var courses = courseService.index();
//        return ResponseEntity.ok().body(courses);
//    }
//
//    @GetMapping("/course/{id}")
//    public ResponseEntity<?> show(@PathVariable Long id) {
//        var course = courseService.show(id);
//        if (course.isPresent()) {
//            return ResponseEntity.ok().body(course.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/course")
//    public ResponseEntity<?> create(@RequestBody Course body) {
//        var course = courseService.create(body);
//        return ResponseEntity.ok(course);
//    }
}
