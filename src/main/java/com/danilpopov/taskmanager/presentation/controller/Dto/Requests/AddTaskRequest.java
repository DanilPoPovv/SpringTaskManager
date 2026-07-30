package com.danilpopov.taskmanager.presentation.controller.Dto.Requests;

import com.danilpopov.taskmanager.Domain.Entity.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddTaskRequest(
        @NotBlank(message = "Task name is required")
        String taskName,
        Long assigneeId,
        TaskPriority priority,
        @NotNull(message = "projectId is required")
        Long projectId
) {
}