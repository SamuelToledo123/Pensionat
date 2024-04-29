package com.mindre.pensionat.Controllers;


import com.mindre.pensionat.Dtos.BookedRoomDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Services.Impl.BookedRoomServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reservations")
public class BookedRoomController {

    @Autowired
    private final BookedRoomServiceImpl bookedRoomService;

    @RequestMapping("/bookedRooms")
    public List<DetailedBookedRoomDto> getAllReservations(){
        return bookedRoomService.getAllKonto();
    }

    @RequestMapping("/bookedRoom")
    public List<BookedRoom> getReservations() {
        return bookedRoomService.getBookedRooms();
    }

    @PostMapping("/saveReservation")
    public String saveReservation(@RequestBody BookedRoom bookedRoom) {
        return bookedRoomService.saveBookedRoom(bookedRoom);
    }

    @PutMapping("/updateReservation/{id}")
    public String updateReservation(@PathVariable Long id, @Valid @RequestBody BookedRoomDto bookedRoomDto, BindingResult result) {
        if (result.hasErrors()) {
            return "Validation Error";
        }
        return bookedRoomService.updateBookedRoom(id, bookedRoomDto);
    }

    @DeleteMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable Long id) {
        return bookedRoomService.deleteBookedRoom(id);

    }
}

