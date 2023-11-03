package com.sirius.challenge.backend.controllers;

import com.sirius.challenge.backend.security.models.Role;
import com.sirius.challenge.backend.security.models.User;
import com.sirius.challenge.backend.security.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class UserControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Valid Get stats with Admin Role")
    @WithMockUser(username = "testuser@mail.com", authorities = "ADMIN")
    void testGetStats() throws Exception {
        // Datos de prueba
        User user1 = new User("User1", "Test","user1@example.com", "asd", Role.USER);
        User user2 = new User("User2", "Test","user2@example.com", "asd", Role.USER);

        user1.setEmailDailyCount(3);
        userRepository.save(user1);
        List<User> userList = Arrays.asList(user1, user2);

        // Configuración del comportamiento del repositorio mock
        when(userRepository.findAll()).thenReturn(userList);

        // Realizar la solicitud GET y validar la respuesta
        mockMvc.perform(get("/api/users/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[0].emailDailyCount").value(3));
    }

    @Test
    @DisplayName("Get user stats with empty list")
    @WithMockUser(username = "testuser@mail.com", authorities = "ADMIN")
    void testGetStatsEmptyList() throws Exception {
        // Configuración del comportamiento del repositorio
        when(userRepository.findAll()).thenReturn(List.of());

        // Realizar la solicitud GET y validar la respuesta
        mockMvc.perform(get("/api/users/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Get user stats with User Role")
    @WithMockUser(username = "testuser@gmail.com", authorities = "USER")
    void testGetStatsWithRoleUser() throws Exception {
        mockMvc.perform(get("/api/users/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
