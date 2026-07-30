package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Role;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.AddUserRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.UserResponse;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.UpdateUserRequest;
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

    public User createUser(Long userCreatorId, AddUserRequest addUserRequest){
        var userCreator = userRepository.findById(userCreatorId).orElseThrow(() -> new RuntimeException("creator not found"));
        if(userCreator.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Not enough rights");
        }
        if(userRepository.isUserExists(addUserRequest.username())) {
            throw new RuntimeException("User already exists");
        }
        var user = new User();
        user.setUsername(addUserRequest.username());
        user.setPasswordHash(passwordEncoder.encode(addUserRequest.password()));
        user.setRole(addUserRequest.role());
        return userRepository.save(user);
    }
    public UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest){
        if(updateUserRequest.username() == null && updateUserRequest.newPassword() == null){
            throw new RuntimeException("Incorrect data");
        }
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        if(updateUserRequest.username() != null){
            if(userRepository.isUserExists(updateUserRequest.username())){
                throw new RuntimeException("User already exists");
            }
            user.setUsername(updateUserRequest.username());
        }
        if(updateUserRequest.oldPassword() != null && updateUserRequest.newPassword() != null) {
            var passwordCorrect = passwordEncoder.
                    matches(updateUserRequest.oldPassword(),user.getPasswordHash());
            if(!passwordCorrect) {
                throw new RuntimeException("Old password incorrect");
            }
            user.setPasswordHash(passwordEncoder.encode(updateUserRequest.newPassword()));
        }
        return new UserResponse(user.getUsername(),user.getRole());
    }
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
