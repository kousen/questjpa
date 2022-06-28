package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Quest;
import com.oreilly.quest.entities.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class QuestRepositoryTest {
    @Autowired
    private QuestRepository questRepository;

    private Quest quest;

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
        Quest retrievedQuest = optionalQuest.get();
        System.out.println(retrievedQuest);
        Set<Task> tasks = retrievedQuest.getTasks();
        System.out.println(tasks);
        assertEquals(3, tasks.size());
        tasks.forEach(task -> assertEquals(retrievedQuest.getName(), task.getQuest().getName()));
    }

    @Test
    void changeNameOfQuest() {
        Optional<Quest> optionalQuest = questRepository.findByName("Seek the Grail");
        assertTrue(optionalQuest.isPresent());
        Quest quest = optionalQuest.get();
        quest.setName("Seek the Holy Grail");
        questRepository.flush();  // without this, when tx rolls back, no changes are saved
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