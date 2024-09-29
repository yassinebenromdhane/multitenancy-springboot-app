package com.multitenency.multitenencyproject.repositories;

import com.multitenency.multitenencyproject.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}