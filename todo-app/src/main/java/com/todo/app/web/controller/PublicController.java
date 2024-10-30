package com.todo.app.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.todo.app.persistance.entitys.ApplicationUserDTO;
import com.todo.app.persistance.services.ApplicationUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;




@Controller
public class PublicController implements WebMvcConfigurer{
    
    protected final Logger logger = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    protected ApplicationUserService applicationUserService;

    @GetMapping("/signup")
    public String getSignup(ApplicationUserDTO applicationUserDTO) {
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@Valid ApplicationUserDTO applicationUserDTO, BindingResult result, Model model) {

        // Form Validation Errors
        if(result.hasErrors()){
            ObjectError objectError = new ObjectError("confirmPassword", "Las contraseñas no coinciden");
            result.addError(objectError);
            return "signup";
        }

        if( !applicationUserService.isUserNameAvailable(applicationUserDTO.getUserName()) ) {
            logger.info(">>>> UserName Not Available");
            ObjectError objectError = new ObjectError("globalUserNameNotAvailable", "Nombre de usuario no disponible");
            result.addError(objectError);
            return "signup";
        }

        if( !applicationUserService.save(applicationUserDTO) ){ 
            ObjectError objectError = new ObjectError("globalUserNotSaved", "Usuario no guardado");
            result.addError(objectError);
            return "signup";
        }

        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String getLogin() {
        logger.info(">>>>>Getting Login template");
        return "login";
    }


    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    @GetMapping("/logout")
    public String postMethodName(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
        return "redirect:/";
    }
    
    
    
}
