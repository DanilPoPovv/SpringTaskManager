package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.application.AuthService;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.LoginRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.RegisterRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("auth/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
    @PostMapping("auth/register")
    public LoginResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}
