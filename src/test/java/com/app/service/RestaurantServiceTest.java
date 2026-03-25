package com.app.service;

import com.app.dto.RestaurantDtos;
import com.app.entity.Restaurant;
import com.app.repository.BookingRepository;
import com.app.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    private final RestaurantRepository restaurantRepo = Mockito.mock(RestaurantRepository.class);
    private final BookingRepository bookingRepo = Mockito.mock(BookingRepository.class);
    private final RestaurantService service = new RestaurantService(restaurantRepo, bookingRepo);

    @Test
    void testSearchAllWithoutDateTime() {
        Restaurant r1 = new Restaurant("Marina Spice", "Indian", 50);
        r1.setId(1L);

        Mockito.when(restaurantRepo.findAll()).thenReturn(List.of(r1));

        List<RestaurantDtos.SearchResult> result = service.search(null, null, null, null);

        assertEquals(1, result.size());
        assertEquals(50, result.get(0).getAvailableSeats());
    }

    @Test
    void testSearchWithFilters() {
        Restaurant r = new Restaurant("Test Resto", "Chinese", 40);
        r.setId(10L);

        Mockito.when(restaurantRepo.findByCuisineContainingIgnoreCase("Chinese"))
                .thenReturn(List.of(r));

        Mockito.when(bookingRepo.findByRestaurantAndDateAndTime(
                Mockito.eq(r),
                Mockito.eq(LocalDate.of(2026, 3, 12)),
                Mockito.eq(LocalTime.of(18, 30))
        )).thenReturn(List.of());

        List<RestaurantDtos.SearchResult> list =
                service.search(LocalDate.of(2026, 3, 12), LocalTime.of(18, 30), 2, "Chinese");

        assertEquals(1, list.size());
        assertEquals(40, list.get(0).getAvailableSeats());
    }
}
