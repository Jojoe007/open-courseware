package com.takdanai.courseware.controllers;

import com.takdanai.courseware.services.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course/{courseId}")
public record LectureController(LectureService lectureService) {
}
