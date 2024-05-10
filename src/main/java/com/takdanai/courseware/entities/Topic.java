package com.takdanai.courseware.entities;

import com.google.common.collect.Lists;
import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Topic extends BaseEntity {
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "topics")
    private List<Course> course = Lists.newLinkedList();
}
