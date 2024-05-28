package com.mindre.pensionat.Services;


import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.EventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.Services.Impl.RoomServiceImpl;
import com.mindre.pensionat.events.Event;
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
    RoomRepo roomRepo;

    @Autowired
    EventRepo eventRepo;

    @BeforeEach
    public void setUp() {
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
    public void testFindAllRooms() {
        List<RoomDto> roomDtoList = roomService.findAllRooms();
        assertEquals(2, roomDtoList.size());

    }
}
