package com.mindre.pensionat;

import com.mindre.pensionat.Models.Event;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SeedRoomEvents  {

    @Transactional
    @Bean
    public CommandLineRunner init(RoomRepo roomRepository) {
        return args -> {

            Room room = roomRepository.findById(1L).orElse(null);
            if (room != null) {
                room.addEvent(new Event(1, "Dörren öppnad", "Samuel Tollan", LocalDate.now(), room));
                room.addEvent(new Event(2, "Dörren stängd", "Petra Mede", LocalDate.now(), room));

                try {
                    roomRepository.save(room);
                    System.out.println("room saved");
                } catch (Exception e) {
                    System.out.println("Failed to save events");
                    e.printStackTrace();

                }
            }
        };
    }
}







