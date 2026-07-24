package com.danilpopov.taskmanager.presentation.controller;

import com.danilpopov.taskmanager.Domain.Entity.Project;
import com.danilpopov.taskmanager.application.ProjectService;
import com.danilpopov.taskmanager.presentation.controller.Dto.AddProjectDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.ProjectUpdateDto;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.ProjectResponseDto;
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
    public ResponseEntity<ProjectResponseDto> create(
            @RequestBody AddProjectDto addProjectDto
            ) {
        return ResponseEntity.ok(
                projectService.create(addProjectDto)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> update(
            @PathVariable Long id,
            @RequestBody ProjectUpdateDto projectUpdateDto
            ) {
        return ResponseEntity.ok(
                projectService.update(id, projectUpdateDto)
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