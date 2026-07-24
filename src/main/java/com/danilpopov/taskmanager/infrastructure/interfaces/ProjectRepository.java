package com.danilpopov.taskmanager.infrastructure.interfaces;

import com.danilpopov.taskmanager.Domain.Entity.Project;

public interface ProjectRepository extends BaseRepository<Project, Long>{
    public boolean isProjectExists(String projectName);
}
