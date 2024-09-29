package com.multitenency.multitenencyproject.controllers;

import com.multitenency.multitenencyproject.entities.Task;
import com.multitenency.multitenencyproject.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}
