package com.andersen.hw.mapper;

import com.andersen.hw.model.Client;
import com.andersen.hw.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new Client();
        user.setId(rs.getInt("id"));
        user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
        user.setName(rs.getString("name"));

        return user;
    }
}
