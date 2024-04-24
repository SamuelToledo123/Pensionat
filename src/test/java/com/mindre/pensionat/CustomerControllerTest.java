package com.mindre.pensionat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Services.impl.CustomerService;
import lombok.Builder;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.*;


import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
        public void testGetCustomers() throws Exception {
            Customer customer1 = new Customer(1L, "John", "Doe");
            Customer customer2 = new Customer(2L, "Jane", "Doe");

            List<Customer> customers = Arrays.asList(customer1, customer2);

            when(customerService.getCustomers()).thenReturn(customers);

            mockMvc.perform(get("/customers"))
                    .andExpect(status().isOk())
                     .andExpect((ResultMatcher) content().json("[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":null,\"phoneNumber\":null,\"bookedRooms\":[]},{\"id\":2,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":null,\"phoneNumber\":null,\"bookedRooms\":[]}]"));
}

}