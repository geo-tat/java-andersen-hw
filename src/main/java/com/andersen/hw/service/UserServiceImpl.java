package com.andersen.hw.service;

import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.storage.TicketStorageDao;
import com.andersen.hw.storage.UserStorageDao;
import com.andersen.hw.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final int classId;
    private final UserStorageDao userStorageDao;
    private final TicketStorageDao ticketStorageDao;

    @Autowired
    public UserServiceImpl(UserStorageDao userStorageDao, TicketStorageDao ticketStorageDao) {
        this.classId = IdGenerator.generateId();
        this.userStorageDao = userStorageDao;
        this.ticketStorageDao = ticketStorageDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userStorageDao.addUser(user);
    }

    @Override
    public User getById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        User client = userStorageDao.getById(id);
        if (client == null) {
            throw new IllegalArgumentException("Client with ID " + id + " not founded");
        }
        List<Ticket> ticketsByUser = ticketStorageDao.getByUserId(List.of(id));
        client.setTickets(ticketsByUser);
        return client;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userStorageDao.getAll();
        List<Integer> userIds = users.stream()
                .map(User::getId)
                .collect(Collectors.toList());

        List<Ticket> tickets = ticketStorageDao.getByUserId(userIds);

        for (User user : users) {
            List<Ticket> userTickets = tickets.stream()
                    .filter(ticket -> ticket.getClient().getId().equals(user.getId()))
                    .collect(Collectors.toList());
            user.setTickets(userTickets);
        }
        return users;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        userStorageDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userStorageDao.updateUser(user);
    }
}
