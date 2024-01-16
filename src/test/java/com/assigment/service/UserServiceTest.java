package com.assigment.service;

import com.assigment.model.User;
import com.assigment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

       // Iterable<User> result = userService.getAllUsers();

        //assertEquals(userList, result);
    }

    @Test
    public void testGetUserById() {
        String userId = "1";
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserById(userId);

        assertEquals(user, result);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId("1");
        user.setFirstName("John");
        user.setLastName("Smit");
        user.setLastLoginTimeUtc(LocalDateTime.now());

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        String userId = "1";
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setFirstName("OldFirstName");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("NewFirstName");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(userId, updatedUser);

        assertEquals("NewFirstName", result.getFirstName());
    }

    @Test
    public void testDeleteUser() {
        String userId = "1";

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
