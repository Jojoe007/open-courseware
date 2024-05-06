package com.takdanai.courseware.controllers.payload.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseCreateRequest {
    @NotBlank(message = "title can't be blank")
    private String title;

    private String overview;
}
