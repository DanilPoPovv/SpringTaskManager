package com.danilpopov.taskmanager.presentation.controller.Dto;

import jakarta.validation.constraints.NotBlank;

public record AddTaskDto(
        @NotBlank(message = "Task name is required")
        String taskName
) {
}