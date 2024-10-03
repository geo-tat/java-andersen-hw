package service;

import enums.SectorType;
import model.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketService {

    public static void main(String[] args) {
        LocalDateTime timeOfEvent = LocalDateTime.of(2024, 12, 31, 12, 0);
        Ticket emptyTicket = new Ticket();
        Ticket fullTicket = new Ticket("A3F1", "Hall", "043", timeOfEvent, true,
                SectorType.C, 30, BigDecimal.valueOf(50));
        Ticket limitedTicket = new Ticket("ConHall", "054", timeOfEvent);
        System.out.println(fullTicket);
        System.out.println(limitedTicket);
    }
}
