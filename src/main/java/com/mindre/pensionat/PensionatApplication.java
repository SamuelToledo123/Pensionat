package com.mindre.pensionat;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.security.UserDataSeeder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

@SpringBootApplication
public class PensionatApplication {

    @Autowired
    private UserDataSeeder userDataSeeder;
    private static final Logger logger = LoggerFactory.getLogger(PensionatApplication.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            SpringApplication.run(PensionatApplication.class, args);


        }
    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {


            try {
                userDataSeeder.Seed();
                logger.info("UserDataSeeder Successfully");
            } catch (Exception e) {
                logger.error("Couldn't seed UserData");
            }

        };
    }
}



















    /*
    public static void main(String[] args) {
            if (args.length == 0) {
                SpringApplication.run(PensionatApplication.class, args);

            }else if (Objects.equals(args[0],"fetchcustomers")){
                SpringApplication application = new SpringApplication(FetchContractCustomers.class);
                application.setWebApplicationType(WebApplicationType.NONE);
                application.run(args);
            }
        }
    }

     */







