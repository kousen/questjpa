package com.oreilly.quest.services;

import com.oreilly.quest.dao.QuestRepository;
import com.oreilly.quest.entities.Quest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestService {
    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    public Quest findByName(String name) {
        return questRepository.findByName(name);
    }
}
