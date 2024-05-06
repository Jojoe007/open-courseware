package com.takdanai.courseware.entities;

import com.google.common.collect.Lists;
import com.takdanai.courseware.controllers.payload.requests.CourseRequest;
import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String title;

    private String overview;

    @ManyToOne
    private Level level;

    @ManyToMany
    private Set<Topic> topics = new HashSet<>();

    @ManyToMany
    private Set<Department> departments = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Storage.Image thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Valid
    @JoinColumn(name = "course_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Lecture> lectures = Lists.newLinkedList();

    public static Type[] types() {
        return Type.values();
    }

    public static Status[] statuses() {
        return Status.values();
    }

    public void addLecture() {
        lectures.add(new Lecture());
    }

    public void removeLecture(Integer lectureIndex) {
        lectures.remove(lectureIndex.intValue());
    }

    public static Course formRequest(CourseRequest request) {
        Course course = new Course();

        return course;
    }

    public enum Type {
        SHORT("Short"),
        LONG("Long");

        public final String title;

        Type(String title) {
            this.title = title;
        }
    }

    public enum Status {
        DRAFT,
        PUBLISHED,
        UNPUBLISHED
    }
}
