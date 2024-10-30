package com.todo.app.persistance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.app.persistance.entitys.ApplicationUser;
import com.todo.app.persistance.entitys.ApplicationUserTask;
import com.todo.app.persistance.repositories.ApplicationUserTaskRepository;

@Service
public class ApplicationUserTaskService {
    @Autowired
    protected ApplicationUserTaskRepository applicationUserTaskRepository;
    
    public List<ApplicationUserTask> getUserTasks(ApplicationUser applicationUser) {
        return this.applicationUserTaskRepository.findByUser(applicationUser);
    }

    public List<ApplicationUserTask> getTasks() {
        return (List<ApplicationUserTask>) this.applicationUserTaskRepository.findAll();
    }

    public void saveTask(ApplicationUserTask applicationUserTask) {
        this.applicationUserTaskRepository.save(applicationUserTask);
    }
}
