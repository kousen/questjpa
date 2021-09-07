package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Knight;
import com.oreilly.quest.entities.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class KnightRepositoryTest {
    @Autowired
    private KnightRepository repository;

    @Test
    void sorting() {
        Sort.TypedSort<Knight> knightSort = Sort.sort(Knight.class);
        Sort sort = knightSort.by(Knight::getTitle).ascending()
                .and(knightSort.by(Knight::getName).descending());
        List<Knight> knights = repository.findAll(sort);
        knights.forEach(System.out::println);
    }

    @Test
    void findWithEntityGraph() {
        List<Knight> knights = repository.findWithGraphByTitle(Title.SIR);
        knights.forEach(System.out::println);
    }
}