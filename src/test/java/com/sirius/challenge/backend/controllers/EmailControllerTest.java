package com.sirius.challenge.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class EmailControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void sendEmailwithAnonymusUser() throws Exception {

        String toEmail = "recipient@mail.com";
        String subject = "Test Subject";
        String body = "Test Body";

        mockMvc.perform(post("/api/users/current/email")
                        .param("toEmail", toEmail)
                        .param("subject", subject)
                        .param("body", body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }




}
