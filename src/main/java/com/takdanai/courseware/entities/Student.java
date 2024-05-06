package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import com.takdanai.courseware.entities.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(indexes = {
        @Index(columnList = "username")
})
public class Student extends BaseEntity {
    private String username;

    private String password;

    private Set<Role> roles;
}
