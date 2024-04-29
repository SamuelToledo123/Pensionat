package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Services.Impl.BookedRoomServiceHtml;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookedRoomHtmlController {

    @Autowired
    private final BookedRoomServiceHtml bookedRoomServiceHtml;

    private static final Logger logger = LoggerFactory.getLogger(BookedRoomServiceHtml.class);

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        DetailedBookedRoomDto booking = new DetailedBookedRoomDto();
        booking.setCustomer(new CustomerDto()); // Initialize nested DTO
        model.addAttribute("booking", booking);
        return "Bookings/CreateBooking";
    }

    @PostMapping("/create")
    public String createBooking(@ModelAttribute("booking") @Valid DetailedBookedRoomDto detailedBookedRoomDto, BindingResult result) {
        logger.info("Create booking method called.");
        if (result.hasErrors()) {
            return "Bookings/CreateBooking";
        }
        try {
            bookedRoomServiceHtml.createBooking(detailedBookedRoomDto);
        } catch (Exception e) {
            System.out.println("Error...");
            return "Bookings/CreateBooking";
        }
        return "redirect:/booking";
    }
}
