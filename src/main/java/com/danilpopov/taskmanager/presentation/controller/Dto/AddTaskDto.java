package com.danilpopov.taskmanager.presentation.controller.Dto;

public class AddTaskDto {
    public String Taskname;
    public Long creatorId;

    public String getTaskname() {
        return Taskname;
    }

    public void setTaskname(String taskname) {
        Taskname = taskname;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
