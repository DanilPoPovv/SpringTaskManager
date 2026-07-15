package com.danilpopov.taskmanager.presentation.controller.Dto;

import com.danilpopov.taskmanager.Domain.Entity.Role;
import jakarta.validation.constraints.NotBlank;
import org.aspectj.bridge.IMessage;

public record AddUserDto(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Role is required")
        Role role
) {
}