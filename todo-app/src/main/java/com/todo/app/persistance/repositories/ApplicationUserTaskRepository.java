package com.todo.app.persistance.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.app.persistance.entitys.ApplicationUser;
import com.todo.app.persistance.entitys.ApplicationUserTask;

@Repository
public interface ApplicationUserTaskRepository extends CrudRepository<ApplicationUserTask, UUID>{
    List<ApplicationUserTask> findByUser(ApplicationUser user);

}
