package com.danilpopov.taskmanager.infrastructure.interfaces;

import com.danilpopov.taskmanager.Domain.Entity.Task;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskListResponse;

public interface TaskRepository extends BaseRepository<Task, Long>{
    public boolean existsByNameAndProjectId(String taskName, Long projectId);
}
