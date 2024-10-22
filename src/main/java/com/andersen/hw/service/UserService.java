package com.andersen.hw.service;

import com.andersen.hw.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    User getById(Integer id);

    List<User> getAll();

    void deleteById(Integer id);

    void updateUser(User user);
}
