package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDtos {

	public static class CreateBookingRequest {
		private Long restaurantId;
		private LocalDate date;
		private LocalTime time;
		private Integer partySize;

		public Long getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(Long restaurantId) {
			this.restaurantId = restaurantId;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public LocalTime getTime() {
			return time;
		}

		public void setTime(LocalTime time) {
			this.time = time;
		}

		public Integer getPartySize() {
			return partySize;
		}

		public void setPartySize(Integer partySize) {
			this.partySize = partySize;
		}
	}

	public static class BookingResponse {
		private Long id;
		private String restaurantName;
		private LocalDate date;
		private LocalTime time;
		private Integer partySize;
		private String status;

		public BookingResponse() {
		}

		public BookingResponse(Long id, String restaurantName, LocalDate date, LocalTime time, Integer partySize,
				String status) {
			this.id = id;
			this.restaurantName = restaurantName;
			this.date = date;
			this.time = time;
			this.partySize = partySize;
			this.status = status;
		}

		public Long getId() {
			return id;
		}

		public String getRestaurantName() {
			return restaurantName;
		}

		public LocalDate getDate() {
			return date;
		}

		public LocalTime getTime() {
			return time;
		}

		public Integer getPartySize() {
			return partySize;
		}

		public String getStatus() {
			return status;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setRestaurantName(String restaurantName) {
			this.restaurantName = restaurantName;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public void setTime(LocalTime time) {
			this.time = time;
		}

		public void setPartySize(Integer partySize) {
			this.partySize = partySize;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}
}
