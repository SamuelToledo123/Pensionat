package com.mindre.pensionat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    private int roomSize;
    private int amountOfBeds;
    private boolean available = true;


    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL)
    private List<BookedRoom> bookedRooms= new ArrayList<>();

    public Room(Long id, String roomType, int roomSize, int amountOfBeds, boolean available) {
        this.id = id;
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.amountOfBeds = amountOfBeds;
        this.available = available;
    }
}
