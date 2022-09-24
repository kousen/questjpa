package com.oreilly.quest.controllers;

import com.oreilly.quest.entities.Quest;
import com.oreilly.quest.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/quest")
public class QuestController {
    private final QuestService service;

    @Autowired
    public QuestController(QuestService service) {
        this.service = service;
    }

    @GetMapping("{name}")
    public ResponseEntity<Quest> getQuestByName(@PathVariable String name) {
        String questName = URLDecoder.decode(name, StandardCharsets.UTF_8);
        return ResponseEntity.of(service.findByName(questName));
    }

    @GetMapping
    public List<Quest> findAllQuests() {
        return service.findAll();
    }
}
