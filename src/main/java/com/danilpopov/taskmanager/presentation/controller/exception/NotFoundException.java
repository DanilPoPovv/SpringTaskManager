package com.danilpopov.taskmanager.presentation.controller.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException (String message) {
        super(message);
    }
}
