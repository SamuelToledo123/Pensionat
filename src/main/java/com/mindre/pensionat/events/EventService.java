package com.mindre.pensionat.events;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.EventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public List<Event> findEventByRoomId(Long roomId) {
        return eventRepo.findByRoomId(roomId).stream()
                .map(event -> Event.builder()
                        .id(event.getId())
                        .type(event.getType())
                        .date(event.getDate())
                        .employee(event.getEmployee())
                        .build())
                .collect(Collectors.toList());

    }

}