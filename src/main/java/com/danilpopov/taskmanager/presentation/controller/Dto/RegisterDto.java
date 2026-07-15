package com.danilpopov.taskmanager.presentation.controller.Dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto (
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "password is required")
        String password){
}
