package com.oreilly.quest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestApplication.class, args);
    }

}
