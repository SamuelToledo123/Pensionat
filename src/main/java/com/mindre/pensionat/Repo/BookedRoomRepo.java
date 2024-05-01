package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.BookedRoom;
import jakarta.validation.constraints.Max;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Date;
import java.util.List;

public interface BookedRoomRepo extends JpaRepository<BookedRoom, Long> {
    boolean existsByCustomerId(Long customerId);
}
