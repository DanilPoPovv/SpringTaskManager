package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Project;

import com.danilpopov.taskmanager.infrastructure.ProjectRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.AddProjectRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.ProjectUpdateRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.ProjectResponse;
import com.danilpopov.taskmanager.presentation.controller.exception.EntityExistException;
import com.danilpopov.taskmanager.presentation.controller.exception.NotFoundException;
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
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("project Not Found"));
    }
    public ProjectResponse create(AddProjectRequest addProjectRequest){
        Project project = new Project();
        if(!projectRepository.isProjectExists(addProjectRequest.projectName())){
            throw new EntityExistException("project already exists");
        }
        project.setName(addProjectRequest.projectName());
        projectRepository.save(project);
        return new ProjectResponse(project.getId(),project.getName());
    }
    public ProjectResponse update(Long id, ProjectUpdateRequest projectUpdateRequest){
        Project project = getById(id);
        if(projectUpdateRequest.projectName() != null) {
            project.setName(projectUpdateRequest.projectName());
        }
        projectRepository.update(project);
        return new ProjectResponse(project.getId(),project.getName());
    }
    public void delete(long id) {
        Project project = getById(id);
        projectRepository.delete(project);
    }
}
