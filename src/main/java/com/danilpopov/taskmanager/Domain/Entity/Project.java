package com.danilpopov.taskmanager.Domain.Entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.awt.geom.GeneralPath;
import java.util.List;
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}
