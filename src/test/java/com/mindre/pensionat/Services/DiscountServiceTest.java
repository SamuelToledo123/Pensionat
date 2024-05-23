package com.mindre.pensionat.Services;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.Impl.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DiscountServiceTest {
        @Autowired
        private DiscountService sut;


        @Autowired
        private RoomRepo roomRepo;
    @BeforeEach
    void init() {
        Room room = new Room();
        room.setId(1L);
        room.setPricePerNight(200.00);
        roomRepo.save(room);
    }

    @Test
    void testFindById() {
        Room room = roomRepo.findById(1L).orElseThrow();
        assertNotNull(room);
    }

       @Test
        void calculateBookingCost_NoDiscount() {


            double result = sut.calculateBookingCost(1L, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 2));
            assertEquals(200, result);
        }

        @Test
        void calculateBookingCost_RegularDiscount() {

            double result = sut.calculateBookingCost(1L, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 3));
            assertEquals(398, result);
        }

        @Test
        void calculateBookingCost_BothDiscounts() {

            double result = sut.calculateBookingCost(1L, LocalDate.of(2024, 5, 19), LocalDate.of(2024, 5, 23));
            assertEquals(780, result);
        }

}
