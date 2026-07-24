package com.danilpopov.taskmanager.Domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
}
