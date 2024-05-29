package com.mindre.pensionat.Services;

import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Services.Impl.BookedRoomServiceHtml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerRepo customerRepo;
    private static final Logger logger = LoggerFactory.getLogger(BookedRoomServiceHtml.class);

    @BeforeEach
    void init() {
        Customer customer1 = new Customer(1L,
                "Martin",
                "Gripenstedt",
                "Martin@hotmail.com",
                "0707663610", null);

        Customer customer2 = new Customer(2L,
                "Kareem",
                "Nicolah",
                "Kareem@hotmail.com", "0708884030",
                null);

        customerRepo.save(customer1);
        customerRepo.save(customer2);

    }


    @Test
    public void getInfoCustomers() {
        List<Customer> customers = customerRepo.findAll();
        assertNotNull(customers);
        assertEquals(2, customers.size());
    }

    @Test
    public void createCustomer() {

        Customer savedCustomer = customerRepo.findById(2L).orElse(null);

        assertNotNull(savedCustomer, "Customer should be saved and not null");

        assertEquals("Kareem", savedCustomer.getFirstName(), "First name should match");
        assertEquals("Nicolah", savedCustomer.getLastName(), "Last name should match");
        assertEquals("Kareem@hotmail.com", savedCustomer.getEmail(), "Email should match");
        assertEquals("0708884030", savedCustomer.getPhoneNumber(), "PhoneNumber should match");
    }


    @Test
    public void editCustomer() {

        Customer savedCustomer = customerRepo.findById(1L).orElse(null);
        assertNotNull(savedCustomer, "Customer should be saved and not null");

        savedCustomer = Customer.builder()
                .id(savedCustomer.getId())
                .firstName("Martin")
                .lastName("Gripenstedt")
                .email("UpdateMartin@hotmail.com")
                .phoneNumber("0704443361")
                .build();

        customerRepo.save(savedCustomer);

        Customer updatedCustomer = customerRepo.findById(1L).orElse(null);
        assertNotNull(updatedCustomer, "Updated customer should not be null");

        assertEquals("Martin", updatedCustomer.getFirstName(), "First name should match");
        assertEquals("Gripenstedt", updatedCustomer.getLastName(), "Last name should match");
        assertEquals("UpdateMartin@hotmail.com", updatedCustomer.getEmail(), "Email should match");
        assertEquals("0704443361", updatedCustomer.getPhoneNumber(), "Phone number should match");
    }


    @Test
    public void deleteFromPageCustomer() {

        Customer savedCustomer = customerRepo.findById(1L).orElse(null);
        assertNotNull(savedCustomer, "Customer should be saved and not null");

        customerRepo.delete(savedCustomer);

        Customer deletedCustomer = customerRepo.findById(1L).orElse(null);
        assertNull(deletedCustomer, "Deleted customer should not exist in the repository");

        logger.info("Deleted customer with ID: {}", savedCustomer.getId());
    }
}