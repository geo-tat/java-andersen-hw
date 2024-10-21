package com.andersen.hw.service;

import com.andersen.hw.model.User;
import com.andersen.hw.storage.UserStorageDao;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserStorageDao userStorageDao;

    public UserServiceImpl(UserStorageDao userStorageDao) {
        this.userStorageDao = userStorageDao;
    }

    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userStorageDao.addUser(user);
    }

    @Override
    public User getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        User client = userStorageDao.getById(id);
        if (client == null) {
            throw new IllegalArgumentException("Client with ID " + id + " not founded");
        }
        return client;
    }

    @Override
    public List<User> getAll() {
        return userStorageDao.getAll();
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        userStorageDao.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userStorageDao.updateUser(user);
    }
}
