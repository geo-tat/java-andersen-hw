package com.andersen.hw.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Client extends User {

    public Client(String name) {
        super(name);
    }

    public Client(Integer userId, String name, LocalDateTime creationDate) {
        super(userId, name, creationDate);
    }

    public Client() {

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
