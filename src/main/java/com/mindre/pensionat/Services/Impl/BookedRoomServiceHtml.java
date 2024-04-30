package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import com.mindre.pensionat.Repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class BookedRoomServiceHtml {
    private static final Logger logger = LoggerFactory.getLogger(BookedRoomServiceHtml.class);

    @Autowired
    private final CustomerRepo customerRepo;
    private final BookedRoomRepo bookedRoomRepo;
    public void createBooking(DetailedBookedRoomDto detailedBookedRoomDto) {

        try {
            Customer newCustomer = new Customer();
            newCustomer.setFirstName(detailedBookedRoomDto.getCustomerDto().getFirstName());
            newCustomer.setLastName(detailedBookedRoomDto.getCustomerDto().getLastName());
            newCustomer.setEmail(detailedBookedRoomDto.getCustomerDto().getEmail());
            newCustomer.setPhoneNumber(detailedBookedRoomDto.getCustomerDto().getPhoneNumber());
            customerRepo.save(newCustomer);
            logger.info("Saved customer with ID: {}", newCustomer.getId());

            BookedRoom newBookedRoom = new BookedRoom();
            newBookedRoom.setCheckIn(detailedBookedRoomDto.getCheckIn());
            newBookedRoom.setCheckOut(detailedBookedRoomDto.getCheckOut());
            newBookedRoom.setAmountPersons(detailedBookedRoomDto.getAmountPersons());
            newBookedRoom.setCustomer(newCustomer);
            bookedRoomRepo.save(newBookedRoom);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating the booking and customer: " + e.getMessage());
        }
    }
}