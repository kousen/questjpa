package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Quest;
import com.oreilly.quest.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestRepositoryTest {
    @Autowired
    private QuestRepository questRepository;

    @Test
    void defaultQuestAndTasks() {
        Quest quest = questRepository.findByName("Seek the Grail");
        Set<Task> tasks = quest.getTasks();
        assertEquals(3, tasks.size());
        tasks.forEach(task -> assertEquals(quest.getName(), task.getQuest().getName()));
    }

    @Test
    void cascadeDeleteWorks(@Autowired TaskRepository taskRepository) {
        assertEquals(1, questRepository.count());
        Quest quest = questRepository.findByName("Seek the Grail");
        assertEquals(3, taskRepository.count());
        questRepository.delete(quest);
        assertEquals(0, taskRepository.count());
    }
}