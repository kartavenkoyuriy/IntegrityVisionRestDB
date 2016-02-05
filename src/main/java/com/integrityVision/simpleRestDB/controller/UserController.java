package com.integrityVision.simpleRestDB.controller;

import com.integrityVision.simpleRestDB.entity.User;
import com.integrityVision.simpleRestDB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/searchByDate", method = RequestMethod.GET)
    public List<User> getUsersByDate(@RequestParam("date") Date date, @RequestParam("limit") int limit) {
        return userService.getUsersByDateWithLimit(date, limit);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public User insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

}
