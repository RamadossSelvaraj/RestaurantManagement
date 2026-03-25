package com.app.repository;

import com.app.entity.Booking;
import com.app.entity.Restaurant;
import com.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserOrderByDateDescTimeDesc(User user);

    List<Booking> findByRestaurantAndDateAndTime(Restaurant restaurant, LocalDate date, LocalTime time);
}