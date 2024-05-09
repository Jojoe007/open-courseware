package com.takdanai.courseware.repositories;

import com.takdanai.courseware.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository<T extends Topic> extends JpaRepository<T, Long> {
    List<Topic> findTopicsByType(Topic.Type type);

    List<Topic.Sub> findByMainTopicId(Long mainTopicId);
}
