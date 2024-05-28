package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class DiscountService {

    @Autowired
    private RoomRepo roomRepo;
   public Double calculateBookingCost(long roomId, LocalDate checkIn, LocalDate checkOut) {
        Room room = roomRepo.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        double pricePerNight = room.getPricePerNight();

        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        days = Math.max(days, 1);

        double basePrice = pricePerNight * days;
        double discountRate = (days >= 2) ? 0.005 : 0.0;

        boolean sundayToMondayIncluded = checkSundayToMonday(checkIn,checkOut);

        if (sundayToMondayIncluded) {
            discountRate += 0.02;
        }

        double discount = basePrice * discountRate;
        double totalPrice = basePrice - discount;

        return totalPrice;
    }

    protected boolean checkSundayToMonday(LocalDate checkIn, LocalDate checkOut){

        boolean sundayToMondayIncluded = false;
        while (checkIn.isBefore(checkOut)) {
            if (checkIn.getDayOfWeek() == DayOfWeek.SUNDAY && checkIn.plusDays(1).getDayOfWeek() == DayOfWeek.MONDAY) {
                sundayToMondayIncluded = true;
                break;
            }
            checkIn = checkIn.plusDays(1);
        }

       return sundayToMondayIncluded;
    }
}
