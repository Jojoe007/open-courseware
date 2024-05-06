package com.takdanai.courseware.entities;

import com.google.common.collect.Sets;
import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
public class Course extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Valid
    @Embedded
    private Information information;

    @Valid
    @JoinColumn(name = "course_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Lecture> lectures = new ArrayList<>();

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

    @Data
    @Embeddable
    public static class Information {
        @NotBlank(message = "title can't be blank")
        private String title;

        @NotBlank(message = "description can't be blank")
        private String description;

        @Valid
        @ManyToOne
        private Level level;

        @Valid
        @ManyToMany
        private Set<Topic> topics = new HashSet<>();

        @Valid
        @ManyToMany
        private Set<Department> departments = new HashSet<>();

        @OneToOne
        private Storage.Image image;

        @ManyToOne
        private Student instructor;
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
