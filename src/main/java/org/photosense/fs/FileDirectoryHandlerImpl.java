package org.photosense.fs;

import lombok.extern.slf4j.Slf4j;
import org.photosense.dto.FileNames;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.photosense.exeption.ErrorMessage.DIRECTORY_SCAN_FAILED;
import static org.photosense.exeption.ErrorMessage.PATH_NOT_FOUND;

@Slf4j
@Service
public class FileDirectoryHandlerImpl implements FileDirectoryHandler {

    private static final Set<String> EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".bmp", ".gif");

    @Override
    public ResponseEntity<FileNames> getFileNamesFromDirectory(String pathName) {
        log.info("Try to find images in {}", pathName);
        Path path = Path.of(pathName);
        if (!Files.exists(path)) throw new IllegalArgumentException(PATH_NOT_FOUND.format(pathName));
        try (Stream<Path> stream = Files.walk(path)) {
            List<String> fileNames = stream
                    .filter(Files::isRegularFile)
                    .filter(this::isImageByExtension)
                    .map(Path::toString)
                    .toList();
            return ResponseEntity.ok(new FileNames(fileNames));
        } catch (IOException exception) {
            log.error(DIRECTORY_SCAN_FAILED.format(path), exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean isImageByExtension(Path filePath) {
        String fileName = filePath.toString().toLowerCase();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) return false;
        String ext = fileName.substring(dotIndex);
        return EXTENSIONS.contains(ext);
    }
}
