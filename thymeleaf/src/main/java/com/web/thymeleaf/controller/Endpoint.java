package com.web.thymeleaf.controller;

import com.web.thymeleaf.persistance.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class Endpoint {
    @Autowired
    Users users;
    @GetMapping("/list-users")
    public String list_users (Model model) {
        model.addAttribute("users", users.getUsers());
        return "users";
    }

    @GetMapping("/list-users/{user_id}")
    public String list_user(@PathVariable String user_id) {
        return "user";
    }
}
