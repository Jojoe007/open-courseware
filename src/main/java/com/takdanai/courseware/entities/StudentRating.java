package com.takdanai.courseware.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentRating extends Rating {

    @ManyToOne
    private Student given;

    @ManyToOne
    private Student received;
}
