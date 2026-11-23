package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Role;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.RefreshAccessTokenRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.RegisterRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.RefreshAccessTokenResponse;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.UserLoginResponse;
import com.danilpopov.taskmanager.presentation.controller.exception.AccessDeniedException;
import com.danilpopov.taskmanager.presentation.controller.exception.EntityExistException;
import com.danilpopov.taskmanager.presentation.controller.exception.InvalidDataException;
import com.danilpopov.taskmanager.presentation.controller.exception.NotFoundException;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserLoginResponse login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new InvalidDataException("username or password is incorrect"));
        if(!passwordEncoder.matches(password,user.getPasswordHash())){
            throw new AccessDeniedException("username or password is incorrect");
        }
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getRole());
        String refreshToken = jwtService.generateRefreshToken(user.getId());

        return new UserLoginResponse(accessToken,refreshToken);
    }
    public RefreshAccessTokenResponse refreshAccessToken(RefreshAccessTokenRequest refreshAccessTokenRequest) {
        Claims claims = jwtService.verifyJwt(refreshAccessTokenRequest.refreshToken());
        User user = userRepository.findById(Long.parseLong(claims.getSubject())).orElseThrow();
        return new RefreshAccessTokenResponse(jwtService.generateAccessToken(user.getId(), user.getRole()), refreshAccessTokenRequest.refreshToken());
    }
    public UserLoginResponse register(RegisterRequest request){
        if(userRepository.isUserExists(request.username())){
            throw new EntityExistException("This username already exists");
        }
        User user = new User();
        user.setRole(Role.USER);
        user.setUsername(request.username());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);
        String accessToken = jwtService.generateAccessToken(savedUser.getId(), savedUser.getRole());
        String refreshToken = jwtService.generateRefreshToken(savedUser.getId());

        return new UserLoginResponse(accessToken,refreshToken);
    }
}
