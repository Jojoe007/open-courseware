package com.takdanai.courseware.services;

import com.takdanai.courseware.controllers.payload.requests.LectureRequest;
import com.takdanai.courseware.entities.Lecture;
import com.takdanai.courseware.entities.Storage;
import com.takdanai.courseware.exceptions.StorageImageException;
import com.takdanai.courseware.repositories.LectureRepository;
import com.takdanai.courseware.services.base.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class LectureService extends BaseService<LectureRepository, Lecture> {

    private final StorageService storageService;

    protected LectureService(LectureRepository repository, StorageService storageService) {
        super(repository);
        this.storageService = storageService;
    }

    @Override
    public List<Lecture> all() {
        return super.all();
    }

    @Override
    public Optional<Lecture> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Lecture create(Lecture entity) {
        return super.create(entity);
    }

    public Lecture create(LectureRequest request) {
        Lecture lecture = new Lecture();
        Storage.Video video = new Storage.Video();
        Storage.Document document = new Storage.Document();

        try {
            video = storageService.saveVideo(request.video);
            document = storageService.saveDocument(request.document);
        } catch (IOException exception) {
            throw new StorageImageException("unable to store lecture files.");
        }

        lecture.setIndex(request.index);
        lecture.setTopic(request.topic);
        lecture.setSummary(request.summary);
        lecture.setVideo(video);
        lecture.setDocument(document);

        return super.create(lecture);
    }

    public List<Lecture> create(List<LectureRequest> requests) {
        return requests.stream().map(this::create).toList();
    }
}
