package com.mindre.pensionat.HtlmControllers;


import com.mindre.pensionat.Dtos.DetailedBookedRoomDto;
import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Models.Event;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomEventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.Impl.BookedRoomServiceHtml;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor

public class roomEventController {

    private final RoomRepo roomRepo;
    private final RoomEventRepo roomEventRepo;
    private static final Logger logger = LoggerFactory.getLogger(roomEventController.class);

    @GetMapping("/events")
    public String showRooms(Model model) {
        List<Room> rooms = roomRepo.findAll();
        model.addAttribute("rooms", rooms);
        return "Events/RoomEvent";
    }

    @GetMapping("/seeEvent")
    public String showRoomEvent(@RequestParam Long roomId, Model model) {
        List<Event> events = roomEventRepo.findByRoomId(roomId);
        model.addAttribute("events", events);
        return "Events/seeEvent";
    }
    }