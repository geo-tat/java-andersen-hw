package com.andersen.hw.service;

import com.andersen.hw.model.Identifiable;
import com.andersen.hw.model.Printable;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.storage.TicketStorageDao;
import com.andersen.hw.storage.TicketStorageInMemoryImpl;

import java.util.List;


public class TicketServiceImpl implements TicketService, Identifiable, Printable {
    private final int classId;
    private final TicketStorageInMemoryImpl ticketStorage;
    private final TicketStorageDao ticketStorageDao;

    public TicketServiceImpl(TicketStorageInMemoryImpl ticketStorage, TicketStorageDao ticketStorageDao) {
        this.ticketStorage = ticketStorage;
        this.ticketStorageDao = ticketStorageDao;
        this.classId = generateId();
    }


    @Override
    public void addTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        ticketStorageDao.addTicket(ticket);
    }

    @Override
    public Ticket getById(String id) {
        if (id == null || id.isEmpty()) {
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
    public void deleteById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        ticketStorageDao.deleteById(id);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        ticketStorageDao.updateTicket(ticket);
    }

    @Override
    public void share(String ticketId, String phone) {
        getById(ticketId);
        System.out.println("Send to the phone number: " + phone);
    }

    @Override
    public void share(String ticketId, String phone, String email) {
        getById(ticketId);
        System.out.println("Sending to the phone number: " + phone + " and to the email: " + email);
    }

    @Override
    public int getId() {
        return classId;
    }
}
