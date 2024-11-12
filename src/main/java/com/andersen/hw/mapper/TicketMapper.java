package com.andersen.hw.mapper;

import com.andersen.hw.dto.TicketDtoOut;
import com.andersen.hw.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public static TicketDtoOut toDto(Ticket ticket) {
      return TicketDtoOut.builder()
              .id(ticket.getId())
              .ticketType(ticket.getTicketType())
              .clientId(ticket.getClient().getId())
              .creationDate(ticket.getCreationDate())
              .build();
    }
}
