package com.danilpopov.taskmanager.presentation.controller.Dto.Requests;

import jakarta.validation.constraints.NotBlank;

public record AddProjectRequest(@NotBlank(message = "project Name is required ") String projectName) {
}