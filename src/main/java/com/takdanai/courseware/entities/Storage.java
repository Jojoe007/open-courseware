package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "storage_type"
)
public class Storage extends BaseEntity {

    @Entity
    @DiscriminatorValue("DOCUMENT")
    public static class Document extends Storage {

    }
    @Entity
    @DiscriminatorValue("IMAGE")
    public static class Image extends Storage {

    }

    @Entity
    @DiscriminatorValue("VIDEO")
    public static class Video extends Storage {

    }
}
