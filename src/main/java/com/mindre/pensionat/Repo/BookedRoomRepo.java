package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedRoomRepo extends JpaRepository<BookedRoom, Long> {
}
