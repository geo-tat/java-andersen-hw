package com.andersen.hw.model;

import com.andersen.hw.enums.UserStatus;
import com.andersen.hw.util.IdGenerator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
abstract public class User implements Printable {
    private String name;
    private Integer id;
    private LocalDateTime creationDate;
    private List<Ticket> tickets;
    private UserStatus userStatus = UserStatus.DEACTIVATED;


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

    @Override
    public String toString() {
        return "User{" +
                "creationDate=" + creationDate +
                ", userId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
