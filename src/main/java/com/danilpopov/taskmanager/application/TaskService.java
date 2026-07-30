package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Project;
import com.danilpopov.taskmanager.Domain.Entity.Task;
import com.danilpopov.taskmanager.Domain.Entity.TaskStatus;
import com.danilpopov.taskmanager.Domain.Entity.User;
import com.danilpopov.taskmanager.infrastructure.ProjectRepository;
import com.danilpopov.taskmanager.infrastructure.TaskRepository;
import com.danilpopov.taskmanager.infrastructure.UserRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.AddTaskRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.Pagination;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskListResponse;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskResponse;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.UpdateTaskRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }


    public TaskResponse getById(Long taskId){
        var task = taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Task not found"));
        return new TaskResponse(task.getId(),task.getName(), task.getCreator().getId());
    }
    public Pagination<TaskListResponse> getPaginationTask(Integer page, Integer pageSize){
        List<TaskListResponse> taskList = taskRepository.findPage(page * pageSize,pageSize);
        Long taskCount = taskRepository.count();
        Integer pageCount = (int) Math.ceil((double) taskCount / pageSize);
        return new Pagination<>(taskList, pageCount, taskCount);
    }

    public TaskResponse addTask(Long creatorId, AddTaskRequest addTaskRequest){
        Task task = new Task();
        if(addTaskRequest.assigneeId() != null) {
            var user = userRepository.findById(addTaskRequest.assigneeId()).
                    orElseThrow(() -> new RuntimeException("assignee not found"));
            task.setAssignee(user);
        }
        Project project = projectRepository.findById(addTaskRequest.projectId()).orElseThrow(() -> new RuntimeException("project not found"));
        task.setProject(project);
        task.setName(addTaskRequest.taskName());

        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        task.setCreator(creator);
        task.setPriority(addTaskRequest.priority());
        task = taskRepository.save(task);
        task.setStatus(TaskStatus.TODO);
        return new TaskResponse(task.getId(),task.getName(),task.getCreator().getId());
    }
    public TaskResponse updateTask(Long taskId, UpdateTaskRequest updateTaskRequest){

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if(updateTaskRequest.taskName() != null){
            task.setName(updateTaskRequest.taskName());
        }

        if(updateTaskRequest.creatorId() != null){
            User user = userRepository.findById(updateTaskRequest.creatorId())
                    .orElseThrow(() -> new RuntimeException("Creator not found"));

            task.setCreator(user);
        }
        taskRepository.update(task);
        return new TaskResponse(task.getId(),task.getName(),task.getCreator().getId());
    }
    public void deleteTask(Long taskId){
        var task = taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}
