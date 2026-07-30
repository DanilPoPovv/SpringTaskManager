package com.danilpopov.taskmanager.presentation.controller.Dto.Requests;

import com.danilpopov.taskmanager.Domain.Entity.Role;
import jakarta.validation.constraints.NotBlank;

public record AddUserRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Role is required")
        Role role
) {
}