package com.mindre.pensionat.Services.Impl;


import com.mindre.pensionat.Dtos.BookedRoomDto;
import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Services.BookedRoomService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class BookedRoomServiceImpl implements BookedRoomService {
    //konto

    private final BookedRoomRepo bookedRoomRepo;
    private final CustomerRepo customerRepo;



    @Autowired
    public BookedRoomServiceImpl(BookedRoomRepo bookedRoomRepo, CustomerRepo customerRepo) {
        this.bookedRoomRepo = bookedRoomRepo;
        this.customerRepo = customerRepo;
    }

 //
    public List<DetailedBookedRoomDto> getAllKonto() {
       return bookedRoomRepo.findAll().stream().map( k-> kontoToDtailedKontoDto(k)).toList();
    }

    @Override
    public BookedRoomDto kontoToKontoDto(BookedRoom b) {
        return BookedRoomDto.builder().id(b.getId()).amountPersons(b.getAmountPersons()).build();
    }


    @Override
    public DetailedBookedRoomDto kontoToDtailedKontoDto(BookedRoom b) {
        if (b.getCustomer() != null) {
            return DetailedBookedRoomDto.builder()
                    .id(b.getId())
                    .checkIn(b.getCheckIn())
                    .checkOut(b.getCheckOut())
                    .amountPersons(b.getAmountPersons())
                    .kund(new CustomerDto(b.getCustomer().getId(), b.getCustomer().getFirstName(), b.getCustomer().getLastName(),
                            b.getCustomer().getEmail(), b.getCustomer().getPhoneNumber()))
                    .build();
        } else {
            return null;
        }
    }


    @Override
    public List<BookedRoom> getBookedRooms() {
        return null;
    }

    @Override
    public String saveBookedRoom(@RequestBody BookedRoom bookedRoom) {
        bookedRoomRepo.save(bookedRoom);
        return "saved bookedRoom";
    }

    @Override
    public String updateBookedRoom(@PathVariable Long id, @Valid @RequestBody BookedRoomDto bookedRoomDto) {

        BookedRoom updateBookedRoom = bookedRoomRepo.findById(id).get();
        updateBookedRoom.setAmountPersons(bookedRoomDto.getAmountPersons());
        updateBookedRoom.setCheckIn(bookedRoomDto.getCheckIn());
        updateBookedRoom.setCheckOut(bookedRoomDto.getCheckOut());
        bookedRoomRepo.save(updateBookedRoom);

        return "Booked-room was updated ";
    }

    @Override
    public String deleteBookedRoom(Long id) {
        BookedRoom deleteBookedRoom = bookedRoomRepo.findById(id).get();
        bookedRoomRepo.delete(deleteBookedRoom);
        return "Booked-Room with the id: " + id + " was deleted successfully";

    }
}



