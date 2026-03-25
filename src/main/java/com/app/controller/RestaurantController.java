package com.app.controller;

import com.app.dto.RestaurantDtos;
import com.app.service.RestaurantService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

	private final RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	// Example:
	// /api/restaurants/search?date=2026-03-11&time=19:30&partySize=4&cuisine=Indian
	@GetMapping("/search")
	public List<RestaurantDtos.SearchResult> search(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time,
			@RequestParam(required = false) Integer partySize, @RequestParam(required = false) String cuisine) {
		return restaurantService.search(date, time, partySize, cuisine);
	}
}
