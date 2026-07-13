package com.danilpopov.taskmanager.infrastructure;

import com.danilpopov.taskmanager.Domain.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepository implements com.danilpopov.taskmanager.infrastructure.interfaces.UserRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(
                entityManager.find(User.class, id)
        );
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(
                entityManager.contains(user)
                        ? user
                        : entityManager.merge(user)
        );
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("""
                select u
                from User u
                """, User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return entityManager.createQuery("""
                select u
                from User u 
                where u.username = :username""", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
}