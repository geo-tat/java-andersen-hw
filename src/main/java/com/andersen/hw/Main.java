package com.andersen.hw;


import com.andersen.hw.enums.TicketType;
import com.andersen.hw.model.Client;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.service.TicketService;
import com.andersen.hw.service.TicketServiceImpl;
import com.andersen.hw.service.UserService;
import com.andersen.hw.service.UserServiceImpl;
import com.andersen.hw.storage.TicketStorageDao;
import com.andersen.hw.storage.TicketStorageInMemoryImpl;
import com.andersen.hw.storage.UserStorageDao;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        TicketService service = new TicketServiceImpl(new TicketStorageInMemoryImpl(), new TicketStorageDao());
        UserService userService = new UserServiceImpl(new UserStorageDao());
        Client client1 = new Client("Andy");
        Client client2 = new Client("Mickey");

        userService.addUser(client1);
        userService.addUser(client2);
        List<User> users = userService.getAll();


        Ticket ticket = new Ticket( TicketType.DAY, users.getFirst());
        Ticket ticket1 = new Ticket( TicketType.MONTH, users.getFirst());

        service.addTicket(ticket);
        service.addTicket(ticket1);


        for (User user : users) {
            System.out.println(user.toString());

        }
        Ticket result = service.getAll().getFirst();
        System.out.println(result.toString());

        service.deleteById(service.getAll().getFirst().getTicketId());

        for (User user : users) {
            userService.deleteById(user.getUserId());
        }
    }
}