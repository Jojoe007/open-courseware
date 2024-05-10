package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String body;

    @ManyToOne
    private Student student;
}
