package com.danilpopov.taskmanager.presentation.controller.Dto.Requests;

import jakarta.validation.constraints.NotBlank;

public record ProjectUpdateRequest(
        @NotBlank(message = "Project name must not be blank")
        String projectName
) {}