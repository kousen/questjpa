package com.oreilly.quest.config;

import com.oreilly.quest.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// JavaConfig --> class annotated with @Configuration,
// methods annotated with @Bean
@Configuration
public class AppInit {

    // method name becomes the name of the Spring bean
    // return type is the type of the bean
    // can autowire any needed method arguments
    @Bean
    public CommandLineRunner initialize(@Autowired QuestService service) {
        // return a lambda expression compatible with CommandLineRunner interface
        return args -> service.initializeDatabase();
    }
}
