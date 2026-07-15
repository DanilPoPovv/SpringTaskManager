package com.danilpopov.taskmanager.presentation.controller.Dto.Response;

import com.danilpopov.taskmanager.Domain.Entity.Role;

public record UserResponseDto (String username, Role role){
}
