package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.Task;
import com.danilpopov.taskmanager.application.TaskService;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddTaskDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.UpdateTaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/v1")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("tasks/{taskId}")
    public Task getById(@PathVariable Long taskId){
        return taskService.getById(taskId);
    }
    @GetMapping("tasks")
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }
    @PostMapping("tasks")
    public Task createTask(@RequestBody AddTaskDto addTaskDto){
        return taskService.addTask(addTaskDto);
    }
    @PutMapping("tasks/{taskId}")
    public Task updateTask(Long taskId, UpdateTaskDto updateTaskDto){
        return taskService.updateTask(taskId, updateTaskDto);
    }
    @DeleteMapping("tasks/{taskId}")
    public void deleteTask(Long taskId){
        taskService.deleteTask(taskId);
    }
}
