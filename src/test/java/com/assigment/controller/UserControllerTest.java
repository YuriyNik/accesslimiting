package com.assigment.controller;

import com.assigment.model.User;
import com.assigment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

  //@MockBean
  //private QuotaService quotaService;



    @Test
    public void testGetUserById() throws Exception {
        String userId = "1";
        User user = new User();
        user.setId(userId);

        when(userService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId));

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setId("1");
        user.setFirstName("John");
        user.setLastName("Doe");
       // user.setLastLoginTimeUtc(LocalDateTime.now())

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testUpdateUser() throws Exception {
        String userId = "1";
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("NewFirstName");

        when(userService.updateUser(eq(userId), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("NewFirstName"));

        verify(userService, times(1)).updateUser(eq(userId), any(User.class));
    }

    @Test
    public void testDeleteUser() throws Exception {
        String userId = "1";

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", userId))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(userId);
    }
}
