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

    ContractCustomerServiceXML sut;

    @Test
    void getContractCustomersWillFetch() throws IOException {
        Scanner s = new Scanner(sut.xmlStreamProvider.getDataStream()).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        assertTrue(  result.contains("<catalog>") );
        assertTrue(  result.contains("</catalog>") );
        assertTrue(  result.contains("<author>") );
        assertTrue(  result.contains("</author>") );
        assertTrue(  result.contains("<title>") );
        assertTrue(  result.contains("</title>") );
        assertTrue(  result.contains("<genre>") );
        assertTrue(  result.contains("</genre>") );
        assertTrue(  result.contains("<price>") );
        assertTrue(  result.contains("</price>") );
        assertTrue(  result.contains("<publish_date>") );
        assertTrue(  result.contains("</publish_date>") );
        assertTrue(  result.contains("<description>") );
        assertTrue(  result.contains("</description>") );

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