package com.takdanai.courseware.controllers.payload.requests;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.takdanai.courseware.entities.Course;
import com.takdanai.courseware.entities.Department;
import com.takdanai.courseware.entities.Storage;
import com.takdanai.courseware.entities.Topic;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CourseRequest {
    @NotBlank(message = "title can't be blank")
    public String title;

    @NotBlank(message = "overview can't be blank")
    public String overview;

    public Set<Topic> topics = Sets.newHashSet();

    public Set<Department> departments = Sets.newHashSet();

    public Storage.Image thumbnail;

    public Course.Type type;

    public Course.Level level;

    public Course.Status status;

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
