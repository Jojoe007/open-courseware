package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Department extends BaseEntity {
    private String title;
}
