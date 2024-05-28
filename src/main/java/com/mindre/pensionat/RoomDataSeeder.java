package com.mindre.pensionat;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoomDataSeeder {

    private final RoomRepo roomrepo;

    @Autowired
    public RoomDataSeeder(RoomRepo roomRepo) {
        this.roomrepo = roomRepo;
    }

    public void seed() {
        Room room1 = new Room(1L, "Single", 1, 1, 499, true );
        Room room2 = new Room(2L, "Single", 1, 1, 358, true );
        Room room3 = new Room(3L, "Double", 2, 1, 897, true );
        Room room4 = new Room(4L, "Double", 2, 2, 1478, true );
        Room room5 = new Room(5L, "Double Luxury", 3, 2, 1900, true );

        roomrepo.save(room1);
        roomrepo.save(room2);
        roomrepo.save(room3);
        roomrepo.save(room4);
        roomrepo.save(room5);

    }

}
