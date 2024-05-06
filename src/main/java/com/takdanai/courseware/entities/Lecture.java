package com.takdanai.courseware.entities;


import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class Lecture extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long index;

    @NotBlank
    private String topic;

    @NotBlank
    private String description;

    @OneToOne
    private Storage.Video video;

    @OneToOne
    private Storage.Document document;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Course course;
}
