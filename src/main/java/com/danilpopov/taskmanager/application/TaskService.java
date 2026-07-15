package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Task;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.TaskRepository;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddTaskDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskResponseDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.UpdateTaskDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponseDto getById(Long taskId){
        var task = taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Task not found"));
        return new TaskResponseDto(task.getId(),task.getName(), task.getCreator().getId());
    }
    public List<Task> getAllTask(){
        return taskRepository.getAll();
    }
    public TaskResponseDto addTask(Long creatorId, @Valid AddTaskDto addTaskDto){

        Task task = new Task();
        task.setName(addTaskDto.taskName());

        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        task.setCreator(creator);
        task = taskRepository.save(task);
        return new TaskResponseDto(task.getId(),task.getName(),task.getCreator().getId());
    }
    public TaskResponseDto updateTask(Long taskId, UpdateTaskDto updateTaskDto){

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if(updateTaskDto.taskName() != null){
            task.setName(updateTaskDto.taskName());
        }

        if(updateTaskDto.creatorId() != null){
            User user = userRepository.findById(updateTaskDto.creatorId())
                    .orElseThrow(() -> new RuntimeException("Creator not found"));

            task.setCreator(user);
        }
        taskRepository.update(task);
        return new TaskResponseDto(task.getId(),task.getName(),task.getCreator().getId());
    }
    public void deleteTask(Long taskId){
        var task = taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}
