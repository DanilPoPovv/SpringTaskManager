package com.danilpopov.taskmanager.presentation.controller.Dto.Response;

public record TaskResponseDto (
         Long id,
         String name,
         Long creatorId
){
}
