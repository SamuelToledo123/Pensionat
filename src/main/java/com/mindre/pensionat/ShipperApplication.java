package com.mindre.pensionat;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mindre.pensionat.Models.Shippers;
import com.mindre.pensionat.Repo.ShippersRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;


@Component
@RequiredArgsConstructor
public class ShipperApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ShipperApplication.class);
    private final ShippersRepo repo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Fetch shippers");

      try{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Shippers[] shippers = objectMapper.readValue(new URL("https://javaintegration.systementor.se/shippers"),
                Shippers[].class);

        for (Shippers shipper : shippers) {

            Shippers shippers1 = new Shippers();
            shippers1.setId(shipper.getId());
            shippers1.setCompanyName(shipper.getCompanyName());
            shippers1.setPhone(shipper.getPhone());

            repo.save(shippers1);
            logger.info("Fetched shipper was succesfull");


        }
    } catch (Exception e) {
          logger.error("Fetching shippers error" + e);
      }

    }
}
