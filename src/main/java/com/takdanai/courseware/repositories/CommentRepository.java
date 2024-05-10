package com.takdanai.courseware.repositories;

import com.takdanai.courseware.entities.Comment;
import com.takdanai.courseware.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByStudent(Student student);
}
