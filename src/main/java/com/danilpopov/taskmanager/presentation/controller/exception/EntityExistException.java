package com.danilpopov.taskmanager.presentation.controller.exception;

public class EntityExistException extends RuntimeException{
    public EntityExistException(String message) {
        super(message);
    }
}
