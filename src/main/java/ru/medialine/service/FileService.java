package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${resources.uploadPath}")
    private String uploadPath;

    @SneakyThrows
    public String upload(MultipartFile file, String oldPath) {
        if(!oldPath.isBlank()) {
            delete(oldPath);
        }
        return upload(file);
    }

    @SneakyThrows
    public String upload(MultipartFile file) {

        Path uploadDir = Paths.get(uploadPath).normalize();

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String fileUuid = UUID.randomUUID().toString();
        String filenameResult = fileUuid + "." + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(filenameResult);

        file.transferTo(filePath);

        return filenameResult;
    }

    @SneakyThrows
    public void delete(String path) {
        Path fullPath = Paths.get(uploadPath + path).normalize();
        Files.delete(fullPath);
    }
}
