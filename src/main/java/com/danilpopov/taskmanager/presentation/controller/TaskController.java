package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/v1")
public class TaskController {

    @GetMapping("task/getById/{taskId}")
    public Task getById(@PathVariable Long taskId){

    }
    @GetMapping("task/getAll")
    public List<Task> getAllTask(){

    }
    @PostMapping("task/add")
    public Task createTask(){

    }
    @PutMapping("task/update/{taskId}")
    public Task updateTask(){

    }
    @DeleteMapping("task/{taskId}")
    public void deleteTask(){

    }
}
