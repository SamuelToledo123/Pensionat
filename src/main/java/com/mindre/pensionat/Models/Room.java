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
    private boolean available = false;
    private double pricePerNight;


    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL)
    private List<BookedRoom> bookedRooms;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Room(Long id, String roomType, int roomSize, int amountOfBeds, boolean available) {
        this.id = id;
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.amountOfBeds = amountOfBeds;
        this.available = available;
        this.bookedRooms = new ArrayList<>();
    }

    public Room(long l, int i) {
    }

    public void addBooking(BookedRoom reservation){
        if (bookedRooms == null){
            bookedRooms = new ArrayList<>();
        }
        bookedRooms.add(reservation);
        reservation.setRoom(this);
        available = true;
    }
}

