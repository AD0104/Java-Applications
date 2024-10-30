package com.todo.app.web.configurations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.app.persistance.entitys.ApplicationUser;
import com.todo.app.persistance.repositories.ApplicationUserRepository;

@Service
public class UserSecurityDetails implements UserDetailsService{
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findByUserName(username);
        if( applicationUser.isPresent() )
            return User.builder()
                .username(applicationUser.get().getUserName())
                .password(applicationUser.get().getUserPassword())
                .roles( getRoles(applicationUser.get()) )
                .build();
        else
            throw new UnsupportedOperationException("Username not found");
    }

    private String[] getRoles(ApplicationUser applicationUser) {
        return applicationUser.getRoles() == null ? new String[]{"USER"} : applicationUser.getRoles().split(",");
    }
    
}
