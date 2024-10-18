package com.andersen.hw.storage;

import model.Identifiable;
import model.Printable;
import model.Ticket;
import util.CustomArrayList;
import util.CustomHashSet;
import com.andersen.hw.model.Identifiable;
import com.andersen.hw.model.Printable;
import com.andersen.hw.model.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketStorageImpl implements TicketStorage, Identifiable, Printable {
    private final int classId;
    private final Map<String, Ticket> tickets = new HashMap<>();
    private final CustomArrayList<Ticket> customArrayTikets = new CustomArrayList<>();
    private final CustomHashSet<Ticket> customHashSetTickets = new CustomHashSet<>();

    public TicketStorageImpl() {
        this.classId = generateId();
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
        customArrayTikets.put(ticket);
        customHashSetTickets.put(ticket);
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
