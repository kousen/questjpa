package com.oreilly.quest.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskTest {

    @Autowired
    private Validator validator;

    @Test
    void getDuration() {
        Task t = new Task();
        t.setEndDate(t.getStartDate().plusDays(2));
        assertEquals(2, t.getDuration());
    }

    @Test
    void rangeValidation() {
        Task t = new Task("name");
        t.setPriority(0);
        Set<ConstraintViolation<Task>> violations = validator.validate(t);
        assertEquals(1, violations.size());
        System.out.println(violations.iterator().next().getMessage());
    }
}