package com.andersen.hw.storage;

import com.andersen.hw.model.User;

import java.util.List;

public interface UserStorage {
    void addUser(User user);
    User getById(long id);
    List<User> getAll();
    void deleteById(long id);
    void updateUser(User user);
}
