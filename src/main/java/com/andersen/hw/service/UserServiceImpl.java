package com.andersen.hw.service;

import com.andersen.hw.enums.UserStatus;
import com.andersen.hw.exception.IllegalFlagException;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.repository.TicketRepository;
import com.andersen.hw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Value("${update-user-flag.enabled}")
    private boolean updateUserFlag;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;


    @Override
    @Transactional
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        User client = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with ID " + id + " not founded"));

        List<Ticket> ticketsByUser = ticketRepository.findAllByUserIds(List.of(id));
        client.setTickets(ticketsByUser);
        return client;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        List<Integer> userIds = users.stream()
                .map(User::getId)
                .collect(Collectors.toList());

        List<Ticket> tickets = ticketRepository.findAllByUserIds(userIds);

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
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, User user) {
        User client = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with ID " + id + " not founded"));
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        client.setName(user.getName());
        client.setUserStatus(user.getUserStatus());
        userRepository.save(client);
    }

    @Override
    public void updateUserStatus(Integer id, UserStatus userStatus) {
        if (!updateUserFlag) {
            throw new IllegalFlagException("The operation is disabled now");
        }
        User client = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with ID " + id + " not founded"));
        client.setUserStatus(userStatus);
        userRepository.save(client);
    }
}
