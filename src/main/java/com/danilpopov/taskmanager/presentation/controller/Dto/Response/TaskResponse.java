package com.danilpopov.taskmanager.presentation.controller.Dto.Response;

public record TaskResponse(
         Long id,
         String name,
         Long creatorId
){
}
