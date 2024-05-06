package com.takdanai.courseware.repositories;

import com.takdanai.courseware.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByUsername(String username);

    Optional<Student> findByUsername(String username);
}
