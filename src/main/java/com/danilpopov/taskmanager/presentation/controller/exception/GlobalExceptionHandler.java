package com.danilpopov.taskmanager.presentation.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex) {

        ApiError error = new ApiError(
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(EntityExistException.class)
    public ResponseEntity<ApiError> handleEntityExistException(EntityExistException ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                HttpStatus.CONFLICT.value()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(error);
    }
}