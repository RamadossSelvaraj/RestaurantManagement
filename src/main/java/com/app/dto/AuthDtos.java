package com.app.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthDtos {

	public static class LoginRequest {
		@NotBlank
		private String username;
		@NotBlank
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	public static class LoginResponse {
		private Long userId;
		private String username;

		public LoginResponse() {
		}

		public LoginResponse(Long userId, String username) {
			this.userId = userId;
			this.username = username;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	}
}