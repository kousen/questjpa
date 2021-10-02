package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByPriorityGreaterThan(int priority);
    List<Task> findAllByPriorityLessThanAndStartDateBetween(int priority, LocalDate first, LocalDate second);

    // NOTE: when returning Streams, be sure to call using try-with-resources
    @Query("select t from Task t")  // JPQL --> Java Persistence Query Language
    Stream<Task> findAllByCustomQueryAndStream();

    Stream<Task> readAllByNameNotNull();
}
