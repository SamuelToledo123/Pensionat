package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Services.Impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/rooms")
public class RoomHtmlController {

    private final RoomServiceImpl roomService;


    @GetMapping({"", "/"})
    public String findAllRooms(Model model) {
        List<RoomDto> rooms = roomService.findAllRooms();
        model.addAttribute("rooms", rooms);
        return "rooms/index";

    }

    @GetMapping("/info/{id}")
    public String findEventRooms(@PathVariable Long id, Model model) {
        RoomDto room = roomService.findRoomById(id);
        model.addAttribute("room", room);
        return "rooms/status";
    }
}







