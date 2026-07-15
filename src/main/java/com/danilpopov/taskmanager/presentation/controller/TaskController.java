package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.Task;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.application.TaskService;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddTaskDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskResponseDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.UpdateTaskDto;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("tasks/{taskId}")
    public TaskResponseDto getById(@PathVariable Long taskId)
    {
        return taskService.getById(taskId);
    }
    @GetMapping("tasks")
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }
    @PostMapping("tasks")
    public TaskResponseDto createTask(@AuthenticationPrincipal User user, @Valid @RequestBody AddTaskDto addTaskDto){
        return taskService.addTask(user.getId(),addTaskDto);
    }
    @PutMapping("tasks/{taskId}")
    public TaskResponseDto updateTask(@PathVariable Long taskId, @RequestBody UpdateTaskDto updateTaskDto){
        return taskService.updateTask(taskId, updateTaskDto);
    }
    @DeleteMapping("tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
}
