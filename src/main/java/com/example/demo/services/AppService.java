package com.example.demo.services;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.demo.models.Application;
import com.example.demo.models.MyUser;
import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;
import  lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@AllArgsConstructor
public class AppService {
    private List<Application> applications;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void loadAppInDB() {
        Faker faker = new Faker();
        applications = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();
    }

    public List<Application> allApplications() {
        return applications;
    }

    public Application applicationByID(int id) {
        return applications.stream()
            .filter(app -> app.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
