package com.danilpopov.taskmanager.infrastructure.interfaces;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BaseRepository<T,ID> {
    public Optional<T> findById(ID id);
    public T save(T t);
    public T update(T t);
    public void delete(T t);
    public List<T> getAll();
}
