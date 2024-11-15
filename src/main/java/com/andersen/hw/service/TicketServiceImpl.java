package com.andersen.hw.service;

import com.andersen.hw.dto.TicketDtoOut;
import com.andersen.hw.mapper.TicketMapper;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.repository.TicketRepository;
import com.andersen.hw.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final UserRepository userRepository;
  private final TicketRepository ticketRepository;

  @Override
  @Transactional
  public void addTicket(Integer userId, Ticket ticket) {
    User client =
        userRepository
            .findById(userId)
            .orElseThrow(
                () -> new IllegalArgumentException("Client with ID " + userId + " not founded"));
    if (ticket == null) {
      throw new IllegalArgumentException("Ticket cannot be null");
    }

    ticket.setClient(client);
    ticketRepository.save(ticket);
  }

  @Override
  public TicketDtoOut getById(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null or empty");
    }
    Ticket ticket =
        ticketRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Ticket with ID " + id + " not founded"));
    return TicketMapper.toDto(ticket);
  }

  @Override
  public List<TicketDtoOut> getAll() {

    return ticketRepository.findAll().stream().map(TicketMapper::toDto).toList();
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null or empty");
    }
    ticketRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void updateTicket(Integer id, Ticket ticket) {
    Ticket ticketToUpdate =
        ticketRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Ticket with ID " + id + " not founded"));
    ticketToUpdate.setTicketType(ticket.getTicketType());
    ticketToUpdate.setClient(ticket.getClient());
    ticketRepository.save(ticketToUpdate);
  }

  @Override
  public void share(Integer ticketId, String phone) {
    getById(ticketId);
    System.out.println("Send to the phone number: " + phone);
  }

  @Override
  public void share(Integer ticketId, String phone, String email) {
    getById(ticketId);
    System.out.println("Sending to the phone number: " + phone + " and to the email: " + email);
  }
}
