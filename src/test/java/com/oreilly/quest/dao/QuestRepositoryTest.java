package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Quest;
import com.oreilly.quest.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestRepositoryTest {
    @Autowired
    private QuestRepository questRepository;

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
        Optional<Quest> optionalQuest = questRepository.findQuestById(1L);
        assertTrue(optionalQuest.isPresent());
    }
}