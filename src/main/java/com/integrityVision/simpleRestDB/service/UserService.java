package com.integrityVision.simpleRestDB.service;

import com.integrityVision.simpleRestDB.entity.User;
import com.integrityVision.simpleRestDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByDate(Date date) {
        return userRepository.getUsersByDate(date);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.getUsersByName(name);
    }

    @Transactional
    public User updateUser(User user) {//TODO:insert and update?
        return userRepository.save(user);
    }

    @Transactional
    public User insertUser(User user) {
        return userRepository.save(user);
    }

}
