package com.takdanai.courseware.controllers;

import com.takdanai.courseware.entities.Course;
import com.takdanai.courseware.entities.Topic;
import com.takdanai.courseware.services.CourseService;
import com.takdanai.courseware.services.LectureService;
import com.takdanai.courseware.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public record IndexController(CourseService courseService, LectureService lectureService,
                              StudentService studentService) {

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<Course> courses = courseService.all();
        List<Topic> topics = courseService.allTopics().stream().limit(6).toList();

        model.addAttribute("courses", courses);
        model.addAttribute("topics", topics);

        return "index";
    }

    @GetMapping("/upload")
    public String upload() {
        return "courses/upload";
    }

    @GetMapping("/video")
    public String video() {
        return "courses/video";
    }

    @GetMapping("/follow")
    public String follow() {
        return "students/follow";
    }

    @GetMapping("/history")
    public String history() {
        return "students/history";
    }

}
