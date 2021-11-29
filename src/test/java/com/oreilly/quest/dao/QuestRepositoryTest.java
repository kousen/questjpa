package com.oreilly.quest.dao;

import com.oreilly.quest.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class QuestRepositoryTest {
    @Autowired
    private QuestRepository questRepository;

    private Quest quest;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp(@Autowired QuestRepository qr) {
        quest = new Quest("Seek the Grail");
        quest.addToTasks(new Task("Answer the bridgekeeper"))
                .addToTasks(new Task("Bring out your dead"))
                .addToTasks(new Task("Defeat the Black Knight"));
        quest = qr.save(quest);
    }

    @Test
    void defaultQuestAndTasks() {
        Optional<Quest> optionalQuest = questRepository.findByName("Seek the Grail");
        assertTrue(optionalQuest.isPresent());
        Quest quest = optionalQuest.get();
        Set<Task> tasks = quest.getTasks();
        assertEquals(3, tasks.size());
        tasks.forEach(task -> assertEquals(quest.getName(), task.getQuest().getName()));
    }

    @Test
    void changeNameOfQuest() {
        Optional<Quest> optionalQuest = questRepository.findByName("Seek the Grail");
        assertTrue(optionalQuest.isPresent());
        Quest quest = optionalQuest.get();
        quest.setName("Seek the Holy Grail");
        entityManager.flush();
    }

    @Test
    void cascadeDeleteWorks(@Autowired TaskRepository taskRepository) {
        assertEquals(1, questRepository.count());
        Optional<Quest> optionalQuest = questRepository.findByName("Seek the Grail");
        assertTrue(optionalQuest.isPresent());
        Quest quest = optionalQuest.get();
        assertEquals(3, taskRepository.count());
        questRepository.delete(quest);
        assertEquals(0, taskRepository.count());
    }

    @Test
    void eagerFetchOfTasksWithQuest() {
        Optional<Quest> optionalQuest = questRepository.findQuestById(quest.getId());
        assertTrue(optionalQuest.isPresent());
        assertEquals(3, quest.getTasks().size());
    }
}