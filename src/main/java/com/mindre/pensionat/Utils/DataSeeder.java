package com.mindre.pensionat.Utils;

import com.github.javafaker.Faker;
import com.mindre.pensionat.Models.Event;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.EventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class DataSeeder {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private RoomRepo roomRepo;

    private final Faker faker = new Faker();

    public void seed() {
        if (eventRepo.count() > 0) {
            return;
        }

        List<Room> rooms = roomRepo.findAll();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found in the database.");
            return;
        }

        for (int i = 0; i < 10; i++) {
            Event event = createRandomEvent();
            eventRepo.save(event);

            Room room = rooms.get(faker.random().nextInt(rooms.size()));
            event.setRoom(room);
            room.getEvents().add(event);
            roomRepo.save(room);
        }
    }

    private Event createRandomEvent() {
        Event event = new Event();
        event.setEmployeeName(faker.name().fullName());
        event.setEventType(randomEventType());
        event.setEventTimeStamp(getRandomTimeStamp());
        return event;
    }

    private String randomEventType() {
        String[] eventTypes = {"Dörren öppnad", "Dörren stängd", "Städning påbörjad", "Städning avslutad"};
        int randomIndex = faker.random().nextInt(eventTypes.length);
        return eventTypes[randomIndex];
    }

    private LocalDateTime getRandomTimeStamp() {
        return LocalDateTime.now().minusDays(new Random().nextInt(30));
    }
}
