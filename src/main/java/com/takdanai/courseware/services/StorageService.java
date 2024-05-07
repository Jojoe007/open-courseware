package com.takdanai.courseware.services;

import com.takdanai.courseware.entities.Storage;
import com.takdanai.courseware.repositories.DocumentRepository;
import com.takdanai.courseware.repositories.ImageRepository;
import com.takdanai.courseware.repositories.StorageRepository;
import com.takdanai.courseware.repositories.VideoRepository;
import com.takdanai.courseware.services.base.BaseService;
import com.takdanai.courseware.utils.StorageUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.errors.AuthenticationException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class StorageService extends BaseService<StorageRepository, Storage> {
    public static String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/storages";
    public static String DOCUMENT_STORAGE_DIRECTORY = STORAGE_DIRECTORY + "/documents";
    public static String IMAGE_STORAGE_DIRECTORY = STORAGE_DIRECTORY + "/images";
    public static String VIDEO_STORAGE_DIRECTORY = STORAGE_DIRECTORY + "/videos";
    private final AuthenticationService authenticationService;
    private final DocumentRepository documentRepository;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;

    protected StorageService(StorageRepository repository, AuthenticationService authenticationService, DocumentRepository documentRepository, ImageRepository imageRepository, VideoRepository videoRepository) {
        super(repository);
        this.authenticationService = authenticationService;
        this.documentRepository = documentRepository;
        this.imageRepository = imageRepository;
        this.videoRepository = videoRepository;
    }

    public Optional<Storage.Document> findDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public Optional<Storage.Document> findDocumentByFileName(String fileName) {
        return documentRepository.findByFileName(fileName);
    }

    public Storage.Document saveDocument(MultipartFile file) throws IOException {
        var storedFile = saveFileToLocalStorage(file);

        Storage.Document document = new Storage.Document();
        document.setFileName(storedFile.getFirst());
        document.setPath(storedFile.getSecond());
        document.setType(file.getContentType());
        document.setUploadedBy(authenticationService.getCurrentStudent()
                .orElseThrow(() -> new AuthenticationException("Require login to upload image.")));

        return repository.save(document);
    }

    public Optional<Storage.Image> finaImageById(Long id) {
        return imageRepository.findById(id);
    }

    public Optional<Storage.Image> findImageByFileName(String fileName) {
        return imageRepository.findByFileName(fileName);
    }

    public Storage.Image saveImage(MultipartFile file) throws IOException {
        var storedFile = saveFileToLocalStorage(file);

        Storage.Image image = new Storage.Image();
        image.setFileName(storedFile.getFirst());
        image.setPath(storedFile.getSecond());
        image.setType(file.getContentType());
        image.setUploadedBy(authenticationService.getCurrentStudent()
                .orElseThrow(() -> new AuthenticationException("Require login to upload image.")));

        return repository.save(image);
    }

    public Optional<Storage.Video> findVideoById(Long id) {
        return videoRepository.findById(id);
    }

    public Optional<Storage.Video> findVideoByFileName(String fileName) {
        return videoRepository.findByFileName(fileName);
    }

    public Storage.Video saveVideo(MultipartFile file) throws IOException {
        var storedFile = saveFileToLocalStorage(file);

        Storage.Video video = new Storage.Video();
        video.setFileName(storedFile.getFirst());
        video.setPath(storedFile.getSecond());
        video.setType(file.getContentType());
        video.setUploadedBy(authenticationService.getCurrentStudent()
                .orElseThrow(() -> new AuthenticationException("Require login to upload video.")));

        return repository.save(video);
    }

    public Resource loadFile(String fileName) throws MalformedURLException {
        Path filePath = Paths.get(IMAGE_STORAGE_DIRECTORY, fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        }

        throw new RuntimeException("Could not read the file!");
    }

    private Pair<String, String> saveFileToLocalStorage(MultipartFile file) throws IOException {
        StringBuilder fileName = new StringBuilder();
        String newFileName = generateFileName(file.getOriginalFilename());

        Path filePath = Paths.get(IMAGE_STORAGE_DIRECTORY, newFileName);
        fileName.append(filePath);
        Files.write(filePath, file.getBytes());

        return Pair.of(fileName.toString(), filePath.toString());
    }

    private String generateFileName(String oldName) {
        String type = StorageUtils.getFileType(oldName);
        String newName = UUID.randomUUID().toString();

        return String.format("%s.%s", newName, type);
    }
}
