package com.mindre.pensionat;

import com.mindre.pensionat.Models.Room;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RoomTest {


   //TEST TO SEE IF ROOMS ARE CREATABLE
    @Test
    public void createRooms() {
        Room room1 = Room.builder()
                .roomType("Single")
                .roomSize(20)
                .amountOfBeds(1)
                .available(true)
                .build();


        Room room2 = Room.builder()
                .roomType("Double")
                .roomSize(30)
                .amountOfBeds(2)
                .available(true)
                .build();

        Room room3 = Room.builder()
                .roomType("Double")
                .roomSize(35)
                .amountOfBeds(4)
                .available(false)
                .build();


        System.out.println(room1);
        System.out.println(room2);
        System.out.println(room3);
    }
}
