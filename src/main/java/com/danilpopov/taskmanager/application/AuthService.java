package com.danilpopov.taskmanager.application;


import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.LoginDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(LoginDto loginDto){
        User user = userRepository.findByUsername(loginDto.username()).orElseThrow(
                () -> new RuntimeException("User not found"));
        boolean passwordMatch = passwordEncoder.matches(loginDto.password(),user.getPasswordHash());
        if(!passwordMatch){
            throw new RuntimeException("Password incorrect");
        }
        return jwtService.generateJwt(user);
    }
}
