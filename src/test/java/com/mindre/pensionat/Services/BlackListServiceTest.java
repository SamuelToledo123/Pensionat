package com.mindre.pensionat.Services;

import com.mindre.pensionat.Services.Impl.BlackListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BlackListServiceTest {
    @Autowired
    BlackListService sut;
    String testEmailWrong = "test@example.com";
    String testEmailRight = "example@hotmail.com";

    @Test
    void testEmailCheckIntegrationWrongEmail() {

        boolean result = sut.checkIfBlacklisted(testEmailWrong);

        assertFalse(result);
    }

    @Test
    void testEmailCheckIntegrationValidEmail() {

        boolean result = sut.checkIfBlacklisted(testEmailRight);

        System.out.println("Integration Test Result for " + testEmailRight + ": " + result);
        assertTrue(result);
    }
}

