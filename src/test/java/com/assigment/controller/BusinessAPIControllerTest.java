package com.assigment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BusinessAPIController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BusinessAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void getAllBusinesses() throws Exception {
        mockMvc.perform(get("/api/businesses"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getBusinessById() throws Exception {
        mockMvc.perform(post("/api/businesses")
                .content("TestBusiness")
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/businesses/{id}", "TestBusiness"))
                .andExpect(status().isOk())
                .andExpect(content().string("TestBusiness"));
    }

    @Test
    void createBusiness() throws Exception {
        mockMvc.perform(post("/api/businesses")
                        .content("TestBusiness")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Business created successfully!"));
    }

    @Test
    void updateBusiness() throws Exception {
        mockMvc.perform(post("/api/businesses")
                .content("TestBusiness")
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(put("/api/businesses/{id}", "TestBusiness")
                        .content("UpdatedBusiness")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Business updated successfully!"));
    }

    @Test
    void deleteBusiness() throws Exception {
        mockMvc.perform(post("/api/businesses")
                .content("TestBusiness")
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(delete("/api/businesses/{id}", "TestBusiness"))
                .andExpect(status().isOk())
                .andExpect(content().string("Business deleted successfully!"));
    }
}
