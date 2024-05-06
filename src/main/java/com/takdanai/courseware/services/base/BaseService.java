package com.takdanai.courseware.services.base;

import com.takdanai.courseware.entities.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<R extends JpaRepository<T, Long>, T extends BaseEntity> implements BaseServiceInterface<T> {

    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> all() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public T create(T request) {
        return repository.save(request);
    }

    @Override
    public Optional<T> update(Long id, T request) {
        boolean exist = repository.existsById(id);
        if (exist) {
            return Optional.of(repository.save(request));
        }

        return Optional.empty();
    }

    @Override
    public Optional<T> delete(Long id) {
        Optional<T> existEntity = repository.findById(id);
        if (existEntity.isPresent()) {
            repository.deleteById(id);
            return existEntity;
        }

        return Optional.empty();
    }
}
