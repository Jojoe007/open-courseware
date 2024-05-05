package com.takdanai.courseware.entities;

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
import java.util.List;

@Data
@Entity
public class Course extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Valid
    @Embedded
    private Information information;

    @OneToMany
    private List<Lecture> lectures;

    @Data
    @Embeddable
    public static class Information {
        @NotBlank(message = "title can't be blank")
        private String title;

        private String overview;

        private String instructor;
    }

    public enum Type {
        SHORT,
        LONG
    }
}
