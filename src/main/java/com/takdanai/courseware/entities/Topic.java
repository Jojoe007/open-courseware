package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorColumn(name = "topic_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Topic extends BaseEntity {
    private String title;

    @Data
    @Entity
    @DiscriminatorValue("MAIN")
    public static class Main extends Topic {

    }

    @Data
    @Entity
    @DiscriminatorValue("SUB")
    public static class Sub extends Topic {
        @ManyToOne(fetch = FetchType.LAZY)
        private Topic mainTopic;
    }
}
