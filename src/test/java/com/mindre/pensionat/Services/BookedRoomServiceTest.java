package com.mindre.pensionat.Services;

import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.Impl.BookedRoomServiceHtml;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookedRoomServiceTest {
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    BookedRoomServiceHtml sut;

    Room room = new Room();

    @BeforeEach
    void init() {

       // Room room = new Room();
        room.setId(1L);
        room.setPricePerNight(200.00);
        roomRepo.save(room);

        ArrayList<BookedRoom> existingBookedRooms = new ArrayList<>();

        BookedRoom existingBooking = new BookedRoom();
        existingBooking.setRoom(room);
        existingBooking.setCheckIn(LocalDate.of(2024, 6, 1));
        existingBooking.setCheckOut(LocalDate.of(2024, 6, 5));
        existingBookedRooms.add(existingBooking);
        room.setBookedRooms(existingBookedRooms);

    }
    @Test
    @Transactional
    public void testRoomAvailability() {

        BookedRoom newBooking = new BookedRoom();
        newBooking.setRoom(room);
        newBooking.setCheckIn(LocalDate.of(2024, 6, 6));
        newBooking.setCheckOut(LocalDate.of(2024, 6, 10));
        roomRepo.save(room);

        assertTrue(sut.roomIsAvailable(room, newBooking,room.getBookedRooms()));
    }

    @Test
    @Transactional
    public void testCheckReservation() {
        Long roomId = 1L;
        BookedRoom bookingRequest = new BookedRoom();
        bookingRequest.setCheckIn(LocalDate.of(2023, 1, 10));
        bookingRequest.setCheckOut(LocalDate.of(2023, 1, 15));

        sut.checkReservation(roomId, bookingRequest);
        assertFalse(roomRepo.findById(roomId).get().getBookedRooms().isEmpty());
    }

}
