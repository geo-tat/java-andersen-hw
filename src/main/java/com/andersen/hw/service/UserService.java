package com.andersen.hw.service;

import com.andersen.hw.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    User getById(Long id);

    List<User> getAll();

    void deleteById(Long id);

    void updateUser(User user);
}
