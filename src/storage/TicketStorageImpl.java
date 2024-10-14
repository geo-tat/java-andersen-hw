package storage;

import model.Identifiable;
import model.Printable;
import model.Ticket;
import util.CustomArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketStorageImpl implements TicketStorage, Identifiable, Printable {
    private final int classId;
    private final Map<String, Ticket> tickets = new HashMap<>();
    private final CustomArrayList<Ticket> customTiskets = new CustomArrayList<>();

    public TicketStorageImpl() {
        this.classId = generateId();
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
        customTiskets.put(ticket);
    }

    @Override
    public Ticket getById(String id) {
        return tickets.get(id);
    }

    @Override
    public List<Ticket> getAll() {
        return tickets.values().stream().toList();
    }

    @Override
    public void deleteById(String id) {
        tickets.remove(id);
    }

    @Override
    public int getId() {
        return classId;
    }
}
