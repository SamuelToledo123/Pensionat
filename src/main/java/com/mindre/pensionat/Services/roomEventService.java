package com.mindre.pensionat.Services;


import com.mindre.pensionat.Models.Event;
import com.mindre.pensionat.Repo.RoomEventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.Serial;
import java.util.List;

@Service
public class roomEventService  {



    @Autowired
    private RoomEventRepo roomEventRepo;

    public List<Event> getEventsByRoomId(Long roomId) {
        return roomEventRepo.findByRoomId(roomId);

    }



}
