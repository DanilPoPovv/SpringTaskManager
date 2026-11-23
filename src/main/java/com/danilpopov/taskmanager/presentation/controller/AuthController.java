package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.application.AuthService;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.LoginRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.RefreshAccessTokenRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.RegisterRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.RefreshAccessTokenResponse;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.UserLoginResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserLoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.username(),loginRequest.password());
    }
    @PostMapping("/refreshAccessToken")
    public RefreshAccessTokenResponse refreshAccessToken(@Valid @RequestBody RefreshAccessTokenRequest refreshAccessTokenRequest) {
        return authService.refreshAccessToken(refreshAccessTokenRequest);
    }
    @PostMapping("/register")
    public UserLoginResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
