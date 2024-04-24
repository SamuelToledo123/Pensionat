package com.mindre.pensionat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    //private double price;
    private int roomSize;
    private int amountOfBeds;
    private boolean available = false;

    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL)
    private List<BookedRoom> bookedRooms= new ArrayList<>();

}
