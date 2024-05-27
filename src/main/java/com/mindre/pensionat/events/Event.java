package com.mindre.pensionat.events;


import com.mindre.pensionat.Models.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String employee;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDateTime openedDoor;
    private LocalDateTime closedDoor;
    private LocalDateTime cleanStart;
    private LocalDateTime cleanEnd;

}
