package com.mindre.pensionat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date checkIn;
    private Date checkOut;
    private int amountPersons;
    private boolean available = false;


    //one to many
    @ManyToOne
    @JoinColumn(name = "room_id")
    private  Room room;
    //one to many
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
