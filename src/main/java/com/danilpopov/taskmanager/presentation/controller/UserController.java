package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.application.UserService;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddUserDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.UserResponseDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.UpdateUserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{userId}")
    public User getById(@PathVariable Long userId) {
        return userService.getById(userId);
    }
    @PostMapping("user")
    public User createUser(@AuthenticationPrincipal User user,@RequestBody AddUserDto addUserDto) {
        return userService.createUser(user.getId(),addUserDto);
    }
    @PutMapping("user/{userId}")
    public UserResponseDto updateUser(@AuthenticationPrincipal User user, @RequestBody UpdateUserDto updateUserDto){
        return userService.updateUser(user.getId(), updateUserDto);
    }
    @DeleteMapping("user/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
