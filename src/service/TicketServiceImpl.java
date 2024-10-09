package service;


import model.Identifiable;
import model.Printable;
import model.Ticket;
import storage.TicketStorage;
import util.IdGenerator;

import java.util.List;

public class TicketServiceImpl implements TicketService, Identifiable, Printable {
    private final int classId;
    private final TicketStorage ticketStorage;

    public TicketServiceImpl(TicketStorage ticketStorage) {
        this.classId = IdGenerator.generateId();
        this.ticketStorage = ticketStorage;
    }


    @Override
    public void addTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        ticketStorage.addTicket(ticket);
    }

    @Override
    public Ticket getById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        Ticket ticket = ticketStorage.getById(id);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket with ID " + id + " not founded");
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {

        return ticketStorage.getAll();
    }

    @Override
    public void deleteById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        ticketStorage.deleteById(id);
    }

    @Override
    public void share(String ticketId,String phone) {
        getById(ticketId);
        System.out.println("Send to the phone number: " + phone);
    }

    @Override
    public void share(String ticketId,String phone, String email) {
        getById(ticketId);
        System.out.println("Sending to the phone number: " + phone + " and to the email: " + email);
    }

    @Override
    public int getId() {
        return classId;
    }
}
