package com.mindre.pensionat.events;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class RoomCleaned extends Event{
    public String employee;

}
