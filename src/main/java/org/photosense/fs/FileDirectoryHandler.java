package org.photosense.fs;

import org.photosense.dto.FileNames;
import org.springframework.http.ResponseEntity;

public interface FileDirectoryHandler {
    public ResponseEntity<FileNames> getFileNamesFromDirectory(String pathName);
}
