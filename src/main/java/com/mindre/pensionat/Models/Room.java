package com.mindre.pensionat.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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


    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL)
    private List<BookedRoom> bookedRooms;

    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Event> events;

    public Room(Long id, String roomType, int roomSize, int amountOfBeds, boolean available) {
        this.id = id;
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.amountOfBeds = amountOfBeds;
        this.available = available;
        this.bookedRooms = new ArrayList<>();
        this.events =  new ArrayList<>();
    }

    public void addBooking(BookedRoom reservation){
        if (bookedRooms == null){
            bookedRooms = new ArrayList<>();
        }
        bookedRooms.add(reservation);
        reservation.setRoom(this);
        available = true;
    }
    public void addEvent(Event event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
        event.setRoom(this);
    }

}

