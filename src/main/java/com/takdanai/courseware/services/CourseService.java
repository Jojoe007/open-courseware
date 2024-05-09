package com.takdanai.courseware.services;

import com.google.common.collect.Lists;
import com.takdanai.courseware.controllers.payload.requests.CommentRequest;
import com.takdanai.courseware.controllers.payload.requests.CourseRequest;
import com.takdanai.courseware.entities.*;
import com.takdanai.courseware.exceptions.CourseNotFoundException;
import com.takdanai.courseware.exceptions.StorageImageException;
import com.takdanai.courseware.exceptions.StudentNotFoundException;
import com.takdanai.courseware.repositories.CommentRepository;
import com.takdanai.courseware.repositories.CourseRepository;
import com.takdanai.courseware.repositories.DepartmentRepository;
import com.takdanai.courseware.repositories.TopicRepository;
import com.takdanai.courseware.services.base.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CourseService extends BaseService<CourseRepository, Course> {
    private final AuthenticationService authenticationService;

    private final CommentRepository commentRepository;

    private final LectureService lectureService;

    private final DepartmentRepository departmentRepository;

    private final TopicRepository<Topic> topicRepository;

    private final StorageService storageService;

    protected CourseService(CourseRepository repository, AuthenticationService authenticationService, CommentRepository commentRepository,
                            LectureService lectureService, DepartmentRepository departmentRepository,
                            TopicRepository<Topic> topicRepository, StorageService storageService) {
        super(repository);
        this.authenticationService = authenticationService;
        this.commentRepository = commentRepository;
        this.lectureService = lectureService;
        this.departmentRepository = departmentRepository;
        this.topicRepository = topicRepository;
        this.storageService = storageService;
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

    @Transactional
    public Course create(CourseRequest request) {
        Course course = new Course();
        Storage.Image thumbnail;
        List<Lecture> lectures = Lists.newLinkedList();

        var student = authenticationService.getCurrentStudent()
                .orElseThrow(() -> new BadCredentialsException("Require login to create course."));

        try {
            thumbnail = storageService.saveImage(request.thumbnail);
        } catch (IOException exception) {
            log.error(exception.getMessage());
            throw new StorageImageException("unable to store thumbnail image.");
        }

        lectures = lectureService.create(request.lectures);

        course.setTitle(request.title);
        course.setOverview(request.overview);
        course.setTopics(request.topics);
        course.setDepartments(request.departments);
        course.setThumbnail(thumbnail);
        course.setStudent(student);
        course.setType(request.type);
        course.setLevel(request.level);
        course.setStatus(request.status);
        course.setLectures(lectures);

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

    public void comment(Long id, CommentRequest commentRequest) {
        Course course = findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id + "course not found."));
        Student student = authenticationService.getCurrentStudent()
                .orElseThrow(() -> new StudentNotFoundException("you must be login for comment."));

        Comment comment = new Comment();
        comment.setBody(commentRequest.getBody());
        comment.setStudent(student);
        comment = commentRepository.save(comment);

        course.getComments().add(comment);
        repository.save(course);
    }

    public List<Department> allDepartments() {
        return departmentRepository.findAll();
    }

    public List<Topic> allTopics() {
        return topicRepository.findAll();
    }

    public List<Topic> allMainTopics() {
        return topicRepository.findTopicsByType(Topic.Type.MAIN);
    }

    public Optional<Topic> findTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public List<Topic.Sub> findByMainTopicId(Long mainTopicId) {
        return topicRepository.findByMainTopicId(mainTopicId);
    }
}
