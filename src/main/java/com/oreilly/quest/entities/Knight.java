package com.oreilly.quest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Knight {
    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Title title;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("knight-quest")
    private Quest quest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("knight-castle")
    private Castle castle;

    public Knight() {
    }

    public Knight(Title title, String name) {
        this.title = title;
        this.name = name;
    }

    public Knight(Title title, String name, Quest quest) {
        this.title = title;
        this.name = name;
        this.quest = quest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return Objects.equals(id, knight.id) && title == knight.title && Objects.equals(name, knight.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, name);
    }

    @Override
    public String toString() {
        return title + " " + name;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }
}
