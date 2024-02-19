package ru.theduke2021.sberbankcrud.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private final int status;
    private final LocalDateTime timestamp;
    private final String message;

    /**
     * Creates an API error, obtaining error message from the given {@code exception} via {@link Exception#getMessage()}.
     * @param status HTTP status code
     * @param exception Exception whose message acquired by {@link Exception#getMessage()} will
     *                 be used as this {@code ApiError} {@link #message}
     */
    public ApiError(int status, Exception exception) {
        this(status, exception.getMessage());
    }

    /**
     * Creates an API error with the given HTTP status code and error {@code message}.
     * @param status HTTP status code
     * @param message Message to be included in the {@code ApiError}
     */
    public ApiError(int status, String message) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

}
