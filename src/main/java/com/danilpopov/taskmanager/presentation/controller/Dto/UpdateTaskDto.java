package com.danilpopov.taskmanager.presentation.controller.Dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskDto(
        String taskName,
        Long creatorId
) {
}