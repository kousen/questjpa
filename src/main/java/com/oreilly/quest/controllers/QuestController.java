package com.oreilly.quest.controllers;

import com.oreilly.quest.entities.Quest;
import com.oreilly.quest.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quest")
public class QuestController {
    private QuestService service;

    @Autowired
    public QuestController(QuestService service) {
        this.service = service;
    }

    @GetMapping
    public Quest getQuestByName(
            @RequestParam(defaultValue = "Seek the Grail",
                    required = false) String name) {
        return service.findByName(name);
    }
}
