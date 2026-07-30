package com.danilpopov.taskmanager.presentation.controller.Dto.Requests;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "password is required")
        String password){
}
