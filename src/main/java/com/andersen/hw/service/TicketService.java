package com.andersen.hw.service;

import com.andersen.hw.model.Ticket;

import java.util.List;

public interface TicketService {
    void addTicket(Ticket ticket);

    Ticket getById(Integer id);

    List<Ticket> getAll();

    void deleteById(Integer id);

    void updateTicket(Ticket ticket);

    void share(Integer ticketId, String phone);

    void share(Integer ticketId, String phone, String email);
}
