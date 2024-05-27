package com.mindre.pensionat.events;

import com.mindre.pensionat.Repo.EventRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class EventSeeder {

    private static final List<String> ROOMS = Arrays.asList("Room1", "Room2", "Room3", "Room4", "Room5");
    private static final List<String> EMPLOYEES = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

    @Autowired
    private EventRepo eventRepo;
    @PostConstruct
    public void seed() {
        Random random = new Random();
        IntStream.range(0, 10).forEach(i -> {
            Event event = new Event();
            event.setEmployee(EMPLOYEES.get(random.nextInt(EMPLOYEES.size())));
            event.setRoom(ROOMS.get(random.nextInt(ROOMS.size())));
            event.setOpenedDoor(randomDateTime());
            event.setClosedDoor(randomDateTime());
            event.setCleanStart(randomDateTime());
            event.setCleanEnd(randomDateTime());
            eventRepo.save(event);
        });
    }

    private LocalDateTime randomDateTime() {
        Random random = new Random();
        int year = 2023; // You can adjust the year as needed
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1; // To avoid issues with different month lengths
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}