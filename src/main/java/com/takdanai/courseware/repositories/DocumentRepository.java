package com.takdanai.courseware.repositories;

import com.takdanai.courseware.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Storage.Document, Long> {
    Optional<Storage.Document> findByFileName(String fileName);
}
