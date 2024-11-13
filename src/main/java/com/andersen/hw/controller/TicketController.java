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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tickets")
@RestController
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public void createTicket(@RequestBody Ticket ticket,
                             @RequestParam Integer userId) {
        ticketService.addTicket(userId, ticket);
    }

    @GetMapping
    public List<TicketDtoOut> getAllTickets() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public TicketDtoOut getTicketById(@PathVariable Integer id) {
        return ticketService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateTicket(@PathVariable Integer id,
                             @RequestBody Ticket ticket) {
        ticketService.updateTicket(id, ticket);
    }


}
