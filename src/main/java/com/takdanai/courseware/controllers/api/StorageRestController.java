package com.takdanai.courseware.controllers.api;

import com.takdanai.courseware.services.StorageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record StorageRestController(StorageService storageService) {

}
