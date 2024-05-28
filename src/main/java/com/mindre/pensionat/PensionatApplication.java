package com.mindre.pensionat;

import com.mindre.pensionat.Models.Room;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Repo.RoomRepo;
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







