package com.nisum.user_management.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.user_management.UserManagementApplication;
import com.nisum.user_management.domain.port.DeleteUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(classes = UserManagementApplication.class)
class DeleteUserDeleteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DeleteUserRepository deleteUserRepository;

    @Test
    @WithMockUser
    void deleteUser_Successful() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.doNothing().when(deleteUserRepository).execute(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

}