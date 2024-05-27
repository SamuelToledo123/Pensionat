package com.mindre.pensionat.events;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.EventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private RoomRepo roomRepo;

    public void createEvent(Event event, Long roomId) {
        Room room = roomRepo.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        event.setRoom(room);
        eventRepo.save(event);
    }
}