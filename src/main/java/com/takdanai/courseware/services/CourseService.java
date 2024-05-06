package com.takdanai.courseware.services;

import com.takdanai.courseware.entities.Course;
import com.takdanai.courseware.entities.Lecture;
import com.takdanai.courseware.repositories.CourseRepository;
import com.takdanai.courseware.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseService extends BaseService<CourseRepository, Course> {
    protected CourseService(CourseRepository repository) {
        super(repository);
    }

    @Override
    public List<Course> all() {
        return super.all();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Course create(Course entity) {
        return super.create(entity);
    }

    @Override
    public Optional<Course> update(Long id, Course entity) {
        return super.update(id, entity);
    }

    @Override
    public Optional<Course> delete(Long id) {
        return super.delete(id);
    }

    public void addLecture(Course course) {
        course.getLectures().add(new Lecture());
    }

    public void removeLecture(Course course, int lectureIndex) {
        course.getLectures().remove(lectureIndex);
    }
}
