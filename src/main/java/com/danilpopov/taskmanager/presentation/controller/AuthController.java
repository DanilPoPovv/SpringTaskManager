package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.application.AuthService;
import com.danilpopov.taskmanager.presentation.controller.Dto.LoginDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.RegisterDto;
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
    public String login(@Valid @RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }
    @PostMapping("auth/register")
    public String register(@Valid @RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }
}
