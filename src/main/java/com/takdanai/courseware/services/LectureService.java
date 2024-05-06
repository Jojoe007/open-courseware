package com.takdanai.courseware.services;

import com.takdanai.courseware.entities.Storage;
import com.takdanai.courseware.repositories.StorageRepository;
import com.takdanai.courseware.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class LectureService  extends BaseService<StorageRepository, Storage> {
    protected LectureService(StorageRepository repository) {
        super(repository);
    }
}
