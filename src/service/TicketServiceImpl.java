package service;

import model.Ticket;
import storage.TicketStorage;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private final TicketStorage ticketStorage;

    public TicketServiceImpl(TicketStorage ticketStorage) {
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
    public List<Ticket> gatAll() {

        return ticketStorage.gatAll();
    }

    @Override
    public void deleteById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        ticketStorage.deleteById(id);
    }
}
