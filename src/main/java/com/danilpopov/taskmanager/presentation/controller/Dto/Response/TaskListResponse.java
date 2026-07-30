package com.danilpopov.taskmanager.presentation.controller.Dto.Response;

import com.danilpopov.taskmanager.Domain.Entity.TaskPriority;
import com.danilpopov.taskmanager.Domain.Entity.TaskStatus;

public record TaskListResponse(
        Long id,
        String name,
        TaskPriority priority,
        TaskStatus status,

        Long projectId,
        String projectName,

        Long creatorId,
        String creatorName,

        Long assigneeId,
        String assigneeName
) {}