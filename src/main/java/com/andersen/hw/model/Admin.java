package com.andersen.hw.model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(String name) {
        super(name);
    }

    public Admin(Integer userId, String name, LocalDateTime creationDate) {
        super(userId, name, creationDate);
    }

    @Override
    void printRole() {
        System.out.println("Role is ADMIN");
    }

    public void checkTicket(Ticket ticket) {
        System.out.println("Checking ticket...");
    }

}
