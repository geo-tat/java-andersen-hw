package com.andersen.hw;

import model.Admin;
import model.Client;
import model.Ticket;
import model.User;
import service.TicketService;
import service.TicketServiceImpl;
import storage.TicketStorageImpl;
import util.CustomHashSet;
import com.andersen.hw.enums.SectorType;

import com.andersen.hw.model.Admin;
import com.andersen.hw.model.Client;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.service.TicketService;
import com.andersen.hw.service.TicketServiceImpl;
import com.andersen.hw.storage.TicketStorageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.Iterator;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        TicketService service = new TicketServiceImpl(new TicketStorageImpl());
        BigDecimal price = BigDecimal.valueOf(100);
        LocalDateTime timofEvent = LocalDateTime.of(2024, 12, 31, 12, 0);

        for (int i = 0; i < 30; i++) {
            Ticket ticket = new Ticket("0" + i, "Hall", "010", timofEvent, true, SectorType.C,
                    23.0, price);
            service.addTicket(ticket);

        }



        CustomHashSet<Ticket> set = new CustomHashSet<>();

        set.put(service.getById("01"));
        set.put(service.getById("03"));
        set.put(service.getById("04"));
        set.put(service.getById("06"));
        Iterator<Ticket> iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}