package com.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 200)
	private String name;

	@Column(nullable = false, length = 100)
	private String cuisine;

	@Column(name = "total_seats", nullable = false)
	private int totalSeats;

	public Restaurant() {
	}

	public Restaurant(String name, String cuisine, int totalSeats) {
		this.name = name;
		this.cuisine = cuisine;
		this.totalSeats = totalSeats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
}
