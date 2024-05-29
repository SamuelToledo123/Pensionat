package com.mindre.pensionat.Services;


import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.EventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.Impl.RoomServiceImpl;
import com.mindre.pensionat.events.Event;
import com.mindre.pensionat.events.EventService;
import com.mindre.pensionat.events.RoomCleaned;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional
public class RoomServiceTest {

    @Autowired
    RoomServiceImpl roomService;

    @Autowired
    EventService eventService;

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    EventRepo eventRepo;

    @BeforeEach
    public void setUp() {

        roomRepo.deleteAll();
        eventRepo.deleteAll();

        Room room1 = new Room();
        room1.setRoomType("Single");
        room1.setRoomSize(1);
        room1.setAmountOfBeds(1);
        roomRepo.save(room1);

        Room room2 = new Room();
        room2.setRoomType("Double");
        room2.setRoomSize(2);
        room2.setAmountOfBeds(2);
        roomRepo.save(room2);

        Event event1 = new Event();
        event1.setEmployee("Martin");
        event1.setType("RoomCleaned");
        event1.setRoom(room1);
        eventRepo.save(event1);

        Event event2 = new Event();
        event2.setEmployee("Martin");
        event2.setType("DoorOpened");
        event2.setRoom(room2);
        eventRepo.save(event2);

    }
    @Test
    public void testFindRoomById() {

        Room room1 = roomRepo.findAll().get(0);
        RoomDto roomDto = roomService.findRoomById(room1.getId());
        assertNotNull(roomDto);
        assertEquals(roomDto.getId(), roomDto.getId());
        assertEquals(roomDto.getRoomSize(), roomDto.getRoomSize());
        assertEquals(room1.getRoomSize(), roomDto.getRoomSize());
        assertEquals(room1.getAmountOfBeds(), roomDto.getAmountOfBeds());
    }

    @Test
    @Transactional

    public void testFindAllRooms() {

        List<RoomDto> roomDtoList = roomService.findAllRooms();
        assertNotNull(roomDtoList);
        assertEquals(2, roomDtoList.size());

        RoomDto room1 = roomDtoList.get(0);
        RoomDto room2 = roomDtoList.get(1);

        assertEquals("Single", room1.getRoomType());
        assertEquals("Double", room2.getRoomType());

        assertEquals(1, room1.getRoomSize());
        assertEquals(2, room2.getRoomSize());
    }

@Test
    public void findEventByRoomId() {

        Long roomId = roomRepo.findAll().get(1).getId();
        List<Event> events = eventRepo.findByRoomId(roomId);
        List<Event> event = eventService.findEventByRoomId(roomId);

        Event event1 = event.get(0);

        assertNotNull(events);
        assertNotNull(event);
        assertEquals(1, event.size());
        assertEquals(1, events.size());
        assertEquals("Martin", event1.getEmployee());



}


}
