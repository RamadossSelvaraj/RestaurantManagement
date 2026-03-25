package com.app.service;

import com.app.dto.BookingDtos;
import com.app.entity.Booking;
import com.app.entity.Restaurant;
import com.app.entity.User;
import com.app.exception.BadRequestException;
import com.app.exception.NotFoundException;
import com.app.repository.BookingRepository;
import com.app.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final RestaurantRepository restaurantRepo;

    public BookingService(BookingRepository bookingRepo, RestaurantRepository restaurantRepo) {
        this.bookingRepo = bookingRepo;
        this.restaurantRepo = restaurantRepo;
    }

    /**
     * Create booking WITHOUT checking seat availability.
     * Always insert booking when user confirms.
     */
    public Booking createBooking(User user,
                                 Long restaurantId,
                                 LocalDate date,
                                 LocalTime time,
                                 int partySize) {

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        if (partySize <= 0) {
            throw new BadRequestException("Party size must be greater than zero");
        }

        // ⬇ NO more availability checking — as requested
        Booking booking = new Booking(
                user,
                restaurant,
                date,
                time,
                partySize,
                "CONFIRMED"
        );

        return bookingRepo.save(booking);
    }

    /**
     * Get all bookings for logged-in user
     */
    public List<Booking> getUserBookings(User user) {
        return bookingRepo.findByUserOrderByDateDescTimeDesc(user);
    }

    /**
     * Convert booking entity to response DTO
     */
    public BookingDtos.BookingResponse toResponse(Booking b) {
        return new BookingDtos.BookingResponse(
                b.getId(),
                b.getRestaurant().getName(),
                b.getDate(),
                b.getTime(),
                b.getPartySize(),
                b.getStatus()
        );
    }
}