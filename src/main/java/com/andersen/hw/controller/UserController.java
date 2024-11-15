package com.andersen.hw.controller;

import com.andersen.hw.enums.UserStatus;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.service.TicketService;
import com.andersen.hw.service.UserService;
import java.util.List;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserService userService;
  private final TicketService ticketService;

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.addUser(user);
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable Integer id) {
    return userService.getById(id);
  }

  @GetMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Integer id, @RequestBody User user) {
    userService.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    userService.deleteById(id);
  }

  @DeleteMapping
  public void deleteAll() {
    userService.deleteAll();
  }

  @PutMapping("/{id}/status")
  public void updateStatusAndCreateTicket(
      @PathVariable Integer userId,
      @RequestParam UserStatus userStatus,
      @RequestBody Ticket ticket) {
    userService.updateUserStatus(userId, userStatus);
    ticketService.addTicket(userId, ticket);
  }
}
