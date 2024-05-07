package com.takdanai.courseware.controllers;

import com.takdanai.courseware.services.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@Log4j2
@RestController
public record StorageRestController(StorageService storageService) {

    @GetMapping("/storage/{fileName}")
    public ResponseEntity<Resource> show(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = storageService.loadFile(fileName);

        log.info(fileName);
        log.info(resource.getFilename());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
