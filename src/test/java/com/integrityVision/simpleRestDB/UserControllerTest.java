package com.integrityVision.simpleRestDB;

import com.integrityVision.simpleRestDB.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//public class UserControllerTest {
//
//    private final MockMvc mockMvc = standaloneSetup(new UserController())
//            .build();
//
//    @Test
//    public void validate_get_address() throws Exception {
//
//        mockMvc.perform(get("/getAll"))
//                .andExpect(status().isOk());
//                .andExpect(
//                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$.street").value("12345 Horton Ave"));
//    }
//}
