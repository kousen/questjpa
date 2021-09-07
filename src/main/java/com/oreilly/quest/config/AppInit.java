package com.oreilly.quest.config;

import com.oreilly.quest.dao.CastleRepository;
import com.oreilly.quest.dao.QuestRepository;
import com.oreilly.quest.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInit {

    @Bean
    public CommandLineRunner initialize(@Autowired QuestRepository qr,
                                        @Autowired CastleRepository cr) {
        return args -> {
            if (qr.count() == 0) {
                Quest quest = new Quest("Seek the Grail");
                quest.addToTasks(new Task("Answer the bridgekeeper"))
                        .addToTasks(new Task("Bring out your dead"))
                        .addToTasks(new Task("Defeat the Black Knight"));

//                quest.getTasks().add(new Task("Answer the bridgekeeper"));
//                quest.getTasks().add(new Task("Bring out your dead"));
//                quest.getTasks().add(new Task("Defeat the Black Knight"));
                qr.save(quest);

                Castle camelot = new Castle("Camelot", "Marlborough", "CT");
                camelot.addToKnights(new Knight(Title.KING, "Arthur", quest))
                        .addToKnights(new Knight(Title.SIR, "Lancelot", quest))
                        .addToKnights(new Knight(Title.SIR, "Robin", quest));
                cr.save(camelot);
            }
        };
    }
}
