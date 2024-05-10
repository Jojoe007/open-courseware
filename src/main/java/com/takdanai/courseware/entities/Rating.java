package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rating extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.ORDINAL)
    private RatingValue value;

    @ManyToOne
    private Student student;

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
