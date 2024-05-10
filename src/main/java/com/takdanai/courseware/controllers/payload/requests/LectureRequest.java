package com.takdanai.courseware.controllers.payload.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class LectureRequest {

    @NotNull
    public Byte index;
    @NotBlank
    public String topic;
    @NotBlank
    public String summary;
    @NotNull
    public MultipartFile video;
    @NotNull
    public MultipartFile document;
}
