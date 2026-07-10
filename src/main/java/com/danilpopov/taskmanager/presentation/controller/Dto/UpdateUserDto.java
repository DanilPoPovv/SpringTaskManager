package com.danilpopov.taskmanager.presentation.controller.Dto;

public record UpdateUserDto(
        String username,
        String oldPassword,
        String newPassword
) {
}