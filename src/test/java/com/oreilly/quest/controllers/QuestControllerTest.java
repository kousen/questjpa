package com.oreilly.quest.controllers;

import com.oreilly.quest.entities.Quest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    void getAllQuests() {
        ResponseEntity<Quest[]> entity = template.getForEntity("/quest", Quest[].class);
        Quest[] quests = entity.getBody();
        assertEquals(1, quests.length);
        Quest quest = quests[0];
        System.out.println(quest);
        System.out.println(quest.getTasks());
        System.out.println(quest.getKnights());
    }
}