package com.oreilly.quest.config;

import com.oreilly.quest.dao.CastleRepository;
import com.oreilly.quest.dao.QuestRepository;
import com.oreilly.quest.entities.*;
import com.oreilly.quest.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInit {

    @Bean
    public CommandLineRunner initialize(@Autowired QuestService service) {
        return args -> {
            service.initializeDatabase();
        };
    }
}
