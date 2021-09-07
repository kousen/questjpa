package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Castle;
import com.oreilly.quest.entities.Knight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CastleRepositoryTest {

    @Autowired
    private CastleRepository repository;

    @Test
    void camelot() {
        assertEquals(1, repository.count());
        Castle camelot = repository.findByName("Camelot");
        Set<Knight> knights = camelot.getKnights();
        System.out.println("Castle " + camelot.getName() +
                " has " + knights.size() + " knights");
    }
}