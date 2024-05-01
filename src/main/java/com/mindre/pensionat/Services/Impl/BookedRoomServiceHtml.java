package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.BookedRoomDto;
import com.mindre.pensionat.Dtos.CustomerDto;
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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookedRoomServiceHtml {
    private static final Logger logger = LoggerFactory.getLogger(BookedRoomServiceHtml.class);

    @Autowired
    private final CustomerRepo customerRepo;
    private final BookedRoomRepo bookedRoomRepo;


    public List<DetailedBookedRoomDto> getAllDetailedBookedRoomDto() {
        List<BookedRoom> bookedRooms = bookedRoomRepo.findAll();
        return bookedRooms.stream()
                .map(this::bookedRoomToDetailedBookedRoomDto)
                .collect(Collectors.toList());
    }
    public DetailedBookedRoomDto bookedRoomToDetailedBookedRoomDto(BookedRoom b) {
        if (b.getCustomer() != null) {
            return DetailedBookedRoomDto.builder()
                    .id(b.getId())
                    .checkIn(b.getCheckIn())
                    .checkOut(b.getCheckOut())
                    .amountPersons(b.getAmountPersons())
                    .customer(new CustomerDto(b.getCustomer().getId(), b.getCustomer().getFirstName(), b.getCustomer().getLastName(),
                            b.getCustomer().getEmail(), b.getCustomer().getPhoneNumber()))
                    .build();
        } else {
            return null;
        }
    }
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
    public void deleteBooking(Long id) {
        bookedRoomRepo.deleteById(id);
    }
    public BookedRoomDto bookedRoomtoBookedRoomDto(BookedRoom b) {
        return BookedRoomDto.builder().id(b.getId()).amountPersons(b.getAmountPersons()).build();
    }
    public BookedRoomDto getBookedRoomDtoById(Long id) {
        return bookedRoomRepo.findById(id)
                .map(this::bookedRoomtoBookedRoomDto)
                .orElseThrow(() -> new IllegalArgumentException("Wrong id: " + id));
    }
    public void updateBookedRoom(Long id, BookedRoomDto bookedRoomDto) {
        BookedRoom bookedRoom = bookedRoomRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("wrong booking Id:" + id));
        bookedRoom.setCheckIn(bookedRoomDto.getCheckIn());
        bookedRoom.setCheckOut(bookedRoomDto.getCheckOut());
        bookedRoomRepo.save(bookedRoom);
    }
}
