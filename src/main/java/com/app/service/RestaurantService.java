package com.app.service;

import com.app.dto.RestaurantDtos;
import com.app.entity.Restaurant;
import com.app.repository.BookingRepository;
import com.app.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;
    private final BookingRepository bookingRepo;

    public RestaurantService(RestaurantRepository restaurantRepo, BookingRepository bookingRepo) {
        this.restaurantRepo = restaurantRepo;
        this.bookingRepo = bookingRepo;
    }

    /**
     * Search restaurants:
     *  - If date/time NOT provided → return all restaurants with totalSeats as availability
     *  - If date/time provided → return based on real availability
     *  - Cuisine is optional
     *  - Party size is optional
     */
    public List<RestaurantDtos.SearchResult> search(
            LocalDate date,
            LocalTime time,
            Integer partySize,
            String cuisine
    ) {

        // 1️⃣ Fetch all or filtered by cuisine
        List<Restaurant> base = (cuisine == null || cuisine.isBlank())
                ? restaurantRepo.findAll()
                : restaurantRepo.findByCuisineContainingIgnoreCase(cuisine);

        // 2️⃣ Build response list
        return base.stream().map(restaurant -> {

            int availableSeats;

            // CASE A: No date/time → show default availability = total seats
            if (date == null || time == null) {
                availableSeats = restaurant.getTotalSeats();
            }
            // CASE B: Date/time provided → calculate real availability
            else {
                int booked = bookingRepo
                        .findByRestaurantAndDateAndTime(restaurant, date, time)
                        .stream()
                        .mapToInt(b -> b.getPartySize())
                        .sum();

                availableSeats = Math.max(0, restaurant.getTotalSeats() - booked);
            }

            return new RestaurantDtos.SearchResult(
                    restaurant.getId(),
                    restaurant.getName(),
                    restaurant.getCuisine(),
                    restaurant.getTotalSeats(),
                    availableSeats
            );

        })
        // 3️⃣ Filter by party size if given
        .filter(r -> partySize == null || r.getAvailableSeats() >= partySize)

        // 4️⃣ Sort by availability descending
        .sorted(Comparator.comparing(RestaurantDtos.SearchResult::getAvailableSeats).reversed())

        .collect(Collectors.toList());
    }
}