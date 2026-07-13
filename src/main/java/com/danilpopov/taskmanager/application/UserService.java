package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddUserDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.UpdateUserDto;
import jakarta.transaction.Transactional;
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

    public User createUser(AddUserDto addUserDto){
        if(addUserDto.password() == null || addUserDto.username() == null) {
            throw new IllegalArgumentException("Incorrect data");
        }
        var user = new User();
        user.setUsername(addUserDto.username());
        user.setPasswordHash(passwordEncoder.encode(addUserDto.password()));
        return userRepository.save(user);
    }
    public User updateUser(Long userId, UpdateUserDto updateUserDto){
        if(updateUserDto.username() == null && updateUserDto.newPassword() == null){
            throw new IllegalArgumentException("Incorrect data");
        }
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        if(updateUserDto.username() != null){
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
        return user;
    }
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
