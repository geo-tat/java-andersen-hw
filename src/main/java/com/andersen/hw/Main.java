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

        //     userService.addUser(client1);
        //     userService.addUser(client2);
        client1.setUserId(1);

        Ticket ticket = new Ticket("021", TicketType.DAY, client1);
        service.addTicket(ticket);

        List<User> users = userService.getAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
        //     Ticket result = service.getById("001");


    }
}