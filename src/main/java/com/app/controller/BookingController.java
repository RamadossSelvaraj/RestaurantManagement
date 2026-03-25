package com.app.controller;

import com.app.dto.BookingDtos;
import com.app.entity.Booking;
import com.app.entity.User;
import com.app.exception.UnauthorizedException;
import com.app.service.AuthService;
import com.app.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	private final BookingService bookingService;
	private final AuthService authService;

	public BookingController(BookingService bookingService, AuthService authService) {
		this.bookingService = bookingService;
		this.authService = authService;
	}

	@PostMapping
	public BookingDtos.BookingResponse book(@RequestBody BookingDtos.CreateBookingRequest req, HttpSession session) {
		Long uid = (Long) session.getAttribute(AuthController.SESSION_USER_ID);
		if (uid == null)
			throw new UnauthorizedException("Please login");
		User user = authService.getById(uid);

		Booking b = bookingService.createBooking(user, req.getRestaurantId(), req.getDate(), req.getTime(),
				req.getPartySize());
		return bookingService.toResponse(b);
	}

	@GetMapping("/mine")
	public List<BookingDtos.BookingResponse> myBookings(HttpSession session) {
		Long uid = (Long) session.getAttribute(AuthController.SESSION_USER_ID);
		if (uid == null)
			throw new UnauthorizedException("Please login");
		User user = authService.getById(uid);

		return bookingService.getUserBookings(user).stream().map(bookingService::toResponse).toList();
	}
}
