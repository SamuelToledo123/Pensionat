package com.mindre.pensionat.Repo;

import com.mindre.pensionat.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {

    List<Event> findByRoomId(Long roomId);
}
