package com.andersen.hw.storage;

import com.andersen.hw.enums.TicketType;
import com.andersen.hw.model.Client;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.util.IdGenerator;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketStorageDao implements TicketStorage {
    private final DataSource dataSource;

    private final int classId;

    public TicketStorageDao(DataSource dataSource) {
        this.classId = IdGenerator.generateId();
        this.dataSource = dataSource;
    }

    @Override
    public void addTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket (id, ticket_type, user_id) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getId());
            stmt.setObject(2, ticket.getTicketType().name(), java.sql.Types.OTHER);
            stmt.setLong(3, ticket.getClient().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getById(Integer id) {
        String sql = "SELECT t.id, t.ticket_type, t.creation_date AS ticket_creation_date, " +
                "u.id AS user_id, u.name, u.creation_date AS user_creation_date " +
                "FROM ticket t " +
                "JOIN user_info u ON t.user_id = u.id " +
                "WHERE t.id = ?";
        Ticket ticket = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                TicketType ticketType = TicketType.valueOf(rs.getString("ticket_type"));


                Integer userId = rs.getInt("user_id");
                String name = rs.getString("name");
                LocalDateTime userCreationDate = rs.getTimestamp("user_creation_date").toLocalDateTime();
                Client client = new Client(userId, name, userCreationDate);

                LocalDateTime ticketCreationDate = rs.getTimestamp("ticket_creation_date").toLocalDateTime();
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
                "FROM ticket t " +
                "JOIN user_info u ON t.user_id = u.id";
        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Integer ticketId = rs.getInt("id");
                TicketType ticketType = TicketType.valueOf(rs.getString("ticket_type"));

                Integer userId = rs.getInt("user_id");
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
    public void deleteById(Integer id) {
        String sql = "DELETE FROM ticket WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {
        String sql = "UPDATE ticket SET ticket_type = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ticket.getTicketType().name());
            stmt.setLong(2, ticket.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
