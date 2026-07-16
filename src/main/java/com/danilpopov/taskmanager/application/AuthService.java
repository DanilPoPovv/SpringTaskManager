package com.danilpopov.taskmanager.application;


import com.danilpopov.taskmanager.Domain.Entity.Role;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.LoginDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.RegisterDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.LoginResponse;
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

    public LoginResponse login(LoginDto loginDto){
        User user = userRepository.findByUsername(loginDto.username())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if(!passwordEncoder.matches(loginDto.password(), user.getPasswordHash())){
            throw new RuntimeException("Invalid username or password");
        }

        return new LoginResponse(jwtService.generateJwt(user));
    }
    public LoginResponse register(RegisterDto registerDto) {
        if(userRepository.isUserExists(registerDto.username())){
            throw new RuntimeException("User already exists");
        }
        var user = new User();
        user.setUsername(registerDto.username());
        user.setPasswordHash(passwordEncoder.encode(registerDto.password()));
        user.setRole(Role.USER);
        user = userRepository.save(user);
        return new LoginResponse(jwtService.generateJwt(user));
    }
}
