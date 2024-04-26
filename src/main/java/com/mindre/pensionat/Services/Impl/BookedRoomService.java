package com.mindre.pensionat.Services.Impl;


import com.mindre.pensionat.Dtos.BookedRoomDto;
import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookedRoomService {

    private final BookedRoomRepo bookedRoomRepo;
    private final CustomerServiceImpl customerServiceImpl;


    public BookedRoomDto bookedRoomToBookedroomDto(BookedRoom bookedRoom) {
        return BookedRoomDto.builder()
                .id(bookedRoom.getId())
                .checkIn(bookedRoom.getCheckIn())
                .checkOut(bookedRoom.getCheckOut())
                .amountPersons(bookedRoom.getAmountPersons()).build();
    }

    /*public DetailedBookedRoomDto bookedRoomToDetailedBookedRoomDto(BookedRoom b) {
        return DetailedBookedRoomDto.builder()
                .id(b.getId())
                .checkIn(b.getCheckIn())
                .checkOut(b.getCheckOut())
                .amountPersons(b.getAmountPersons())
                .customers(b.getCustomers()).stream().
                map(customers -> customerServiceImpl.getAllDetailedCustomers().toList)); */

    }



