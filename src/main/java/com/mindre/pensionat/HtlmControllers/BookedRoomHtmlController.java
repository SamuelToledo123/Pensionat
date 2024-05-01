package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Models.Room;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookedRoomHtmlController {

    @Autowired
    private final BookedRoomServiceHtml bookedRoomServiceHtml;
    private final BookedRoomServiceImpl bookedRoomService;
    private final RoomRepo roomRepo;

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
            logger.error("Error creating booking: " + e.getMessage());
            return "Bookings/CreateBooking";
        }
        return "redirect:/booking";
    }
}
