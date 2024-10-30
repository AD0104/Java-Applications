package com.todo.app.persistance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.app.persistance.entitys.ApplicationUser;
import com.todo.app.persistance.entitys.ApplicationUserDTO;
import com.todo.app.persistance.repositories.ApplicationUserRepository;

@Service
public class ApplicationUserService {

    @Autowired
    protected ApplicationUserRepository applicationUserRepository;
    @Autowired
    protected PasswordEncoder encoder;

    public boolean isUserNameAvailable(String userName) {
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findByUserName(userName);
        return !applicationUser.isPresent();
    }

    public boolean save(ApplicationUserDTO applicationUserDTO) {
        ApplicationUser applicationUser = new ApplicationUser(applicationUserDTO.getUserName(), encoder.encode(applicationUserDTO.getUserPassword()), "USER");
        return applicationUserRepository.save(applicationUser) == null ? false : true;
    }

    public ApplicationUser getUserByUserName(String username){ 
        Optional<ApplicationUser> user = this.applicationUserRepository.findByUserName(username);
        return user.isPresent() ? user.get() : null;
    }
}
