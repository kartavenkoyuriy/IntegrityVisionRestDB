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

    @Transactional
    public User insertUser(User user) {
        return userRepository.insertUser(user);
    }

    @Transactional
    public User updateUser(User user) {
        User tempUser = userRepository.getUserById(user.getId());
        if (tempUser != null) {
            return userRepository.updateUser(user);
        } else{
            return userRepository.insertUser(user);
        }
    }

    public List<User> getUsersByDateWithLimit(Date date, int limit) {
        return userRepository.getUsersByDateWithLimit(date, limit);
    }

}
