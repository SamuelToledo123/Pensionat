package com.mindre.pensionat;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import com.mindre.pensionat.events.EventSeeder;
import com.mindre.pensionat.security.UserDataSeeder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

@SpringBootApplication
public class PensionatApplication {

    @Autowired
    private UserDataSeeder userDataSeeder;

    @Autowired
    private RoomDataSeeder roomDataSeeder;

    @Autowired
    private EventSeeder eventSeeder;
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

            try {
            roomDataSeeder.seed();
            logger.info("RoomDataSeeder successful");
        } catch (Exception e) {
            logger.error("Couldn't seed room data", e);
        }
            try {
               eventSeeder.seed();
                logger.info("event successful");
            } catch (Exception e) {
                logger.error("couldnt seed events", e);
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







