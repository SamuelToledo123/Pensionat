package com.mindre.pensionat.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
public class Queue {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="Id")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomIdCSV() {
        return roomIdCSV;
    }

    public void setRoomIdCSV(String roomIdCSV) {
        this.roomIdCSV = roomIdCSV;
    }

    @Column(name="Name")
    private String name;

    @Column(name="RoomIdCSV")
    private String roomIdCSV;

    @Column(name="MessagesToSend")
    private int messagesToSend;



    public int getMessagesToSend() {
        return messagesToSend;
    }

    public void setMessagesToSend(int messagesToSend) {
        this.messagesToSend = messagesToSend;
    }


}
