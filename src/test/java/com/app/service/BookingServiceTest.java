package com.app.service;

import com.app.entity.Booking;
import com.app.entity.Restaurant;
import com.app.entity.User;
import com.app.exception.BadRequestException;
import com.app.exception.NotFoundException;
import com.app.repository.BookingRepository;
import com.app.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private final BookingRepository bookingRepo = Mockito.mock(BookingRepository.class);
    private final RestaurantRepository restaurantRepo = Mockito.mock(RestaurantRepository.class);
    private final BookingService service = new BookingService(bookingRepo, restaurantRepo);

    @Test
    void testCreateBookingSuccess() {
        User user = new User("ramadoss", "12345");
        user.setId(1L);

        Restaurant rest = new Restaurant("Marina Spice", "Indian", 50);
        rest.setId(10L);

        Mockito.when(restaurantRepo.findById(10L)).thenReturn(Optional.of(rest));
        Mockito.when(bookingRepo.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);

        Booking booking = service.createBooking(
                user,
                10L,
                LocalDate.of(2026, 3, 11),
                LocalTime.of(18, 0),
                2
        );

        assertNotNull(booking);
        assertEquals("CONFIRMED", booking.getStatus());
    }

    @Test
    void testCreateBookingRestaurantNotFound() {
        Mockito.when(restaurantRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.createBooking(new User(), 99L, LocalDate.now(), LocalTime.now(), 2);
        });
    }

    @Test
    void testCreateBookingInvalidPartySize() {
        Restaurant rest = new Restaurant("X", "Y", 20);
        rest.setId(1L);

        Mockito.when(restaurantRepo.findById(1L)).thenReturn(Optional.of(rest));

        assertThrows(BadRequestException.class, () -> {
            service.createBooking(new User(), 1L, LocalDate.now(), LocalTime.now(), 0);
        });
    }
}
