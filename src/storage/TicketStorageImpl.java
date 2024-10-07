package storage;

import model.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketStorageImpl implements TicketStorage {
    private final Map<String, Ticket> tickets = new HashMap<>();

    @Override
    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getTicketId(),ticket);
    }

    @Override
    public Ticket getById(String id) {
        return tickets.get(id);
    }

    @Override
    public List<Ticket> gatAll() {
        return tickets.values().stream().toList();
    }

    @Override
    public void deleteById(String id) {
        tickets.remove(id);
    }
}
