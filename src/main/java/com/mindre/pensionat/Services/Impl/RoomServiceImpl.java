package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.events.Event;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @Autowired
    private final RoomRepo repo;

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
        Event event = room.getEvent();


        return null;
    }

}