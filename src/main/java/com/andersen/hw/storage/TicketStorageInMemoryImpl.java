package com.andersen.hw.storage;


import com.andersen.hw.model.Identifiable;
import com.andersen.hw.model.Printable;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.util.CustomArrayList;
import com.andersen.hw.util.CustomHashSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketStorageInMemoryImpl implements TicketStorage, Identifiable, Printable {
    private final int classId;
    private final Map<Long, Ticket> tickets = new HashMap<>();
    private final CustomArrayList<Ticket> customArrayTikets = new CustomArrayList<>();
    private final CustomHashSet<Ticket> customHashSetTickets = new CustomHashSet<>();

    public TicketStorageInMemoryImpl() {
        this.classId = generateId();
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
        customArrayTikets.put(ticket);
        customHashSetTickets.put(ticket);
    }

    @Override
    public Ticket getById(Long id) {
        return tickets.get(id);
    }

    @Override
    public List<Ticket> getAll() {
        return tickets.values().stream().toList();
    }

    @Override
    public void deleteById(Long id) {
        tickets.remove(id);
    }

    @Override
    public void updateTicket(Ticket ticket) {

    }

    @Override
    public int getId() {
        return classId;
    }
}