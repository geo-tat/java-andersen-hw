package com.andersen.hw.service;

import com.andersen.hw.model.Ticket;

import java.util.List;

public interface TicketService {
    void addTicket(Ticket ticket);
    Ticket getById(String id);
    List<Ticket> getAll();
    void deleteById(String id);

    void share(String ticketId,String phone);
    void share(String ticketId,String phone, String email);
}
