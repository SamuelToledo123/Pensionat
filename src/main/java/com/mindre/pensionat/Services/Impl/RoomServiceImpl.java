package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Repo.EventRepo;
import com.mindre.pensionat.events.Event;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.RoomService;
import com.mindre.pensionat.events.EventDto;
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

    @Autowired
    private EventRepo eventRepo;

    @Override
    public RoomDto findRoomById(Long id) {
        Room room = repo.findById(id).orElseThrow(()-> new RuntimeException("Room not found"));
        return roomToRoomDto(room);
    }

    @Override
    public List<RoomDto> findAllRooms() {
        return repo.findAll().stream().map(room -> roomToRoomDto(room)).toList();

    }
    @Override
    public RoomDto roomToRoomDto(Room room) {
        RoomDto dto = new RoomDto();
        dto.setId(room.getId());
        dto.setRoomType(room.getRoomType());
        dto.setRoomSize(room.getRoomSize());
        dto.setAmountOfBeds(room.getAmountOfBeds());
        return dto;
    }

}