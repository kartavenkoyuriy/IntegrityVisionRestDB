package com.integrityVision.simpleRestDB.repository;

import com.integrityVision.simpleRestDB.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        user.setLastLogOn(rs.getDate("last_login_on"));
        return user;
    };

    public User insertUser(User user) {
        String sql = "SELECT * FROM insert_user(?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, userMapper,
                user.getLogin(), user.getFirstName(), user.getLastName(), user.getLastLogOn());
    }

    public User updateUser(User user) {
        String sql = "SELECT * FROM update_user(?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, userMapper,
                user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getLastLogOn());
    }

    public List<User> getUsersByDateWithLimit(Date date, int limit) {
        String sql = "SELECT * FROM get_users_by_date(?, ?)";
        return jdbcTemplate.query(sql, userMapper, date, limit);
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{id}, userMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

}
