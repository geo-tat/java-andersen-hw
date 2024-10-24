package com.andersen.hw.model;


import java.time.LocalDateTime;

public class Client extends User {
    public Client(String name) {
        super(name);
    }

    public Client(Integer userId, String name, LocalDateTime creationDate) {
        super(userId, name, creationDate);
    }

    @Override
    void printRole() {
        System.out.println("Role is CLIENT");
    }

    public void getTicket() {
        System.out.println("Client ticket information:");
    }

    @Override
    public void print() {
        System.out.println("Class ID = " + getId());
    }

}
