package com.danilpopov.taskmanager.application;


import com.danilpopov.taskmanager.Domain.Entity.Role;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.LoginRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.RegisterRequest;
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

    public LoginResponse login(LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if(!passwordEncoder.matches(loginRequest.password(), user.getPasswordHash())){
            throw new RuntimeException("Invalid username or password");
        }

        return new LoginResponse(jwtService.generateJwt(user));
    }
    public LoginResponse register(RegisterRequest registerRequest) {
        if(userRepository.isUserExists(registerRequest.username())){
            throw new RuntimeException("User already exists");
        }
        var user = new User();
        user.setUsername(registerRequest.username());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.password()));
        user.setRole(Role.USER);
        user = userRepository.save(user);
        return new LoginResponse(jwtService.generateJwt(user));
    }
}
