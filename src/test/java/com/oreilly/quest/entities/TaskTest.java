package com.oreilly.quest.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskTest {
    @Test
    void getDuration() {
        Task t = new Task();
        t.setEndDate(t.getStartDate().plusDays(2));
        assertEquals(2, t.getDuration());
    }
}