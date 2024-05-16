package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Models.Event;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @Autowired
    private final RoomRepo repo;
    @Override
    public List<RoomDto> findAllRooms(Model model) {
        return repo.findAll().stream().map(room -> roomToRoomDto(room)).toList();

    }

    @Override
    public RoomDto roomToRoomDto(Room room) {
        Event event = room.getEvent();

        return RoomDto.builder()
                .id(room.getId())
                .roomType(room.getRoomType())
                .roomSize(room.getRoomSize())
                .amountOfBeds(room.getAmountOfBeds()).build();
               /* .event(new Event(event.getId(), event.getOpenedDoor(),
                        event.getClosedDoor(), event.getCleanStart(),
                        event.getCleanEnd(),event.getEmployee())).build();*/

    }

}