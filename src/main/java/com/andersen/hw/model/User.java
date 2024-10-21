package com.andersen.hw.model;

import com.andersen.hw.util.IdGenerator;

import java.time.LocalDateTime;

abstract public class User implements Identifiable, Printable {
    private String name;
    private Long userId;
    private LocalDateTime creationDate;
    private int classId;

    public User() {
        this.classId = IdGenerator.generateId();
    }

    public User(String name) {
        this.name = name;
    }

    public User(Long userId, String name, LocalDateTime creationDate) {
        this.name = name;
        this.userId = userId;
        this.creationDate = creationDate;
    }

    @Override
    public int getId() {
        return classId;
    }

    abstract void printRole();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "creationDate=" + creationDate +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
