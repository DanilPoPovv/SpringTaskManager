package com.danilpopov.taskmanager.infrastructure.interfaces;

import com.danilpopov.taskmanager.Domain.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends BaseRepository<User,Long>{
    public Optional<User> findByUsername(String username);
}
