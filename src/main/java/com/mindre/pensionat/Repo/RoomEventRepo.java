package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomEventRepo extends JpaRepository<Room, Long> {
}


