package service;

import model.Ticket;

import java.util.List;

public interface TicketService {
    void addTicket(Ticket ticket);
    Ticket getById(String id);
    List<Ticket> gatAll();
    void deleteById(String id);
}
