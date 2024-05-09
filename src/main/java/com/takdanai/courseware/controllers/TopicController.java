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
        List<Topic> topics = courseService.allMainTopics();
        model.addAttribute("topics", topics);

        return "topics/index";
    }

    @GetMapping("/topic/{id}")
    public String show(@PathVariable Long id, Model model) {
        Topic topic = courseService.findTopicById(id)
                .orElseThrow(() -> new TopicNotFoundException(String.format("topic %d not found.", id)));
        List<Topic.Sub> subTopics = courseService.findByMainTopicId(id);

        model.addAttribute("topic", topic);
        model.addAttribute("subTopics", subTopics);
        return "topics/show";
    }

    @GetMapping("/topic/*/subs/{id}")
    public String subsShow(@PathVariable Long id, Model model) {
        Topic.Sub subTopic = (Topic.Sub) courseService.findTopicById(id)
                .orElseThrow();
        model.addAttribute("subTopic", subTopic);

        return "topics/subs/show";
    }


}
