package com.takdanai.courseware.services;

import com.takdanai.courseware.controllers.payload.requests.CourseRequest;
import com.takdanai.courseware.entities.Course;
import com.takdanai.courseware.entities.Department;
import com.takdanai.courseware.entities.Lecture;
import com.takdanai.courseware.entities.Topic;
import com.takdanai.courseware.repositories.CourseRepository;
import com.takdanai.courseware.repositories.DepartmentRepository;
import com.takdanai.courseware.repositories.TopicRepository;
import com.takdanai.courseware.services.base.BaseService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseService extends BaseService<CourseRepository, Course> {
    private final AuthenticationService authenticationService;

    private final LectureService lectureService;

    private final DepartmentRepository departmentRepository;

    private final TopicRepository topicRepository;

    protected CourseService(CourseRepository repository, AuthenticationService authenticationService, LectureService lectureService, DepartmentRepository departmentRepository, TopicRepository topicRepository) {
        super(repository);
        this.authenticationService = authenticationService;
        this.lectureService = lectureService;
        this.departmentRepository = departmentRepository;
        this.topicRepository = topicRepository;
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
    public Course create(Course request) {
        return super.create(request);
    }

    public Course create(CourseRequest request) {
        var student = authenticationService.getCurrentStudent()
                .orElseThrow(() -> new BadCredentialsException("Require login to create course."));

        Course course = new Course();
        course.setTitle(request.title);
        course.setOverview(request.description);
        course.setTopics(request.topics);
        course.setDepartments(request.departments);
        course.setThumbnail(request.thumbnail);
        course.setType(request.type);
        course.setLevel(request.level);
        course.setStatus(request.status);

        return super.create(course);
    }

    @Override
    public Optional<Course> update(Long id, Course request) {
        return super.update(id, request);
    }

    @Override
    public Optional<Course> delete(Long id) {
        return super.delete(id);
    }

    public void addLecture(Course course) {
        course.getLectures().add(new Lecture());
    }

    public void addLecture(CourseRequest request) {
        request.addLecture();
    }

    public void removeLecture(Course course, int lectureIndex) {
        course.getLectures().remove(lectureIndex);
    }

    public void removeLecture(CourseRequest request, int lectureIndex) {
        request.addLecture();
    }

    public List<Department> allDepartments() {
        return departmentRepository.findAll();
    }

    public List<Topic> allTopic() {
        return topicRepository.findAll();
    }
}
