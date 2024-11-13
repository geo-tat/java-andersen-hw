package com.andersen.hw.model;

import com.andersen.hw.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class User implements Printable {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CurrentTimestamp
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.DEACTIVATED;


    public User() {
    }


    public User(String name) {
        this.name = name;
    }

    public User(Integer userId, String name, LocalDateTime creationDate) {
        this.name = name;
        this.id = userId;
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
