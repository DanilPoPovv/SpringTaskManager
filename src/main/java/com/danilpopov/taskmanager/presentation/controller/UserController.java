package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.application.UserService;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.AddUserRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.UserResponse;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.UpdateUserRequest;
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
    public User createUser(@AuthenticationPrincipal User user,@RequestBody AddUserRequest addUserRequest) {
        return userService.createUser(user.getId(), addUserRequest);
    }
    @PutMapping("user/{userId}")
    public UserResponse updateUser(@AuthenticationPrincipal User user, @RequestBody UpdateUserRequest updateUserRequest){
        return userService.updateUser(user.getId(), updateUserRequest);
    }
    @DeleteMapping("user/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
