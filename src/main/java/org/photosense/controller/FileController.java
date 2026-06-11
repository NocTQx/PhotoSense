package org.photosense.controller;

import lombok.AllArgsConstructor;
import org.photosense.dto.FileNames;
import org.photosense.fs.FileDirectoryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileDirectoryHandler fileDirectoryHandler;

    @PostMapping("/names")
    public ResponseEntity<FileNames> getFileNames(@RequestParam String pathName) {
        return fileDirectoryHandler.getFileNamesFromDirectory(pathName);
    }
}
