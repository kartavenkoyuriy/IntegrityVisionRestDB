package com.integrityVision.simpleRestDB.repository;

import com.integrityVision.simpleRestDB.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> userMapper = (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("login"), rs.getString("firstname"), rs.getString("lastname"), rs.getDate("last_login_on"));

    public User insertUser(User user){
        String sql = "SELECT * FROM insert_user(?, ?, ?, ?)";
        User resultUser = jdbcTemplate.queryForObject(sql, userMapper,
                user.getLogin(), user.getFirstName(), user.getLastName(), user.getLastLogOn());
        return resultUser;
    }

    public User updateUser(User user){
        String sql = "SELECT * FROM update_user(?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, userMapper,
                user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getLastLogOn());
    }

    public List<User> getUsersByDateWithLimit(Date date, int limit){
        String sql = "SELECT * FROM get_users_by_date(?, ?)";
        return jdbcTemplate.query(sql, userMapper, new Object[] { date, limit });
    }
}
