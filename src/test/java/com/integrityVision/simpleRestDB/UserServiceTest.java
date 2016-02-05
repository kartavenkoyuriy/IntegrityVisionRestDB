package com.integrityVision.simpleRestDB;

import com.integrityVision.simpleRestDB.entity.User;
import com.integrityVision.simpleRestDB.repository.UserRepository;
import com.integrityVision.simpleRestDB.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Ignore("Not ready yet")
    @Test
    public void shouldReturnUserByNameAndDate(){

    }

    @Test
    public void shouldCorrectInsertNewUser() throws Exception{
        //Setup
        User userToInsert = new User(9999, "1", "1", "1", new Date(Calendar.getInstance().getTimeInMillis()));

        //Run
        when(userRepository.insertUser(userToInsert)).thenReturn(userToInsert);
        User userAfterTestInsert = userService.insertUser(userToInsert);


        //Verify
        assertEquals(userToInsert, userAfterTestInsert);
        assertEquals("1", userAfterTestInsert.getLogin());

    }

//    @Test
//    public void shouldCorrectUpdateUser() throws Exception{
//        //Setup
//        User userToUpdate = new User(9999, "1", "1", "1", new Date(Calendar.getInstance().getTimeInMillis()));
//
//        //Run
//        when(userRepository.save(userToUpdate)).thenReturn(userToUpdate);
//        User userAfterTestUpdate = userService.updateUser(userToUpdate);
//
//        when(userRepository.findOne(userToUpdate.getId())).thenReturn(userToUpdate);
//        User userAfterGetById = userService.getUserById(userToUpdate.getId());
//
//        //Verify
//        assertEquals(userToUpdate, userAfterTestUpdate);
//        assertEquals("1", userAfterTestUpdate.getLogin());
//
//        assertEquals(userToUpdate, userAfterGetById);
//        assertEquals("1", userAfterGetById.getLastName());
//    }

}
