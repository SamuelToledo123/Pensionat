package com.mindre.pensionat.events;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.EventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@Component
public class EventSeeder {

    private static final List<String> EMPLOYEES = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

   @Autowired
   public RoomRepo roomRepo;

    @Autowired
    private EventRepo eventRepo;

    @Transactional
    public void seed() {
        Random random = new Random();
        List<Room> availableRooms = roomRepo.findAll();


        LocalDateTime now = LocalDateTime.now();

        IntStream.range(0, 10).forEach(i -> {
            Event event = new Event();
            event.setEmployee(EMPLOYEES.get(random.nextInt(EMPLOYEES.size())));

            if (!availableRooms.isEmpty()) {
                Room room = availableRooms.get(random.nextInt(availableRooms.size()));
                event.setRoom(room);
            }
            event.setOpenedDoor(now);
            event.setClosedDoor(now);
            event.setCleanStart(now);
            event.setCleanEnd(now);


            eventRepo.save(event);
        });
    }
}