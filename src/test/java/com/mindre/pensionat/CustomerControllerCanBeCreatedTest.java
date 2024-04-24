package com.mindre.pensionat;


import com.mindre.pensionat.Controllers.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CustomerControllerCanBeCreatedTest {



    @Autowired
    private CustomerController customerController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(customerController).isNotNull();
    }
}
