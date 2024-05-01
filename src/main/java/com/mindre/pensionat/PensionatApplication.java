package com.mindre.pensionat;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PensionatApplication {



    public static void main(String[] args) {
        SpringApplication.run(PensionatApplication.class, args);
    }

}

