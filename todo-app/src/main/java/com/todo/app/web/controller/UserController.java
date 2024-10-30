package com.todo.app.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.todo.app.persistance.entitys.ApplicationUser;
import com.todo.app.persistance.entitys.ApplicationUserTask;
import com.todo.app.persistance.services.ApplicationUserService;
import com.todo.app.persistance.services.ApplicationUserTaskService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    @Autowired
    ApplicationUserTaskService applicationUserTaskService;
    @Autowired
    ApplicationUserService applicationUserService;

    protected Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/user/update-task/{taskId}/{isFinished}")
    public ResponseEntity<String> postUpdateTask(@PathVariable("taskId") UUID taskId, @PathVariable("isFinished") boolean isFinished) {

        log.info(">>> TaskId: {}", taskId);
        log.info(">>>> isTaskFinished: {}", isFinished);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/user/update-task/{taskId}/")
    public String getMethodName(@PathVariable("taskId") UUID taskId) {
        return "Users/task";
    }
    

    @GetMapping("/user/get-tasks")
    public String getUserTasks(HttpServletRequest request, ApplicationUserTask applicationUserTask, Model model){
        //List<ApplicationUserTask> tasks = this.applicationUserTaskService.getTasks();
        List<ApplicationUserTask> tasks = this.applicationUserTaskService.getUserTasks(this.applicationUserService.getUserByUserName(request.getUserPrincipal().getName()));
        model.addAttribute("tasks", tasks);
        return "Users/tasks";
    }

    @PostMapping("/user/create-simple-task")
    public String postCreateUserTask(HttpServletRequest request, ApplicationUserTask applicationUserTask) {

        ApplicationUser user = this.applicationUserService.getUserByUserName(request.getUserPrincipal().getName());
        if(user == null) {
            log.warn("UserName: [{}] Not Found!!", request.getUserPrincipal().getName());
            return "redirect:/user/get-tasks";
        }

        applicationUserTask.setId();
        applicationUserTask.setStartDate(new Timestamp(new Date().getTime()));
        applicationUserTask.setUser(user);

        log.info(">>>>> ApplicationUserTask: {}", applicationUserTask.toString());
        
        this.applicationUserTaskService.saveTask(applicationUserTask);
        return "redirect:/user/get-tasks";
    }
    
    
}