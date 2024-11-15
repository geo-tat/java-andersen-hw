package com.andersen.hw.service;

import com.andersen.hw.dto.TicketDtoOut;
import com.andersen.hw.model.Ticket;
import java.util.List;

public interface TicketService {
  void addTicket(Integer userId, Ticket ticket);

  TicketDtoOut getById(Integer id);

  List<TicketDtoOut> getAll();

  void deleteById(Integer id);

  void updateTicket(Integer id, Ticket ticket);

  void share(Integer ticketId, String phone);

  void share(Integer ticketId, String phone, String email);
}
