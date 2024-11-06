package com.andersen.hw.service;

import com.andersen.hw.model.Identifiable;
import com.andersen.hw.model.Printable;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.storage.TicketStorageDao;
import com.andersen.hw.storage.UserStorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService, Identifiable, Printable {
    @Value("${update-user-flag.enabled}")
    private boolean updateUserFlag;
    private final int classId;

    private final UserStorageDao userStorageDao;
    private final TicketStorageDao ticketStorageDao;

    @Autowired
    public TicketServiceImpl(TicketStorageDao ticketStorageDao, UserStorageDao userStorageDao) {
        this.userStorageDao = userStorageDao;
        this.ticketStorageDao = ticketStorageDao;
        this.classId = generateId();
    }


    @Override
    @Transactional
    public void addTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        ticketStorageDao.addTicket(ticket);
    }

    @Override
    public Ticket getById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        Ticket ticket = ticketStorageDao.getById(id);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket with ID " + id + " not founded");
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {

        return ticketStorageDao.getAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        ticketStorageDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        ticketStorageDao.updateTicket(ticket);
    }

    @Override
    @Transactional
    public void updateUserStatusAndCreateTicket(User user, Ticket ticket) {
        if (updateUserFlag) {
            userStorageDao.updateUserStatus(user);
            ticketStorageDao.addTicket(ticket);
        }
        throw new IllegalArgumentException("The operation is disabled now");
    }

    @Override
    public void share(Integer ticketId, String phone) {
        getById(ticketId);
        System.out.println("Send to the phone number: " + phone);
    }

    @Override
    public void share(Integer ticketId, String phone, String email) {
        getById(ticketId);
        System.out.println("Sending to the phone number: " + phone + " and to the email: " + email);
    }

    @Override
    public int getId() {
        return classId;
    }
}
