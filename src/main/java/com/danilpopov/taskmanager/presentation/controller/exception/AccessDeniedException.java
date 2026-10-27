package com.danilpopov.taskmanager.presentation.controller.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message) {
        super(message);
    }
}
