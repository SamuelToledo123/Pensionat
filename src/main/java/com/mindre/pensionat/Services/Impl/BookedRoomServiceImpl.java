package com.mindre.pensionat.Services.Impl;


import com.mindre.pensionat.Dtos.BookedRoomDto;
import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Services.BookedRoomService;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class BookedRoomServiceImpl implements BookedRoomService {
    //konto

    private final BookedRoomRepo bookedRoomRepo;
    private final CustomerRepo customerRepo;


    public BookedRoomServiceImpl(BookedRoomRepo bookedRoomRepo, CustomerRepo customerRepo) {
        this.bookedRoomRepo = bookedRoomRepo;
        this.customerRepo = customerRepo;
    }

    @Override
    public BookedRoomDto kontoToKontoDto(BookedRoom b) {
        return BookedRoomDto.builder().id(b.getId()).amountPersons(b.getAmountPersons()).build();
    }

    @Override
    public DetailedBookedRoomDto kontoToDtailedKontoDto(BookedRoom b) {
        CustomerDto customerDto = new CustomerDto(b.getCustomer().getId(), b.getCustomer().getFirstName(),b.getCustomer().getLastName(),
                b.getCustomer().getEmail(),b.getCustomer().getPhoneNumber());
        return DetailedBookedRoomDto.builder().id(b.getId()).checkIn(b.getCheckIn()).checkOut(b.getCheckOut())
                .amountPersons(b.getAmountPersons())
                .kund(customerDto).build();
    }

    @Override
    public List<BookedRoom> getBookedRooms() {
        return null;
    }

    @Override
    public String saveBookedRoom(BookedRoom bookedRoom) {
        return null;
    }

    @Override
    public String updateBookedRoom(Long id, BookedRoomDto bookedRoomDto) {
        return null;
    }

    @Override
    public String deleteBookedRoom(Long id) {
        return null;
    }
}



