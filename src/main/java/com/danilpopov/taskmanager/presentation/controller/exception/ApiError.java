package com.danilpopov.taskmanager.presentation.controller.exception;

public record ApiError(String message, int status) {
}
