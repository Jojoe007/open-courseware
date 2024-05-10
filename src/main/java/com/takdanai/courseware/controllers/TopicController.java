package com.takdanai.courseware.controllers;

import com.takdanai.courseware.entities.Topic;
import com.takdanai.courseware.exceptions.TopicNotFoundException;
import com.takdanai.courseware.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public record TopicController(CourseService courseService) {

    @GetMapping("/topics")
    public String index(Model model) {
        List<Topic> topics = courseService.allTopics();
        model.addAttribute("topics", topics);

        return "topics/index";
    }

    @GetMapping("/topic/{id}")
    public String show(@PathVariable Long id, Model model) {
        Topic topic = courseService.findTopicById(id)
                .orElseThrow(() -> new TopicNotFoundException(String.format("topic %d not found.", id)));

        model.addAttribute("topic", topic);
        return "topics/show";
    }
}
