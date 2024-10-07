package service;

import model.Ticket;

import java.util.List;

public interface TicketService {
    void addTicket(Ticket ticket);
    Ticket getById(String id);
    List<Ticket> gatAll();
    void deleteById(String id);

    void share(String ticketId,String phone);
    void share(String ticketId,String phone, String email);
}
