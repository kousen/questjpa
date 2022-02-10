package com.oreilly.quest.services;

import com.oreilly.quest.dao.KnightRepository;
import com.oreilly.quest.entities.Knight;
import com.oreilly.quest.entities.Quest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestServiceTest {
    @Autowired
    private QuestService service;

    @PersistenceContext  // used to flush the session when necessary
    private EntityManager entityManager;

    @Test
    void findAll() {
        List<Quest> quests = service.findAll();
        assertEquals(1, quests.size());
    }

    @Test
    void findByName() {
        Optional<Quest> optionalQuest = service.findByName("Seek the Grail");
        assertTrue(optionalQuest.isPresent());
        Quest quest = optionalQuest.get();
        assertAll(
                () -> assertEquals("Seek the Grail", quest.getName()),
                () -> assertEquals(1, quest.getId())
        );
    }

    @Test  // 2 queries: 1 for quest, 1 for tasks
    void findByIdWithoutTasks() {
        Optional<Quest> optionalQuest = service.findById(1L);
        assertTrue(optionalQuest.isPresent());
        Quest quest = optionalQuest.get();
        assertEquals(3, quest.getTasks().size());
        System.out.println(quest);
        System.out.println(quest.getTasks());
    }

    @Test  // 1 query retrieves both quest and tasks
    void findByIdWithTasks() {
        Optional<Quest> optionalQuest = service.findByIdWithTasks(1L);
        assertTrue(optionalQuest.isPresent());
        Quest quest = optionalQuest.get();
        assertEquals(3, quest.getTasks().size());
        System.out.println(quest);
        System.out.println(quest.getTasks());
    }

    @Test
    void delete(@Autowired KnightRepository knightRepository) {
        Optional<Quest> optionalQuest = service.findById(1L);
        assertTrue(optionalQuest.isPresent());
        Quest quest = optionalQuest.get();

        // Remove knights to avoid referential integrity violation
        Set<Knight> knights = quest.getKnights();
        knightRepository.deleteAllInBatch(knights);

        service.deleteQuest(quest);

        // have to flush the session, or won't see the deletes
        // if commented out, will pass, but no SQL deletes in console
        entityManager.flush();
    }
}