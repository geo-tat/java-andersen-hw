package com.andersen.hw.model;

import com.andersen.hw.util.IdGenerator;

import java.time.LocalDateTime;

abstract public class User implements Printable {
    private String name;
    private Integer id;
    private LocalDateTime creationDate;


    public User() {
    }

    public User(String name) {
        this.id = IdGenerator.generateId();
        this.name = name;
    }

    public User(Integer userId, String name, LocalDateTime creationDate) {
        this.name = name;
        this.id = userId;
        this.creationDate = creationDate;
    }

    abstract void printRole();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
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
                ", userId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
