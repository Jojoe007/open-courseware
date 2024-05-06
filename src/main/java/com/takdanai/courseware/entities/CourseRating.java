package com.takdanai.courseware.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class CourseRating extends Rating {

    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}
