package com.mindre.pensionat;

import com.mindre.pensionat.Utils.DataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunDataSeeder implements CommandLineRunner {

    @Autowired
    private DataSeeder dataSeeder;

    public static void main(String[] args) {
        SpringApplication.run(RunDataSeeder.class, args);
    }

    @Override
    public void run(String... args) {
        dataSeeder.seed();
    }
}
