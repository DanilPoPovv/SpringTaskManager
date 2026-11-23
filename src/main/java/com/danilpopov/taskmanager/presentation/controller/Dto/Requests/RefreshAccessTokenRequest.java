package com.danilpopov.taskmanager.presentation.controller.Dto.Requests;

import jakarta.validation.constraints.NotBlank;

public record RefreshAccessTokenRequest(
        @NotBlank(message = "Access token must not be blank")
        String accessToken,

        @NotBlank(message = "Refresh token must not be blank")
        String refreshToken
) {}