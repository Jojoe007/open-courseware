package com.takdanai.courseware.controllers;

import com.takdanai.courseware.services.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public record StorageController(StorageService storageService) {

    @GetMapping("/storages")
    public String index() {
        return "storages/index";
    }
}
