package com.example.daybookspring.repository;

import com.example.daybookspring.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDayOfWeek(int dayOfWeek);
}