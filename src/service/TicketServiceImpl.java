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
        ticketStorage.addTicket(ticket);
    }

    @Override
    public Ticket getById(String id) {
        return ticketStorage.getById(id);
    }

    @Override
    public List<Ticket> gatAll() {
        return ticketStorage.gatAll();
    }

    @Override
    public void deleteById(String id) {
        ticketStorage.deleteById(id);
    }
}
