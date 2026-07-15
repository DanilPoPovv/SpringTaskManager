package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Role;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddUserDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.UserResponseDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.UpdateUserDto;
import jakarta.transaction.Transactional;
import org.springframework.expression.AccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User getById(Long userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(Long userCreatorId,AddUserDto addUserDto){
        var userCreator = userRepository.findById(userCreatorId).orElseThrow(() -> new RuntimeException("creator not found"));
        if(userCreator.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Not enough rights");
        }
        if(userRepository.isUserExists(addUserDto.username())) {
            throw new IllegalArgumentException("User already exists");
        }
        var user = new User();
        user.setUsername(addUserDto.username());
        user.setPasswordHash(passwordEncoder.encode(addUserDto.password()));
        user.setRole(addUserDto.role());
        return userRepository.save(user);
    }
    public UserResponseDto updateUser(Long userId, UpdateUserDto updateUserDto){
        if(updateUserDto.username() == null && updateUserDto.newPassword() == null){
            throw new IllegalArgumentException("Incorrect data");
        }
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        if(updateUserDto.username() != null){
            if(userRepository.isUserExists(updateUserDto.username())){
                throw new IllegalArgumentException("User already exists");
            }
            user.setUsername(updateUserDto.username());
        }
        if(updateUserDto.oldPassword() != null && updateUserDto.newPassword() != null) {
            var passwordCorrect = passwordEncoder.
                    matches(updateUserDto.oldPassword(),user.getPasswordHash());
            if(!passwordCorrect) {
                throw new IllegalArgumentException("Old password incorrect");
            }
            user.setPasswordHash(passwordEncoder.encode(updateUserDto.newPassword()));
        }
        return new UserResponseDto(user.getUsername(),user.getRole());
    }
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
