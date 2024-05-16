package com.mindre.pensionat;

import com.mindre.pensionat.Repo.ContractCustomerRepo;
import com.mindre.pensionat.Services.Impl.ContractCustomerServiceXML;
import com.mindre.pensionat.Services.XmlStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class ContractCustomerTestIT {
    @Autowired
    ContractCustomerRepo contractCustomerRepo;
    @Autowired
    XmlStreamProvider xmlStreamProvider;

    @Autowired
    ContractCustomerServiceXML sut;


   //Integration Test. Testar att alla fälten hämtas in.
    @Test
    void getContractCustomersWillFetch() throws IOException {
        Scanner s = new Scanner(sut.xmlStreamProvider.getDataStream()).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        assertTrue(result.contains("<allcustomers>"));
        assertTrue(result.contains("</allcustomers>"));
        assertTrue(result.contains("<customers>"));
        assertTrue(result.contains("</customers>"));
        assertTrue(result.contains("<id>"));
        assertTrue(result.contains("</id>"));
        assertTrue(result.contains("<companyName>"));
        assertTrue(result.contains("</companyName>"));
        assertTrue(result.contains("<contactName>"));
        assertTrue(result.contains("</contactName>"));
        assertTrue(result.contains("<contactTitle>"));
        assertTrue(result.contains("</contactTitle>"));
        assertTrue(result.contains("<streetAddress>"));
        assertTrue(result.contains("</streetAddress>"));
        assertTrue(result.contains("<city>"));
        assertTrue(result.contains("</city>"));
        assertTrue(result.contains("<postalCode>"));
        assertTrue(result.contains("</postalCode>"));
        assertTrue(result.contains("<country>"));
        assertTrue(result.contains("</country>"));
        assertTrue(result.contains("<phone>"));
        assertTrue(result.contains("</phone>"));
        assertTrue(result.contains("<fax>"));
        assertTrue(result.contains("</fax>"));


    }

//WORKING
    @Test
    void fetchAndSaveBooksShouldSaveToDatabase() throws IOException {
        XmlStreamProvider xmlStreamProvider = mock(XmlStreamProvider.class);
        when(xmlStreamProvider.getDataStream()).thenReturn(getClass().getClassLoader().getResourceAsStream("contractcustomers.xml"));

        sut = new ContractCustomerServiceXML(xmlStreamProvider, contractCustomerRepo);

        // Delete all innan säkerställer testet alltid startar fresh
        contractCustomerRepo.deleteAll();

        // Act
        sut.FetchAndSaveContractCustomers();

        //Assert
        assertEquals(3,contractCustomerRepo.count());
    }
}