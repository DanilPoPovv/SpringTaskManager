package com.danilpopov.taskmanager.presentation.controller.Dto.Requests;

public record UpdateUserRequest(
        String username,
        String oldPassword,
        String newPassword
) {
}