package com.danilpopov.taskmanager.presentation.controller.Dto;

public record AddTaskDto(
        String taskName,
        Long creatorId
) {
}