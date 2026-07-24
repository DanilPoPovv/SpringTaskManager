package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Project;

import com.danilpopov.taskmanager.infrastructure.ProjectRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddProjectDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.ProjectUpdateDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.ProjectResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAll(){
        return projectRepository.getAll();
    }
    public Project getById(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("project Not Found"));
    }
    public ProjectResponseDto create(AddProjectDto addProjectDto){
        Project project = new Project();
        if(!projectRepository.isProjectExists(addProjectDto.projectName())){
            throw new RuntimeException("project already exists");
        }
        project.setName(addProjectDto.projectName());
        projectRepository.save(project);
        return new ProjectResponseDto(project.getId(),project.getName());
    }
    public ProjectResponseDto update(Long id, ProjectUpdateDto projectUpdateDto){
        Project project = getById(id);
        if(projectUpdateDto.projectName() != null) {
            project.setName(projectUpdateDto.projectName());
        }
        projectRepository.update(project);
        return new ProjectResponseDto(project.getId(),project.getName());
    }
    public void delete(long id) {
        Project project = getById(id);
        projectRepository.delete(project);
    }
}
