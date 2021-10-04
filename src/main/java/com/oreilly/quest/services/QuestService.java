package com.oreilly.quest.services;

import com.oreilly.quest.dao.CastleRepository;
import com.oreilly.quest.dao.QuestRepository;
import com.oreilly.quest.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestService {
    private final QuestRepository questRepository;
    private final CastleRepository castleRepository;

    @Autowired
    public QuestService(QuestRepository questRepository, CastleRepository castleRepository) {
        this.questRepository = questRepository;
        this.castleRepository = castleRepository;
    }

    public void initializeDatabase() {
        if (questRepository.count() == 0) {
            Quest quest = new Quest("Seek the Grail");
            quest.addToTasks(new Task("Answer the bridgekeeper"))
                    .addToTasks(new Task("Bring out your dead"))
                    .addToTasks(new Task("Defeat the Black Knight"));
            questRepository.save(quest);

            Castle camelot = new Castle("Camelot", "Marlborough", "CT");
            camelot.addToKnights(new Knight(Title.KING, "Arthur", quest))
                    .addToKnights(new Knight(Title.SIR, "Lancelot", quest))
                    .addToKnights(new Knight(Title.SIR, "Robin", quest));
            castleRepository.save(camelot);
        }
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
