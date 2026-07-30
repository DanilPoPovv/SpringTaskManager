package com.danilpopov.taskmanager.presentation.controller.Dto.Requests;

public record UpdateTaskRequest(
        String taskName,
        Long creatorId
) {
}