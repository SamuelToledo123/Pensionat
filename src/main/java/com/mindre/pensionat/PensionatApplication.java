package com.mindre.pensionat;

import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PensionatApplication {
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private CustomerRepo customerRepo;


    public static void main(String[] args) {
        SpringApplication.run(PensionatApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {

            Room singleRoom1 = new Room(1L, "Single Room", 1, 1, true);
            Room singleRoom2 = new Room(2L, "Single Room", 1, 1, true);
            Room doubleRoom1 = new Room(3L, "Double Room", 2, 1, true);
            Room doubleRoom2 = new Room(4L, "Double Room Luxury", 3, 2, true);
            Room doubleRoom3 = new Room(5L, "Double Room Luxury", 4, 2, true);

            roomRepo.save(singleRoom1);
            roomRepo.save(singleRoom2);
            roomRepo.save(doubleRoom1);
            roomRepo.save(doubleRoom2);
            roomRepo.save(doubleRoom3);

        };
    }
}
