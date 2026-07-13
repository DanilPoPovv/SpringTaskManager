package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.application.AuthService;
import com.danilpopov.taskmanager.presentation.controller.Dto.LoginDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("auth/login")
    public String login(LoginDto loginDto){
        return authService.login(loginDto);
    }
}
