package com.takdanai.courseware.entities;

import com.google.common.collect.Lists;
import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.*;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
public class Topic extends BaseEntity {
    private String title;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Data
    @Entity
    @DiscriminatorValue("0")
    public static class Main extends Topic {

    }

    @Data
    @Entity
    @DiscriminatorValue("1")
    public static class Sub extends Topic {
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "main_topic_id")
        private Main mainTopic;

        @ManyToMany
        private List<Course> course = Lists.newLinkedList();
    }


    public static enum Type {
        MAIN,
        SUB
    }
}
