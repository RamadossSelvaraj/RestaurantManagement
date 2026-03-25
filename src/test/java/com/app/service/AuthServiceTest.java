package com.app.service;

import com.app.entity.User;
import com.app.exception.UnauthorizedException;
import com.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private final UserRepository userRepo = Mockito.mock(UserRepository.class);
    private final AuthService service = new AuthService(userRepo);

    @Test
    void testLoginSuccess() {
        User u = new User("ramadoss", "12345");
        u.setId(1L);

        Mockito.when(userRepo.findByUsernameAndPassword("ramadoss", "12345"))
                .thenReturn(Optional.of(u));

        User result = service.login("ramadoss", "12345");

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testLoginFail() {
        Mockito.when(userRepo.findByUsernameAndPassword("wrong", "wrong"))
                .thenReturn(Optional.empty());

        assertThrows(UnauthorizedException.class, () -> {
            service.login("wrong", "wrong");
        });
    }
}

