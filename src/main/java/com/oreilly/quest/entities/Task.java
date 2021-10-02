package com.oreilly.quest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "Tasks must have a name")
    private String name;

    @Range(min = 1, max = 5,
        message = "${validatedValue} must be between {min} and {max}, inclusive")
    private int priority = 3;

    @FutureOrPresent
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate = LocalDate.now();
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Quest quest;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Transient
    private Duration duration;

    public long getDuration() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return priority == task.priority && completed == task.completed && Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(startDate, task.startDate) && Objects.equals(endDate, task.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, priority, startDate, endDate, completed);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", completed=" + completed +
                '}';
    }
}
