package com.app.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@Column(name = "booking_date", nullable = false)
	private LocalDate date;

	@Column(name = "booking_time", nullable = false)
	private LocalTime time;

	@Column(name = "party_size", nullable = false)
	private int partySize;

	@Column(nullable = false, length = 50)
	private String status; // e.g., CONFIRMED

	public Booking() {
	}

	public Booking(User user, Restaurant restaurant, LocalDate date, LocalTime time, int partySize, String status) {
		this.user = user;
		this.restaurant = restaurant;
		this.date = date;
		this.time = time;
		this.partySize = partySize;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public int getPartySize() {
		return partySize;
	}

	public String getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
