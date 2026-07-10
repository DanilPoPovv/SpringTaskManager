package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Task;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.TaskRepository;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.infrastructure.interfaces.BaseRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddTaskDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.UpdateTaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task getById(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Task not found"));
    }
    public List<Task> getAllTask(){
        return taskRepository.getAll();
    }
    public Task addTask(AddTaskDto addTaskDto){

        Task task = new Task();

        if(addTaskDto.taskName() != null){
            task.setName(addTaskDto.taskName());
        }

        if(addTaskDto.creatorId() != null){

            User creator = userRepository.findById(addTaskDto.creatorId())
                    .orElseThrow(() -> new RuntimeException("Creator not found"));

            task.setCreator(creator);
        }

        return taskRepository.save(task);
    }
    public Task updateTask(Long taskId, UpdateTaskDto updateTaskDto){
        Task task = new Task();
        if(taskId != null){
            task = taskRepository.findById(taskId).
                    orElseThrow(() -> new RuntimeException("Task not found"));
        }
        if(updateTaskDto.taskName() != null){
            task.setName(updateTaskDto.taskName());
        }
        if(updateTaskDto.creatorId() != null){
            var user = userRepository.findById(updateTaskDto.creatorId()).
            orElseThrow(() -> new RuntimeException("creator not found"));
            task.setCreator(user);
        }
        return taskRepository.update(task);
    }
    public void deleteTask(Long taskId){
        var task = taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}
