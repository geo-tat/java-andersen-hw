package com.andersen.hw.storage;

import com.andersen.hw.model.User;

import java.util.List;

public interface UserStorage {
    void addUser(User user);

    User getById(Integer id);

    List<User> getAll();

    void deleteById(Integer id);

    void updateUser(User user);

    void updateUserStatus(User user);
}
