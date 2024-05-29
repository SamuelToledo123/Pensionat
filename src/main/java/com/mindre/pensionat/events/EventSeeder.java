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

    private static final List<String> EMPLOYEES = Arrays.asList("Kareem", "Martin", "Samuel", "David", "Jireel");

   @Autowired
   public RoomRepo roomRepo;

    @Autowired
    private EventRepo eventRepo;
    private final Random random = new Random();

    LocalDateTime time;

    @Transactional
    public void seed() {

        List<Room> availableRooms = roomRepo.findAll();
        LocalDateTime now = LocalDateTime.now();
        IntStream.range(0, 20).forEach(i -> {
            Event event = createRandomEvent(now, availableRooms);
            eventRepo.save(event);

            eventRepo.save(event);
        });
    }

    private Event createRandomEvent(LocalDateTime timeStamp, List<Room> availableRooms) {
        String eventType = getRandomEventType();
        switch (eventType) {
            case "RoomClosed":
                return roomClosedEvent(timeStamp, availableRooms);
            case "RoomCleaned":
                return roomCleanedEvent(timeStamp, availableRooms);
            case "RoomOpened":
                return roomOpenedEvent(timeStamp, availableRooms);
            default:
                throw new IllegalArgumentException("Invalid event type: " + eventType);
        }

    }

    private Room getRandomRoom(List<Room> availableRooms) {
        if (!availableRooms.isEmpty()) {
            return availableRooms.get(random.nextInt(availableRooms.size()));
        }
        return null;
    }

    private String getRandomEventType() {
        List<String> eventTypes = Arrays.asList("RoomClosed", "RoomCleaned", "RoomOpened");
        return eventTypes.get(new Random().nextInt(eventTypes.size()));
    }


    private RoomCleaned roomCleanedEvent(LocalDateTime time, List<Room> availableRooms) {

        RoomCleaned event = new RoomCleaned();
        event.setType("RoomCleaned");
        event.setDate(time);
        event.setRoom(getRandomRoom(availableRooms));
        event.setEmployee(EMPLOYEES.get(random.nextInt(EMPLOYEES.size())));
        return event;
    }

        private RoomClosed roomClosedEvent(LocalDateTime time, List<Room> availableRooms) {

            RoomClosed event = new RoomClosed();
            event.setDate(time);
            event.setType("RoomClosed");
            event.setRoom(getRandomRoom(availableRooms));
            event.setEmployee(EMPLOYEES.get(random.nextInt(EMPLOYEES.size())));
            return event;
        }

            private RoomOpened roomOpenedEvent(LocalDateTime time, List<Room> availableRooms) {

                RoomOpened event = new RoomOpened();
                event.setDate(time);
                event.setType("RoomOpened");
                event.setRoom(getRandomRoom(availableRooms));
                event.setEmployee(EMPLOYEES.get(random.nextInt(EMPLOYEES.size())));
                return event;
            }

}
