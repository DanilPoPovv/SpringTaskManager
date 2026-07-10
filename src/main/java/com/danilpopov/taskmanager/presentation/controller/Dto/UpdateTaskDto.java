package com.danilpopov.taskmanager.presentation.controller.Dto;

public record UpdateTaskDto(
        String taskName,
        Long creatorId
) {
}