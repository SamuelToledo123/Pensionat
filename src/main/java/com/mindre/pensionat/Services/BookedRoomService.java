package com.mindre.pensionat.Services;

import com.mindre.pensionat.Dtos.BookedRoomDto;
import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Customer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//konto
public interface BookedRoomService {

    public BookedRoomDto kontoToKontoDto(BookedRoom b);
    public DetailedBookedRoomDto kontoToDtailedKontoDto(BookedRoom b);

    public List<BookedRoom> getBookedRooms();

    public String saveBookedRoom(@RequestBody BookedRoom bookedRoom);

    public String updateBookedRoom(@PathVariable Long id, @Valid @RequestBody BookedRoomDto bookedRoomDto);

    public String deleteBookedRoom(@PathVariable Long id);
}



