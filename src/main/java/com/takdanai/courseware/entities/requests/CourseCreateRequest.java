package com.takdanai.courseware.entities.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CourseCreateRequest {
    @NotEmpty(message = "title can't be empty")
    String title;
}
