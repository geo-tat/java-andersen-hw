package com.andersen.hw;


import com.andersen.hw.config.AppSpringConfig;
import com.andersen.hw.enums.TicketType;
import com.andersen.hw.model.BusTicket;
import com.andersen.hw.model.Client;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.service.TicketService;
import com.andersen.hw.service.UserService;
import com.andersen.hw.util.TicketDataReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppSpringConfig.class);


        TicketService ticketService = context.getBean(TicketService.class);
        UserService userService = context.getBean(UserService.class);

        Client client1 = new Client("Andy");
        Client client2 = new Client("Mickey");
        userService.deleteById(5);
        userService.deleteById(6);
        userService.addUser(client1);
        userService.addUser(client2);
        List<User> users = userService.getAll();

        Ticket ticket = new Ticket(TicketType.DAY, users.getFirst());
        Ticket ticket1 = new Ticket(TicketType.MONTH, users.getFirst());

        ticketService.addTicket(ticket);
        ticketService.addTicket(ticket1);


        for (User user : users) {
            System.out.println(user.toString());

        }
        Ticket result = ticketService.getAll().getFirst();
        System.out.println(result.toString());

        ticketService.deleteById(ticketService.getAll().getFirst().getId());

        for (User user : users) {
            userService.deleteById(user.getId());
        }

        TicketDataReader busTicketFileReader = context.getBean(TicketDataReader.class);
        List<BusTicket> busTickets = busTicketFileReader.getBusTickets();
        System.out.println(busTickets);
    }


}