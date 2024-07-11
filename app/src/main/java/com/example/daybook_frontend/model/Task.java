package com.example.daybook_frontend.model;

public class Task {
    private Long id;
    private String description;
    private int dayOfWeek;
    private String subject;

    // Constructor
    public Task(Long id, String description, int dayOfWeek, String subject) {
        this.id = id;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.subject = subject;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}