package com.danilpopov.taskmanager.infrastructure;

import com.danilpopov.taskmanager.Domain.Entity.Project;
import com.danilpopov.taskmanager.infrastructure.interfaces.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepository implements com.danilpopov.taskmanager.infrastructure.interfaces.ProjectRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public ProjectRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return Optional.ofNullable(
                entityManager.find(Project.class, id)
        );
    }


    @Override
    public Project save(Project project) {
        if(project == null){
            throw new RuntimeException("project is empty");
        }

        entityManager.persist(project);
        return project;
    }

    @Override
    public Project update(Project project) {
        return entityManager.merge(project);
    }

    @Override
    public void delete(Project project) {
        entityManager.remove(entityManager.contains(project)
                ? project
                : entityManager.merge(project));
    }

    @Override
    public List<Project> getAll() {
        return entityManager.createQuery(
                "SELECT p from Project p"
        ).getResultList();
    }

    @Override
    public boolean isProjectExists(String projectName) {
        return entityManager.createQuery("""
                SELECT 1 FROM Project p 
                WHERE p.name = :projectName """).
                setParameter("projectName",projectName)
                .getResultList()
                .isEmpty();
    }
}
