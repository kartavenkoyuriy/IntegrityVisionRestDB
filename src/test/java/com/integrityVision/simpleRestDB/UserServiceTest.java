package com.integrityVision.simpleRestDB;

import com.integrityVision.simpleRestDB.entity.User;
import com.integrityVision.simpleRestDB.repository.UserRepository;
import com.integrityVision.simpleRestDB.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private String dateInString;

    @Before
    public void setUp() throws Exception {
        dateInString = "2013-02-01";
    }

    @Test
    public void shouldCorrectInsertNewUser() throws Exception {
        //Setup
        User userToInsert = new User("1", "1", "1", Date.valueOf(dateInString));

        //Run
        when(userRepository.insertUser(userToInsert)).thenReturn(userToInsert);
        User userAfterTestInsert = userService.insertUser(userToInsert);

        //Verify
        verify(userRepository).insertUser(userToInsert);
        assertEquals(userToInsert, userAfterTestInsert);
        assertEquals("1", userAfterTestInsert.getLogin());

    }

    @Test
    public void shouldCorrectUpdateUser() throws Exception {
        //Setup
        User userToUpdate = new User(1, "2", "2", "2", Date.valueOf(dateInString));

        //Run
        when(userRepository.updateUser(userToUpdate)).thenReturn(userToUpdate);
        when(userRepository.getUserById(userToUpdate.getId())).thenReturn(userToUpdate);

        User userAfterTestUpdate = userService.updateUser(userToUpdate);

        //Verify
        verify(userRepository).updateUser(userToUpdate);
        assertEquals(userToUpdate, userAfterTestUpdate);
        assertEquals("2", userAfterTestUpdate.getLogin());
    }

    @Test
    public void shouldCorrectInsertUserWhenNoUserToUpdate() throws Exception {
        //Setup
        User userToUpdate = new User(9999, "2", "2", "2", Date.valueOf(dateInString));

        //Run
        when(userRepository.getUserById(userToUpdate.getId())).thenReturn(null);
        when(userRepository.insertUser(userToUpdate)).thenReturn(userToUpdate);
        User userAfterTestUpdate = userService.updateUser(userToUpdate);

        //Verify
        verify(userRepository).insertUser(userToUpdate);
        assertEquals(userToUpdate.getFirstName(), userAfterTestUpdate.getFirstName());
        assertEquals("2", userAfterTestUpdate.getLogin());
    }

    @Test
    public void shouldReturnUserByNameAndDate() throws Exception {
        //Setup
        User firstUserToInsert = new User("1", "1", "1", Date.valueOf(dateInString));
        User secondUserToInsert = new User("2", "2", "2", Date.valueOf(dateInString));
        User thirdUserToInsert = new User("3", "3", "3", Date.valueOf(dateInString));
        List<User> users = new ArrayList<>();

        //Run
        when(userRepository.insertUser(firstUserToInsert)).thenReturn(firstUserToInsert);
        when(userRepository.insertUser(secondUserToInsert)).thenReturn(secondUserToInsert);
        when(userRepository.insertUser(thirdUserToInsert)).thenReturn(thirdUserToInsert);
        users.add(userService.insertUser(firstUserToInsert));
        users.add(userService.insertUser(secondUserToInsert));
        users.add(userService.insertUser(thirdUserToInsert));
        when(userRepository.getUsersByDateWithLimit(Date.valueOf(dateInString), 2))
                .thenReturn(new ArrayList<>(Arrays.asList
                        (firstUserToInsert, secondUserToInsert)));
        users = userService.getUsersByDateWithLimit(Date.valueOf(dateInString), 2);

        //Verify
        verify(userRepository, times(3)).insertUser(any(User.class));
        verify(userRepository).getUsersByDateWithLimit(Date.valueOf(dateInString), 2);
        assertEquals(2, users.size());
    }
}
