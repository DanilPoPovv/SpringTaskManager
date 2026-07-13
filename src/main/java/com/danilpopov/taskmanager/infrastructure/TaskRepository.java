package com.danilpopov.taskmanager.infrastructure;

import com.danilpopov.taskmanager.Domain.Entity.Task;
import com.danilpopov.taskmanager.infrastructure.interfaces.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository implements BaseRepository<Task,Long> {
    @PersistenceContext
    private final EntityManager entityManager;

    public TaskRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(
                entityManager.find(Task.class, id)
        );
    }

    @Override
    public Task save(Task task) {
        if(task == null){
            throw new IllegalArgumentException("Task is null");
        }

        entityManager.persist(task);
        return task;
    }

    @Override
    public Task update(Task task) {
        return entityManager.merge(task);
    }

    @Override
    public void delete(Task task) {
        if(task == null){
            throw new IllegalArgumentException("Task is null");
        }
        entityManager.remove(entityManager.contains(task)
                        ? task
                        : entityManager.merge(task));
    }

    @Override
    public List<Task> getAll() {
        return entityManager.createQuery(
                "select t from Task t",
                Task.class
        ).getResultList();
    }
}
