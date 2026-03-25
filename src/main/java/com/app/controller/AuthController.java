package com.app.controller;

import com.app.dto.AuthDtos;
import com.app.entity.User;
import com.app.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	public static final String SESSION_USER_ID = "USER_ID";

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthDtos.LoginResponse> login(@Valid @RequestBody AuthDtos.LoginRequest req,
			HttpSession session) {
		User user = authService.login(req.getUsername(), req.getPassword());
		session.setAttribute(SESSION_USER_ID, user.getId());
		return ResponseEntity.ok(new AuthDtos.LoginResponse(user.getId(), user.getUsername()));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/me")
	public ResponseEntity<AuthDtos.LoginResponse> me(HttpSession session) {
		Long uid = (Long) session.getAttribute(SESSION_USER_ID);
		if (uid == null)
			return ResponseEntity.status(401).build();
		User user = authService.getById(uid);
		return ResponseEntity.ok(new AuthDtos.LoginResponse(user.getId(), user.getUsername()));
	}
}
