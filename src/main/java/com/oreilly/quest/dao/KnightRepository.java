package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Knight;
import com.oreilly.quest.entities.Title;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnightRepository extends JpaRepository<Knight,Long> {
    @EntityGraph(attributePaths = "quest.tasks",
        type = EntityGraph.EntityGraphType.FETCH)
    List<Knight> findWithGraphByTitle(Title title);
}
