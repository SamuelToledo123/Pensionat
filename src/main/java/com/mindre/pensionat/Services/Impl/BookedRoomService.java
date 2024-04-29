package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.BookedRoomDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import com.mindre.pensionat.Dtos.MiniBookedRoomDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Customer;


import java.awt.*;
import java.util.List;

public interface BookedRoomService {

    public BookedRoomDto bookedRoomToBookedRoomDTO(BookedRoom b);
    public DetailedCustomerDto bookedRoomToDetailedBookedRoomDto(BookedRoom k);
    public BookedRoom detailedBookedRoomToBookedRoom(DetailedCustomerDto k, Customer customer);
    public List<DetailedBookedRoomDto> getAllBookedRoom();

    public String addBookedRoom(DetailedBookedRoomDto bookedRoom);



}
