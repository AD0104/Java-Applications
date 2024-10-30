package com.todo.app.persistance.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.app.persistance.entitys.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, UUID>{
    Optional<ApplicationUser> findByUserName(String userName);
}
