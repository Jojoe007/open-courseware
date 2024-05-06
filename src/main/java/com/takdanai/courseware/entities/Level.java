package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Level extends BaseEntity implements Comparable<Level> {
    private String title;
    private Integer value;

    @Override
    public int compareTo(Level other) {
        return value.compareTo(other.value);
    }

    enum Type {
        UNDERGRADUATE,
        GRADUATE,
        HIGH_SCHOOL;
    }
}
