package com.andersen.hw.storage;

import com.andersen.hw.model.Identifiable;
import com.andersen.hw.model.Printable;
import com.andersen.hw.model.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketStorageImpl implements TicketStorage, Identifiable, Printable {
    private final int classId;
    private final Map<String, Ticket> tickets = new HashMap<>();

    public TicketStorageImpl() {
        this.classId = generateId();
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
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