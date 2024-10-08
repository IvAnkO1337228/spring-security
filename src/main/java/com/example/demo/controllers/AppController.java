package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Application;
import com.example.demo.models.MyUser;
import  com.example.demo.services.AppService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {
    private AppService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the unprotected page";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> allApplications() {
        return service.allApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("HasAuthority('ROLE_ADMIN')")
    public Application applicationByID(@PathVariable int id) {
        return service.applicationByID(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser user) {
       service.addUser(user);
       return("User is saved");
    }


}