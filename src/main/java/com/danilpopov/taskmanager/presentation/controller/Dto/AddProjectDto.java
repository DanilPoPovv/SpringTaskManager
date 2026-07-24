package com.danilpopov.taskmanager.presentation.controller.Dto;

import jakarta.validation.constraints.NotBlank;

public record AddProjectDto(@NotBlank(message = "project Name is required ") String projectName) {
}