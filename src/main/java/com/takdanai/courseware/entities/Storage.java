package com.takdanai.courseware.entities;

import com.takdanai.courseware.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorColumn(name = "storage_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Storage extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String fileName;
    private String path;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Student uploadedBy;

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
