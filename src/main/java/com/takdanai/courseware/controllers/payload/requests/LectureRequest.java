package com.takdanai.courseware.controllers.payload.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class LectureRequest {
    public Byte index;
    public String topic;
    public String summary;
    public MultipartFile video;
    public MultipartFile document;
}
