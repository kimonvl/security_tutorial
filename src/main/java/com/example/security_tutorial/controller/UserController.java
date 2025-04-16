package com.example.security_tutorial.controller;

import com.example.security_tutorial.model.User;
import com.example.security_tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return this.service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return this.service.verify(user);
    }
}
