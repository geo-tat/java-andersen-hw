package com.andersen.hw.service;

import com.andersen.hw.model.Ticket;

import java.util.List;

public interface TicketService {
    void addTicket(Ticket ticket);

    Ticket getById(Long id);

    List<Ticket> getAll();

    void deleteById(Long id);

    void updateTicket(Ticket ticket);

    void share(Long ticketId, String phone);

    void share(Long ticketId, String phone, String email);
}
