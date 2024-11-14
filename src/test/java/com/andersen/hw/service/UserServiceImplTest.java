package com.andersen.hw.service;

import com.andersen.hw.enums.TicketType;
import com.andersen.hw.enums.UserStatus;
import com.andersen.hw.exception.IllegalFlagException;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import com.andersen.hw.repository.TicketRepository;
import com.andersen.hw.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private UserServiceImpl userService;

    User user1;
    User user2;
    Ticket ticket1;
    Ticket ticket2;

    @BeforeEach
    void setUp() {
        user1 = new User("Test1");
        user2 = new User("Test2");
        ticket1 = new Ticket(TicketType.DAY);
        ticket2 = new Ticket(TicketType.WEEK);
        ReflectionTestUtils.setField(userService, "updateUserFlag", true);
    }

    @Test
    void testAddUser_whenUserIsValid() {
        when(userRepository.save(user1)).thenReturn(user1);
        User savedUser = userService.addUser(user1);

        assertNotNull(savedUser);
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void testAddUser_whenUserIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.addUser(null));
        assertEquals("User cannot be null", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetById_whenUserExists() {

        user1.setId(1);
        ticket1.setClient(user1);
        ticket2.setClient(user1);
        List<Ticket> tickets = List.of(ticket1, ticket2);

        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(ticketRepository.findAllByUserIds(List.of(1))).thenReturn(tickets);

        User result = userService.getById(1);

        assertNotNull(result);
        assertEquals(tickets, result.getTickets());
        verify(userRepository, times(1)).findById(1);
        verify(ticketRepository, times(1)).findAllByUserIds(List.of(1));
    }

    @Test
    void testGetById_whenUserDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.getById(1));
        assertEquals("Client with ID 1 not founded", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetAll() {

        user1.setId(1);
        List<User> users = List.of(user1);
        ticket1.setClient(user1);
        ticket2.setClient(user1);

        List<Ticket> tickets = List.of(ticket1, ticket2);

        when(userRepository.findAll()).thenReturn(users);
        when(ticketRepository.findAllByUserIds(anyList())).thenReturn(List.of(ticket1, ticket2));

        List<User> result = userService.getAll();

        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getTickets().size());
        verify(userRepository, times(1)).findAll();
        verify(ticketRepository, times(1)).findAllByUserIds(List.of(1));
    }

    @Test
    void testDeleteById_whenIdIsValid() {
        userService.deleteById(1);

        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteById_whenIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.deleteById(null));
        assertEquals("ID cannot be null", exception.getMessage());
        verify(userRepository, never()).deleteById(anyInt());
    }

    @Test
    void testUpdateUser_whenUserExists() {
        User existingUser = new User();
        existingUser.setId(1);
        User updatedUser = new User();
        updatedUser.setName("New Name");

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        userService.updateUser(1, updatedUser);

        verify(userRepository, times(1)).save(existingUser);
        assertEquals("New Name", existingUser.getName());
    }

    @Test
    void testUpdateUser_whenUserDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.updateUser(1, new User()));
        assertEquals("Client with ID 1 not founded", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateUserStatus_whenFlagIsTrue() {
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.updateUserStatus(1, UserStatus.ACTIVATED);

        verify(userRepository, times(1)).save(user);
        assertEquals(UserStatus.ACTIVATED, user.getUserStatus());
    }

    @Test
    void testUpdateUserStatus_whenFlagIsFalse() {
        ReflectionTestUtils.setField(userService, "updateUserFlag", false);

        IllegalFlagException exception = assertThrows(IllegalFlagException.class, () -> userService.updateUserStatus(1, UserStatus.ACTIVATED));
        assertEquals("The operation is disabled now", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testDeleteAll() {
        userService.deleteAll();

        verify(userRepository, times(1)).deleteAll();
    }


}