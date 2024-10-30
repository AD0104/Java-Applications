package com.todo.app.persistance.services;

import org.springframework.stereotype.Service;
import com.todo.app.persistance.entitys.ApplicationUserDTO;

@Service
public class ApplicationUserDTO_Service {

    public boolean passwordCoincide(ApplicationUserDTO user){
        return user.getUserPassword().equals(user.getConfirmPassword());
    }
}
