package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.application.AuthService;
import com.danilpopov.taskmanager.presentation.controller.Dto.LoginDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.RegisterDto;
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
    public LoginResponse login(@Valid @RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }
    @PostMapping("auth/register")
    public LoginResponse register(@Valid @RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }
}
