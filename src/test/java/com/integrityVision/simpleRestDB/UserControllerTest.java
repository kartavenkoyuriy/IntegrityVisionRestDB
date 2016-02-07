package com.integrityVision.simpleRestDB;

import com.integrityVision.simpleRestDB.entity.User;
import com.integrityVision.simpleRestDB.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String dateInString;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        dateInString = "2013-02-02";
    }

    @Test
    public void shouldCorrectInsertUser() throws Exception {
        //Setup
        User userToInsert = new User("1", "1", "1", Date.valueOf(dateInString));

        //Run

        //Verify
        mockMvc.perform(post("/insertUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userToInsert.toJsonStringWithoutId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is("1")))
                .andExpect(jsonPath("$.firstName", is("1")))
                .andExpect(jsonPath("$.lastName", is("1")))
                .andExpect(jsonPath("$.lastLogOn", is(dateInString)));
    }

    @Test
    public void shouldCorrectUpdateUserWhichExist() throws Exception {
        //Setup
        User userToUpdate = new User(1, "2", "2", "2", Date.valueOf(dateInString));

        //Run

        //Verify
        mockMvc.perform(put("/updateUser", userToUpdate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userToUpdate.toJsonStringWithId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.login", is("2")))
                .andExpect(jsonPath("$.firstName", is("2")))
                .andExpect(jsonPath("$.lastName", is("2")))
                .andExpect(jsonPath("$.lastLogOn", is(dateInString)));
    }

    @Test
    public void shouldCorrectInsertNewUserIfCantUpdate() throws Exception {
        //Setup
        User userToUpdate = new User(9999, "2", "2", "2", Date.valueOf(dateInString));

        //Run

        //Verify
        mockMvc.perform(put("/updateUser", userToUpdate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userToUpdate.toJsonStringWithId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1001)))
                .andExpect(jsonPath("$.login", is("2")))
                .andExpect(jsonPath("$.firstName", is("2")))
                .andExpect(jsonPath("$.lastName", is("2")))
                .andExpect(jsonPath("$.lastLogOn", is(dateInString)));
    }

    @Test
    public void shouldCorrectGetUserList() throws Exception {
        //Setup
        User firstUserToInsert = new User(1, "1", "1", "1", Date.valueOf(dateInString));
        User secondUserToInsert = new User(2, "2", "2", "2", Date.valueOf(dateInString));
        User thirdUserToInsert = new User(3, "3", "3", "3", Date.valueOf(dateInString));

        //Run
        userService.insertUser(firstUserToInsert);
        userService.insertUser(secondUserToInsert);
        userService.insertUser(thirdUserToInsert);

        //Verify
        mockMvc.perform(get("/searchByDate?date=" + dateInString + "&limit=" + 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].lastLogOn", is(dateInString)))
                .andExpect(jsonPath("$[1].lastLogOn", is(dateInString)));
    }

}
