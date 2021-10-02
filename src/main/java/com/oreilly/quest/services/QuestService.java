package com.oreilly.quest.services;

import com.oreilly.quest.dao.QuestRepository;
import com.oreilly.quest.entities.Quest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Optional<Quest> findByName(String name) {
        return questRepository.findByName(name);
    }

    public Optional<Quest> findById(Long id) {
        return questRepository.findById(id);
    }

    public Optional<Quest> findByIdWithTasks(Long id) {
        return questRepository.findQuestById(id);
    }

    public void deleteQuest(Quest quest) {
        questRepository.delete(quest);
    }
}
