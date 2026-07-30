package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.application.TaskService;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.AddTaskRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.Pagination;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskListResponse;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskResponse;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.UpdateTaskRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("tasks/{taskId}")
    public TaskResponse getById(@PathVariable Long taskId)
    {
        return taskService.getById(taskId);
    }
    @GetMapping("/tasks")
    public Pagination<TaskListResponse> getTasks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {
        return taskService.getPaginationTask(page,size);
    }
    @PostMapping("tasks")
    public TaskResponse createTask(@AuthenticationPrincipal User user, @Valid @RequestBody AddTaskRequest addTaskRequest){
        return taskService.addTask(user.getId(), addTaskRequest);
    }
    @PutMapping("tasks/{taskId}")
    public TaskResponse updateTask(@PathVariable Long taskId, @RequestBody UpdateTaskRequest updateTaskRequest){
        return taskService.updateTask(taskId, updateTaskRequest);
    }
    @DeleteMapping("tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
}
