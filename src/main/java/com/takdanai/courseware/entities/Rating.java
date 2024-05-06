package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Rating extends BaseEntity {

    @Enumerated(EnumType.ORDINAL)
    private RatingValue value;

    @Getter
    public enum RatingValue {
        ONE_STAR("Very dissatisfied"),
        ONE_AND_A_HALF_STARS("Poor"),
        TWO_STARS("Fair"),
        TWO_AND_A_HALF_STARS("Mediocre"),
        THREE_STARS("Average"),
        THREE_AND_A_HALF_STARS("Above Average"),
        FOUR_STARS("Good"),
        FOUR_AND_A_HALF_STARS("Very Good"),
        FIVE_STARS("Excellent");

        private final String title;

        RatingValue(String title) {
            this.title = title;
        }
    }
}
