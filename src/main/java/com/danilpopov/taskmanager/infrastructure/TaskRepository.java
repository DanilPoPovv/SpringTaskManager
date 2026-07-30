package com.danilpopov.taskmanager.infrastructure;

import com.danilpopov.taskmanager.Domain.Entity.Task;
import com.danilpopov.taskmanager.infrastructure.interfaces.BaseRepository;
import com.danilpopov.taskmanager.infrastructure.interfaces.PageableRepository;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.Pagination;
import com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskListResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository implements com.danilpopov.taskmanager.infrastructure.interfaces.TaskRepository, PageableRepository<TaskListResponse> {
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

    @Override
    public List<TaskListResponse> findPage(int offset, int limit) {
         return entityManager.createQuery("""
                SELECT NEW com.danilpopov.taskmanager.presentation.controller.Dto.Response.TaskListResponse(
                    t.id,
                    t.name,
                    t.priority,
                    t.status,
                    p.id,
                    p.name,
                    c.id,
                    c.username,
                    a.id,
                    a.username
                )
                FROM Task t
                JOIN t.project p
                LEFT JOIN t.assignee a
                JOIN t.creator c
                order by t.id
                """, TaskListResponse.class).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    @Override
    public Long count() {
        return (Long)entityManager.createQuery("""
             select COUNT(t) FROM Task t""").getSingleResult();
    }

    @Override
    public boolean existsByNameAndProjectId(String taskName, Long projectId) {
        return !entityManager.createQuery("""
            SELECT 1 FROM Task t
            WHERE t.name = :taskName AND t.project.id = :projectId""")
                .setMaxResults(1)
                .setParameter("taskName", taskName)
                .setParameter("projectId", projectId)
                .getResultList().isEmpty();
    }
}
