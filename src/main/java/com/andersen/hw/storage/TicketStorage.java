package com.andersen.hw.storage;

import com.andersen.hw.model.Ticket;

import java.util.List;

public interface TicketStorage {
    void addTicket(Ticket ticket);
    Ticket getById(String id);
    List<Ticket> getAll();
    void deleteById(String id);
}
