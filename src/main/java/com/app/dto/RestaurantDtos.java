package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class RestaurantDtos {

	public static class SearchResult {
		private Long id;
		private String name;
		private String cuisine;
		private int totalSeats;
		private int availableSeats;

		public SearchResult() {
		}

		public SearchResult(Long id, String name, String cuisine, int totalSeats, int availableSeats) {
			this.id = id;
			this.name = name;
			this.cuisine = cuisine;
			this.totalSeats = totalSeats;
			this.availableSeats = availableSeats;
		}

		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getCuisine() {
			return cuisine;
		}

		public int getTotalSeats() {
			return totalSeats;
		}

		public int getAvailableSeats() {
			return availableSeats;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setCuisine(String cuisine) {
			this.cuisine = cuisine;
		}

		public void setTotalSeats(int totalSeats) {
			this.totalSeats = totalSeats;
		}

		public void setAvailableSeats(int availableSeats) {
			this.availableSeats = availableSeats;
		}
	}

	public static class SearchParams {
		private LocalDate date;
		private LocalTime time;
		private Integer partySize;
		private String cuisine;

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

		public String getCuisine() {
			return cuisine;
		}

		public void setCuisine(String cuisine) {
			this.cuisine = cuisine;
		}
	}
}
