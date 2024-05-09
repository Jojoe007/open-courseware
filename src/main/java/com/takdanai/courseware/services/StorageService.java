package com.takdanai.courseware.services;

import com.takdanai.courseware.entities.Storage;
import com.takdanai.courseware.repositories.DocumentRepository;
import com.takdanai.courseware.repositories.ImageRepository;
import com.takdanai.courseware.repositories.StorageRepository;
import com.takdanai.courseware.repositories.VideoRepository;
import com.takdanai.courseware.services.base.BaseService;
import com.takdanai.courseware.utils.StorageUtils;
import lombok.extern.log4j.Log4j2;
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
    private final DocumentRepository documentRepository;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;

    protected StorageService(StorageRepository repository, DocumentRepository documentRepository, ImageRepository imageRepository, VideoRepository videoRepository) {
        super(repository);
        this.documentRepository = documentRepository;
        this.imageRepository = imageRepository;
        this.videoRepository = videoRepository;
    }

    @Override
    public Optional<Storage> findById(Long id) {
        return super.findById(id);
    }

    public Optional<Storage> findByFileName(String fileName) {
        return repository.findByFileName(fileName);
    }

    public Optional<Storage.Document> findDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public Optional<Storage.Document> findDocumentByFileName(String fileName) {
        return documentRepository.findByFileName(fileName);
    }

    public Storage.Document saveDocument(MultipartFile file) throws IOException {
        var storedFile = saveFileToLocalStorage(file, "document");

        Storage.Document document = new Storage.Document();
        document.setFileName(storedFile.getFirst());
        document.setPath(storedFile.getSecond());
        document.setType(file.getContentType());
//        document.setUploadedBy(AuthenticationHelper.getPrincipal());
//        document.setUploadedBy(authenticationService.getCurrentStudent()
//                .orElseThrow(() -> new AuthenticationException("Require login to upload image.")));

        return repository.save(document);
    }

    public Optional<Storage.Image> finaImageById(Long id) {
        return imageRepository.findById(id);
    }

    public Optional<Storage.Image> findImageByFileName(String fileName) {
        return imageRepository.findByFileName(fileName);
    }

    public Storage.Image saveImage(MultipartFile file) throws IOException {
        var storedFile = saveFileToLocalStorage(file, "image");

        Storage.Image image = new Storage.Image();
        image.setFileName(storedFile.getFirst());
        image.setPath(storedFile.getSecond());
        image.setType(file.getContentType());
//        image.setUploadedBy(AuthenticationHelper.getPrincipal());
//        image.setUploadedBy(authenticationService.getCurrentStudent()
//                .orElseThrow(() -> new AuthenticationException("Require login to upload image.")));

        return repository.save(image);
    }

    public Optional<Storage.Video> findVideoById(Long id) {
        return videoRepository.findById(id);
    }

    public Optional<Storage.Video> findVideoByFileName(String fileName) {
        return videoRepository.findByFileName(fileName);
    }

    public Storage.Video saveVideo(MultipartFile file) throws IOException {
        var storedFile = saveFileToLocalStorage(file, "video");

        Storage.Video video = new Storage.Video();
        video.setFileName(storedFile.getFirst());
        video.setPath(storedFile.getSecond());
        video.setType(file.getContentType());
//        video.setUploadedBy(AuthenticationHelper.getPrincipal());
//        video.setUploadedBy(authenticationService.getCurrentStudent()
//                .orElseThrow(() -> new AuthenticationException("Require login to upload video.")));

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

    private Pair<String, String> saveFileToLocalStorage(MultipartFile file, String type) throws IOException {
        String newFileName = generateFileName(file.getOriginalFilename());
        String storeFilePath = "/storages";
        Path realFilePath;

        switch (type) {
            case "image": {
                realFilePath = Paths.get(IMAGE_STORAGE_DIRECTORY, newFileName);
                storeFilePath += "/images/" + newFileName;
            }

            case "video": {
                realFilePath = Paths.get(VIDEO_STORAGE_DIRECTORY, newFileName);
                storeFilePath += "/videos/" + newFileName;
            }

            case "document": {
                realFilePath = Paths.get(DOCUMENT_STORAGE_DIRECTORY, newFileName);
                storeFilePath += "/documents/" + newFileName;
            }

            case null, default: {
                realFilePath = Paths.get(STORAGE_DIRECTORY, newFileName);
                storeFilePath += newFileName;
            }
        }

        Files.write(realFilePath, file.getBytes());

        return Pair.of(newFileName, storeFilePath);
    }

    private String generateFileName(String oldName) {
        String type = StorageUtils.getFileType(oldName);
        String newName = UUID.randomUUID().toString();

        return String.format("%s.%s", newName, type);
    }
}
