package ru.theduke2021.sberbankcrud.config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.theduke2021.sberbankcrud.dto.ApiError;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApiExceptionHandler {

    // Handles exceptions that occur with domain objects with invalid fields
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String validationErrorMessage = ex.getAllErrors().get(0).getDefaultMessage();
        return new ApiError(BAD_REQUEST.value(), validationErrorMessage);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ApiError handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ApiError(NOT_FOUND.value(), ex);
    }

}
