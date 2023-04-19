package com.example.appKp6.service;

import com.example.appKp6.controller.UserController;
import com.example.appKp6.dto.LoginDTO;
import com.example.appKp6.entity.User;
import com.example.appKp6.service.map.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {

    /*@Autowired
    private UserService userService;*/


    @Autowired
    private UserController userController;
    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    public void loginUser() {
    }

    @Test
    public void createEmployee() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setEmail("testuser@test.com");

        User createdUser = userServiceImpl.createEmployee(user);

        assertNotNull(createdUser.getId());
        assertEquals(user.getUsername(), createdUser.getUsername());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
    }

    @Test(expected = ResponseStatusException.class)
    public void testCreateUserWithExistingUsername() {
        User user = new User();
        user.setUsername("7");
        user.setPassword("testpassword");
        user.setEmail("testuser@test.com");

        userServiceImpl.createEmployee(user);

        User user2 = new User();
        user2.setUsername("7");
        user2.setPassword("testpassword2");
        user2.setEmail("testuser2@test.com");

        userServiceImpl.createEmployee(user2);
    }

    @Test(expected = ResponseStatusException.class)
    public void testCreateUserWithInvalidUsername() {
        User user = new User();
        user.setUsername("");
        user.setPassword("testpassword");
        user.setEmail("testuser@test.com");

        userServiceImpl.createEmployee(user);
    }

    @Test(expected = ResponseStatusException.class)
    public void testCreateUserWithInvalidPassword() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("");
        user.setEmail("testuser@test.com");

        userServiceImpl.createEmployee(user);
    }

    @Test
    public void testLoginSuccess() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");

        User user = new User();
        user.setUsername("testUser");
        user.setPassword(new BCryptPasswordEncoder().encode("testPassword"));

        Mockito.when(userServiceImpl.loginUser(loginDTO)).thenReturn(user);

        ResponseEntity<?> response = userController.login(loginDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testLoginFailure() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");

        Mockito.when(userServiceImpl.loginUser(loginDTO)).thenReturn(null);

        ResponseEntity<?> response = userController.login(loginDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}