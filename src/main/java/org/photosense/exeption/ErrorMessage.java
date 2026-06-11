package org.photosense.exeption;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    PATH_NOT_FOUND("Path doesn't exist: %s"),
    DIRECTORY_SCAN_FAILED("Failed to scan directory: %s");

    private final String template;

    public String format(Object... args) {
        return String.format(template, args);
    }
}