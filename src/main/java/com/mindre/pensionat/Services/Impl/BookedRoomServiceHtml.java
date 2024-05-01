package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookedRoomServiceHtml {
    private static final Logger logger = LoggerFactory.getLogger(BookedRoomServiceHtml.class);

    @Autowired
    private final CustomerRepo customerRepo;
    private final BookedRoomRepo bookedRoomRepo;
    private final RoomRepo roomRepo;
    public void createBooking(DetailedBookedRoomDto detailedBookedRoomDto) {

        try {

            BookedRoom newBookedRoom = new BookedRoom();
            newBookedRoom.setCheckIn(detailedBookedRoomDto.getCheckIn());
            newBookedRoom.setCheckOut(detailedBookedRoomDto.getCheckOut());
            newBookedRoom.setAmountPersons(detailedBookedRoomDto.getAmountPersons());

            if (!roomIsAvailable(detailedBookedRoomDto, bookedRoomRepo.findAll())) {
                throw new RuntimeException("Room is not available for the specified dates.");
            }

            Customer newCustomer = new Customer();
            newCustomer.setFirstName(detailedBookedRoomDto.getCustomerDto().getFirstName());
            newCustomer.setLastName(detailedBookedRoomDto.getCustomerDto().getLastName());
            newCustomer.setEmail(detailedBookedRoomDto.getCustomerDto().getEmail());
            newCustomer.setPhoneNumber(detailedBookedRoomDto.getCustomerDto().getPhoneNumber());
            customerRepo.save(newCustomer);
            logger.info("Saved customer with ID: {}", newCustomer.getId());

            Room selectedRoom = roomRepo.findById(detailedBookedRoomDto.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));

            BookedRoom newBookedRoom = new BookedRoom();
            newBookedRoom.setCheckIn(detailedBookedRoomDto.getCheckIn());
            newBookedRoom.setCheckOut(detailedBookedRoomDto.getCheckOut());
            newBookedRoom.setAmountPersons(detailedBookedRoomDto.getAmountPersons());
            newBookedRoom.setRoom(selectedRoom);
            newBookedRoom.setCustomer(newCustomer);
            bookedRoomRepo.save(newBookedRoom);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while creating the booking and customer: " + e.getMessage());
        }
    }


    private boolean roomIsAvailable(DetailedBookedRoomDto reservationRequest, List<BookedRoom> existingReservations) {
        return existingReservations.stream()
                .noneMatch(existingBooking ->
                        reservationRequest.getCheckIn().equals(existingBooking.getCheckIn())
                                || reservationRequest.getCheckOut().before(existingBooking.getCheckOut())
                                || (reservationRequest.getCheckIn().after(existingBooking.getCheckIn())
                                && reservationRequest.getCheckIn().before(existingBooking.getCheckOut()))
                                || (reservationRequest.getCheckIn().before(existingBooking.getCheckIn())

                                && reservationRequest.getCheckOut().equals(existingBooking.getCheckOut()))
                                || (reservationRequest.getCheckIn().before(existingBooking.getCheckIn())

                                && reservationRequest.getCheckOut().after(existingBooking.getCheckOut()))

                                || (reservationRequest.getCheckIn().equals(existingBooking.getCheckOut())
                                && reservationRequest.getCheckOut().equals(existingBooking.getCheckIn()))

                                || (reservationRequest.getCheckIn().equals(existingBooking.getCheckOut())
                                && reservationRequest.getCheckOut().equals(reservationRequest.getCheckIn()))
                );
    }
}
