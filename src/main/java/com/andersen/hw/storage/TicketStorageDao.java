package com.andersen.hw.storage;

import com.andersen.hw.config.DatabaseConnectionManager;
import com.andersen.hw.enums.TicketType;
import com.andersen.hw.model.Client;
import com.andersen.hw.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketStorageDao implements TicketStorage {
    @Override
    public void addTicket(Ticket ticket) {
        String sql = "INSERT INTO public.ticket (id, ticket_type, user_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ticket.getTicketId());
            stmt.setObject(2, ticket.getTicketType().name(), java.sql.Types.OTHER);
            stmt.setLong(3, ticket.getClient().getUserId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getById(String id) {
        String sql = "SELECT t.id, t.ticket_type, t.creation_date AS ticket_creation_date, " +
                "u.id AS user_id, u.name, u.creation_date AS user_creation_date " +
                "FROM public.ticket t " +
                "JOIN public.user u ON t.user_id = u.id " +
                "WHERE t.id = ?";
        Ticket ticket = null;

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                TicketType ticketType = TicketType.valueOf(rs.getString("ticket_type"));


                long userId = rs.getLong("user_id");
                String name = rs.getString("name");
                LocalDateTime userCreationDate = rs.getTimestamp("user_creation_date").toLocalDateTime();
                Client client = new Client(userId, name, userCreationDate);

                LocalDateTime ticketCreationDate = rs.getTimestamp("ticket_creation_date").toLocalDateTime(); // Получаем creation_date тикета
                ticket = new Ticket(id, ticketType, client, ticketCreationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        String sql = "SELECT t.id, t.ticket_type, t.creation_date AS ticket_creation_date, " +
                "u.id AS user_id, u.name, u.creation_date AS user_creation_date " +
                "FROM public.ticket t " +
                "JOIN public.user u ON t.user_id = u.id";
        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = DatabaseConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String ticketId = rs.getString("id");
                TicketType ticketType = TicketType.valueOf(rs.getString("ticket_type"));

                long userId = rs.getLong("user_id");
                String name = rs.getString("name");
                LocalDateTime userCreationDate = rs.getTimestamp("user_creation_date").toLocalDateTime();
                Client client = new Client(userId, name, userCreationDate);

                LocalDateTime ticketCreationDate = rs.getTimestamp("ticket_creation_date").toLocalDateTime();
                tickets.add(new Ticket(ticketId, ticketType, client, ticketCreationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM public.ticket WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {
        String sql = "UPDATE public.ticket SET ticket_type = ? WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ticket.getTicketType().name());
            stmt.setString(2, ticket.getTicketId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
