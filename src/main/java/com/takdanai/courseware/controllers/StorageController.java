package com.takdanai.courseware.controllers;

import com.takdanai.courseware.entities.Storage;
import com.takdanai.courseware.services.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@Controller
public record StorageController(StorageService storageService) {

    @GetMapping("/storages")
    public String index(Model model) {
        List<Storage> storages = storageService.all();
        model.addAttribute("storages", storages);

        return "storages/index";
    }
}
