package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.Event;
import com.mindre.pensionat.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomEventRepo extends JpaRepository<Event, Long> {
    List<Event> findByRoomId(Long roomId);
}


