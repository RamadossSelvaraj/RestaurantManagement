package com.app.controller;

import com.app.dto.AuthDtos;
import com.app.entity.User;
import com.app.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;	

    @MockBean
    private AuthService authService;

    @Test
    void testLogin() throws Exception {
        User user = new User("ramadoss", "12345");
        user.setId(10L);

        Mockito.when(authService.login("ramadoss", "12345"))
                .thenReturn(user);

        mvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"ramadoss\", \"password\":\"12345\"}"))
                .andExpect(jsonPath("$.userId").value(10));
    }
}

