package com.andersen.hw.model;

import com.andersen.hw.util.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_info")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract public class User implements Printable {
    @Column(name = "name")
    private String name;
    @Id
    private Integer id;
    @CreationTimestamp
    @Column(name = "creation_date")
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
