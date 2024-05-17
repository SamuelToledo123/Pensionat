package com.mindre.pensionat.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String eventType;
    private String employee;
    private LocalDate timeOfEvent;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;




}
