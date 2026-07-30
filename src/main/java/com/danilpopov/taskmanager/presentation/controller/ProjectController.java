package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.Project;
import com.danilpopov.taskmanager.application.ProjectService;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.AddProjectRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Requests.ProjectUpdateRequest;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.ProjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(
                projectService.getAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                projectService.getById(id)
        );
    }


    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @RequestBody AddProjectRequest addProjectRequest
            ) {
        return ResponseEntity.ok(
                projectService.create(addProjectRequest)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(
            @PathVariable Long id,
            @RequestBody ProjectUpdateRequest projectUpdateRequest
            ) {
        return ResponseEntity.ok(
                projectService.update(id, projectUpdateRequest)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        projectService.delete(id);

        return ResponseEntity.noContent().build();
    }
}