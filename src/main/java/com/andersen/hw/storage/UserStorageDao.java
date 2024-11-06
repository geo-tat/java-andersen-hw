package com.andersen.hw.storage;

import com.andersen.hw.mapper.UserMapper;
import com.andersen.hw.model.User;
import com.andersen.hw.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserStorageDao implements UserStorage {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;
    private final int classId;

    @Autowired
    public UserStorageDao(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.classId = IdGenerator.generateId();
        this.userMapper = userMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user_info (id, name, user_status) VALUES (?, ?, ?::user_status)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getUserStatus().name());
    }

    @Override
    public User getById(Integer id) {
        String sql = "SELECT * FROM user_info WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, userMapper, id);
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM user_info";
        return jdbcTemplate.query(sql, userMapper);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM user_info WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user_info SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getId());
    }

    @Override
    public void updateUserStatus(User user) {
        String sql = "UPDATE user_info SET user_status = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUserStatus(), user.getId());
    }
}
