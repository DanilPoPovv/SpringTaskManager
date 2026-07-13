package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.application.UserService;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddUserDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.UpdateUserDto;
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
    public User createUser(@RequestBody AddUserDto addUserDto) {
        return userService.createUser(addUserDto);
    }
    @PutMapping("user/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UpdateUserDto updateUserDto){
        return userService.updateUser(userId, updateUserDto);
    }
    @DeleteMapping("user/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
