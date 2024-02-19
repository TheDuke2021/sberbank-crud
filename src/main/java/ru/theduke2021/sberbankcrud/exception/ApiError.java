package ru.theduke2021.sberbankcrud.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private final int status;
    private final LocalDateTime timestamp;
    private final String message;

    public ApiError(int status, Exception exception) {
        this(status, exception.getMessage());
    }

    public ApiError(int status, String message) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

}
