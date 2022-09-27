package com.oreilly.quest.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Quest {
    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "Quests must have a name")
    private String name;

    @Version  // for optimistic locking
    private long version;

    @OneToMany(mappedBy = "quest", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // don't follow this when serializing
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "quest", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("knight-quest")
    private Set<Knight> knights = new HashSet<>();

    public Quest() {
    }

    public Quest(String name) {
        this.name = name;
    }

    public Quest addToTasks(Task task) {
        tasks.add(task);
        task.setQuest(this);
        return this;
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setQuest(null);
    }

    public Quest addToKnights(Knight knight) {
        knights.add(knight);
        knight.setQuest(this);
        return this;
    }

    public void removeKnight(Knight knight) {
        knights.remove(knight);
        knight.setQuest(null);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Knight> getKnights() {
        return knights;
    }

    public void setKnights(Set<Knight> knights) {
        this.knights = knights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return Objects.equals(id, quest.id) && Objects.equals(name, quest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
