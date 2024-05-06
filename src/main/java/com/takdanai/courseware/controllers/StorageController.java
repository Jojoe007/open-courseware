package com.takdanai.courseware.controllers;

import com.takdanai.courseware.services.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public record StorageController(StorageService storageService) {

    @GetMapping("/storages")
    public String index() {
        return "storages/index";
    }
}
