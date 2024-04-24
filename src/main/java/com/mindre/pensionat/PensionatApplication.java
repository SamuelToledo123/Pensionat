package com.mindre.pensionat;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PensionatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PensionatApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoomRepo repo) {
        return (args) -> {
            Room room1 = Room.builder()
                    .roomType("Single")
                    .roomSize(20)
                    .amountOfBeds(1)
                    .available(true)
                    .build();

            Room room2 = Room.builder()
                    .roomType("Single")
                    .roomSize(23)
                    .amountOfBeds(1)
                    .available(true)
                    .build();

            Room room3 = Room.builder()
                    .roomType("Double")
                    .roomSize(40)
                    .amountOfBeds(2)
                    .available(false)
                    .build();

            Room room4 = Room.builder()
                    .roomType("Double")
                    .roomSize(35)
                    .amountOfBeds(2)
                    .available(false)
                    .build();

            Room room5 = Room.builder()
                    .roomType("Double")
                    .roomSize(32)
                    .amountOfBeds(1)
                    .available(false)
                    .build();

           repo.save(room1);
           repo.save(room2);
           repo.save(room3);
           repo.save(room4);
           repo.save(room5);



        };
    }


}
