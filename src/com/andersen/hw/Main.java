package com.andersen.hw;

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

import java.util.List;



public class Main {
    public static void main(String[] args) {
        TicketService service = new TicketServiceImpl(new TicketStorageImpl());
        BigDecimal price = BigDecimal.valueOf(100);
        LocalDateTime timofEvent = LocalDateTime.of(2024, 12, 31, 12, 0);

        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket("000" + i, "Hall", "010", timofEvent, true, SectorType.C,
                    23.0, price);
            service.addTicket(ticket);
        }

        service.getById("0007").printTicketInfo();

        Admin admin = new Admin();
        Client client = new Client();


        List<User> users = List.of(client, admin);


        client.getTicket();
        System.out.println(client.getId());


        // checking overriding print() and class Id
        client.print();

        // checking share methods
        service.share("0001", "+0303939399");
        service.share("0002", "+39341340", "test@test.com");

    }

}