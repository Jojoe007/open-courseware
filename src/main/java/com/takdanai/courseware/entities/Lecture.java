package com.takdanai.courseware.entities;


import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Lecture extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Byte index;

    @NotBlank
    private String topic;

    @NotBlank
    private String summary;

    @OneToOne
    private Storage.Video video;

    @OneToOne
    private Storage.Document document;
}
