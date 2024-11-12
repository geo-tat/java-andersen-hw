package com.andersen.hw.controller;

import com.andersen.hw.dto.TicketDtoOut;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tickets")
@RestController
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    void createTicket(@RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
    }

    @GetMapping
    List<TicketDtoOut> getAllTickets() {
       return ticketService.getAll();
    }

    @GetMapping("/{id}")
    TicketDtoOut getTicketById(@PathVariable Integer id) {
        return ticketService.getById(id);
    }

    @DeleteMapping("/{id}")
    void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteById(id);
    }

    @PutMapping("/{id}")
    void updateTicket(@PathVariable Integer id,
                      @RequestBody Ticket ticket) {
        ticketService.updateTicket(id,ticket);
    }



}
