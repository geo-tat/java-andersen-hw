package com.andersen.hw;


import com.andersen.hw.enums.SectorType;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.service.TicketService;
import com.andersen.hw.service.TicketServiceImpl;
import com.andersen.hw.storage.TicketStorageImpl;
import com.andersen.hw.util.CustomHashSet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;


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