package com.danilpopov.taskmanager.Domain;

import jakarta.persistence.*;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
}
