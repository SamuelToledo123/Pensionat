package com.mindre.pensionat;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Services.impl.CustomerService;
import lombok.Builder;
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
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


    @SpringBootTest
    @AutoConfigureMockMvc

    public class CustomerControllerMockTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        CustomerService customerService;

        //HÄMTA EN KUND I TAGET TEST
        @Test
        public void testGetCustomers() throws Exception {

            Customer c1 = new Customer(1L, "Svan", "Holmberg");
            when(customerService.getCustomers()).thenReturn(Arrays.asList(c1));
            this.mvc.perform(get("/customers?id=1"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[{\"id\":1,\"firstName\":\"Svan\",\"lastName\":\"Holmberg\"}]"));
        }

     //HÄMTAR ALLA KUNDER TEST
        @Test
        void testGetAllCustomers() {
            Customer c1 = new Customer(1L, "Svan", "Holmberg");
            Customer c2 = new Customer(2L, "Svan", "Holmberg");
            Customer c3 = new Customer(3L, "Svan", "Holmberg");

            when(customerService.getCustomers()).thenReturn(Arrays.asList(c1, c2, c3));

        }
        @Test
        public void testUpdateCustomer() {}
    }