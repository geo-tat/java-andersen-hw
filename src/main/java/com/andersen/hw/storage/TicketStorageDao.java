package com.andersen.hw.storage;

import com.andersen.hw.mapper.TicketMapper;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TicketStorageDao implements TicketStorage {
    private final JdbcTemplate jdbcTemplate;
    private final TicketMapper ticketMapper;

    private final int classId;

    @Autowired
    public TicketStorageDao(JdbcTemplate jdbcTemplate, TicketMapper mapper) {
        this.classId = IdGenerator.generateId();
        this.jdbcTemplate = jdbcTemplate;
        this.ticketMapper = mapper;
    }

    @Override
    public void addTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket (id, ticket_type, user_id) VALUES (?, ?::ticket_type, ?)";
        jdbcTemplate.update(sql, ticket.getId(), ticket.getTicketType().name(), ticket.getClient().getId());
    }

    @Override
    public Ticket getById(Integer id) {
        String sql = "SELECT t.id, t.ticket_type, t.creation_date AS ticket_creation_date, " +
                "u.id AS user_id, u.name, u.creation_date AS user_creation_date " +
                "FROM ticket t " +
                "JOIN user_info u ON t.user_id = u.id " +
                "WHERE t.id = ?";

        return jdbcTemplate.queryForObject(sql, ticketMapper, id);
    }

    @Override
    public List<Ticket> getAll() {
        String sql = "SELECT t.id, t.ticket_type, t.creation_date AS ticket_creation_date, " +
                "u.id AS user_id, u.name, u.creation_date AS user_creation_date " +
                "FROM ticket t " +
                "JOIN user_info u ON t.user_id = u.id";

        return jdbcTemplate.query(sql, ticketMapper);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM ticket WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    @Override
    public void updateTicket(Ticket ticket) {
        String sql = "UPDATE ticket SET ticket_type = ? WHERE id = ?";
        jdbcTemplate.update(sql, ticket.getTicketType().name(), ticket.getId());
    }

    @Override
    public List<Ticket> getByUserId(List<Integer> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return Collections.emptyList();
        }

        String placeholders = userIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(", "));

        String sql = "SELECT t.id, t.ticket_type, t.creation_date AS ticket_creation_date, " +
                "u.id AS user_id, u.name, u.creation_date AS user_creation_date " +
                "FROM ticket t " +
                "JOIN user_info u ON t.user_id = u.id " +
                "WHERE t.user_id IN (" + placeholders + ")";

        return jdbcTemplate.query(sql, ticketMapper, userIds.toArray());
    }
}
