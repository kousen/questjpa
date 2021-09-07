package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest,Long> {
    Quest findByName(String name);
}
