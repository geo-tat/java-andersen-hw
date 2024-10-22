package com.andersen.hw.storage;

import com.andersen.hw.config.DatabaseConnectionManager;
import com.andersen.hw.model.Client;
import com.andersen.hw.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserStorageDao implements UserStorage {
    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user_info (name) VALUES (?)";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        String sql = "SELECT * FROM user_info WHERE id = ?";
        Client client = null;

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                LocalDateTime creationDate = rs.getTimestamp("creation_date").toLocalDateTime();
                client = new Client(id, name, creationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM user_info";
        List<User> clients = new ArrayList<>();

        try (Connection conn = DatabaseConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                LocalDateTime creationDate = rs.getTimestamp("creation_date").toLocalDateTime();
                clients.add(new Client(id, name, creationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public void deleteById(long id) {
        String deleteTicketsSql = "DELETE FROM ticket WHERE user_id = ?";
        String deleteUserSql = "DELETE FROM user_info WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection()) {


            try (PreparedStatement stmt = conn.prepareStatement(deleteUserSql)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user_info SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getUserId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
