package com.oreilly.quest.services;

import com.oreilly.quest.dao.QuestRepository;
import com.oreilly.quest.entities.Quest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuestService {
    private QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public Quest findByName(String name) {
        return questRepository.findByName(name);
    }
}
