package com.takdanai.courseware.controllers.payload.requests;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.takdanai.courseware.controllers.payload.validators.Image;
import com.takdanai.courseware.entities.Course;
import com.takdanai.courseware.entities.Department;
import com.takdanai.courseware.entities.Topic;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
public class CourseRequest {
    @NotBlank(message = "Title can't be blank.")
    public String title;

    @NotBlank(message = "Overview can't be blank.")
    public String overview;

    public Set<Topic> topics = Sets.newHashSet();

    public Set<Department> departments = Sets.newHashSet();

    @Image(message = "You must insert only image type.")
    public MultipartFile thumbnail;

    @NotNull(message = "Type can't be blank.")
    public Course.Type type;

    @NotNull(message = "Level can't be blank.")
    public Course.Level level;

    @NotNull(message = "Status can't be blank.")
    public Course.Status status;

    @Valid
    @NotEmpty(message = "Lectures can't be empty.")
    public List<LectureRequest> lectures = Lists.newLinkedList();

    public Course.Type[] types() {
        return Course.Type.values();
    }

    public Course.Level[] levels() {
        return Course.Level.values();
    }

    public Course.Status[] statuses() {
        return Course.Status.values();
    }

    public void addLecture() {
        lectures.add(new LectureRequest());
    }

    public void removeLecture(Integer lectureIndex) {
        lectures.remove(lectureIndex.intValue());
    }
}
