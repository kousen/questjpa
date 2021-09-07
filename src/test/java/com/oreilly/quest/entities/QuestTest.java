package com.oreilly.quest.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class QuestTest {
    @Autowired
    private Validator validator;

    @Test
    void validQuest() {
        Quest quest = new Quest("Seek the Grail");
        Set<ConstraintViolation<Quest>> violations = validator.validate(quest);
        assertEquals(0, violations.size());
    }
}