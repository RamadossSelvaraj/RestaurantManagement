package com.app.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("error", "Validation failed");
		body.put("details", ex.getBindingResult().getFieldErrors().stream()
				.map(f -> f.getField() + ": " + f.getDefaultMessage()).toList());
		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> handleUnauthorized(UnauthorizedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFound(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
		return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleOther(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("error", "Internal server error", "message", ex.getMessage()));
	}
}