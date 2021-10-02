package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Quest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestRepository extends JpaRepository<Quest,Long> {
    Optional<Quest> findByName(String name);

    // Eager fetch of tasks
    @EntityGraph(attributePaths = "tasks",
        type = EntityGraph.EntityGraphType.FETCH)
    Optional<Quest> findQuestById(Long id);
}
