package com.danilpopov.taskmanager.presentation.controller.exception;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException (String message){
        super(message);
    }
}
