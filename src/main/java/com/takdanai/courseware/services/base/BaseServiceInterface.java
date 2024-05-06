package com.takdanai.courseware.services.base;

import com.takdanai.courseware.entities.base.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseServiceInterface<T extends BaseEntity> {
    List<T> all();

    Optional<T> findById(Long id);

    T create(T request);

    Optional<T> update(Long id, T request);

    Optional<T> delete(Long id);
}
