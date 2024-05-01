package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.BookedRoomDto;
import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.Impl.BookedRoomServiceHtml;
import com.mindre.pensionat.Services.Impl.BookedRoomServiceImpl;
import jakarta.validation.Valid;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookedRoomHtmlController {

    @Autowired
    private final BookedRoomServiceHtml bookedRoomServiceHtml;
    private final RoomRepo roomRepo;
    private final BookedRoomRepo bookedRoomRepo;

    private static final Logger logger = LoggerFactory.getLogger(BookedRoomServiceHtml.class);

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        DetailedBookedRoomDto booking = new DetailedBookedRoomDto();
        booking.setCustomer(new CustomerDto()); // Initialize nested DTO
        booking.setRoom(new RoomDto());

        List<Room> rooms = roomRepo.findAll();
        model.addAttribute("booking", booking);
        model.addAttribute("rooms", rooms);

        return "Bookings/CreateBooking";
    }

    @PostMapping("/create")
    public String createBooking(@ModelAttribute("booking") @Valid DetailedBookedRoomDto detailedBookedRoomDto, BindingResult result) {
        logger.info("Create booking method called.");
        if (result.hasErrors()) {
            System.out.println(result);
            logger.info("Create booking error");
            return "Bookings/CreateBooking";
        }
        try {
            RoomDto roomDto = detailedBookedRoomDto.getRoom();
            Room room = roomRepo.findById(roomDto.getId()).orElse(null);

            if(room != null) {
                room.setAvailable(false);
                roomRepo.save(room);
            }else{
                logger.error("Room not found" + roomDto.getId());
                return "Bookings/CreateBooking";
            }
            bookedRoomServiceHtml.createBooking(detailedBookedRoomDto);
        } catch (Exception e) {
            System.out.println("Error...");
            return "Bookings/CreateBooking";
        }
        return "redirect:/booking";
    }
    @GetMapping("/edit")
    public String listAllBookings(Model model) {
        List<DetailedBookedRoomDto> allBookings = bookedRoomServiceHtml.getAllDetailedBookedRoomDto();
        model.addAttribute("bookings", allBookings);
        return "bookings/editBookings";
    }
    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookedRoomServiceHtml.deleteBooking(id);
        return "redirect:/booking/edit";
    }
    @GetMapping("/editBooking/{id}")
    public String showEditBookingForm(@PathVariable Long id, Model model) {
        BookedRoomDto bookedRoomDto = bookedRoomServiceHtml.getBookedRoomDtoById(id);
        model.addAttribute("bookingDto", bookedRoomDto);
        return "Bookings/editBookingForm";
    }

    @PostMapping("/editBooking/{id}")
    public String updateBooking(@PathVariable Long id, @ModelAttribute("bookingDto") BookedRoomDto bookedRoomDto) {
        bookedRoomServiceHtml.updateBookedRoom(id, bookedRoomDto);
        return "redirect:/booking/edit";
    }

}
