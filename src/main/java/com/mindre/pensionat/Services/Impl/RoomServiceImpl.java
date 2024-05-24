package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.EventDto;
import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Models.Event;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @Autowired
    private final RoomRepo repo;

    @Override
    public RoomDto findRoomById(Long id) {
        Room room = repo.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        return roomToRoomDto(room);
    }

    @Override
    public List<RoomDto> findAllRooms() {
        return repo.findAll().stream().map(this::roomToRoomDto).collect(Collectors.toList());
    }

    @Override
    public RoomDto roomToRoomDto(Room room) {
        List<EventDto> eventDtos = room.getEvents().stream().map(this::eventToEventDto).collect(Collectors.toList());
        return RoomDto.builder()
                .id(room.getId())
                .roomType(room.getRoomType())
                .roomSize(room.getRoomSize())
                .amountOfBeds(room.getAmountOfBeds())
                .events(eventDtos)
                .build();
    }

    private EventDto eventToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .employeeName(event.getEmployeeName())
                .eventType(event.getEventType())
                .eventTimeStamp(event.getEventTimeStamp())
                .build();
    }
}
