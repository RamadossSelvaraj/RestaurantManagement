package com.app.service;

import com.app.entity.User;
import com.app.exception.UnauthorizedException;
import com.app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User login(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));
    }

    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UnauthorizedException("User not found"));
    }
}
