package com.danilpopov.taskmanager.presentation.controller.Dto;

public class UpdateTaskDto {
    private String taskName;
    private Long creatorId;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
