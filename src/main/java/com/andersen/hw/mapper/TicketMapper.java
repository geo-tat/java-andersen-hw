package com.andersen.hw.mapper;

import com.andersen.hw.enums.TicketType;
import com.andersen.hw.model.Client;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TicketMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setCreationTime(rs.getTimestamp("ticket_creation_date").toLocalDateTime());

        User client = new Client();
        client.setId(rs.getInt("user_id"));
        client.setName(rs.getString("name"));
        client.setCreationDate(rs.getTimestamp("user_creation_date").toLocalDateTime());

        ticket.setClient(client);
        ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));

        return ticket;
    }
}
