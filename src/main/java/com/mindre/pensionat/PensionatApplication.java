package com.mindre.pensionat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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







