package com.mindre.pensionat;

import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import com.mindre.pensionat.Repo.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class PensionatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PensionatApplication.class, args);
    }



   @Bean
    CommandLineRunner Pensionat(CustomerRepo customerRepo, BookedRoomRepo bookedRoomRepo) {
        return args -> {

            Customer customer1 = new Customer(null, "John", "Doe", "john.doe@example.com", "1234567890", null);
            Customer customer2 = new Customer(null, "Jane", "Doe", "jane.doe@example.com", "0987654321", null);


            customer1 = customerRepo.save(customer1);
            customer2 = customerRepo.save(customer2);


            BookedRoom bookedRoom1 = new BookedRoom(null, new Date(), new Date(), 2, false, null, customer1);
            BookedRoom bookedRoom2 = new BookedRoom(null, new Date(), new Date(), 1, false, null, customer2);


            bookedRoomRepo.saveAll(Arrays.asList(bookedRoom1, bookedRoom2));
        };
    }
}
