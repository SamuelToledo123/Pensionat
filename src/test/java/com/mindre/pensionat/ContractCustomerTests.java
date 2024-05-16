package com.mindre.pensionat;

import com.mindre.pensionat.Models.ContractCustomer;
import com.mindre.pensionat.Repo.ContractCustomerRepo;
import com.mindre.pensionat.Services.Impl.ContractCustomerServiceXML;
import com.mindre.pensionat.Services.XmlStreamProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class ContractCustomerTests {
    private XmlStreamProvider xmlStreamProvider = mock(XmlStreamProvider.class);
    private ContractCustomerRepo contractCustomerRepo = mock(ContractCustomerRepo.class);
    ContractCustomerServiceXML sut;

    @BeforeEach()
    void setUp() {
        sut = new ContractCustomerServiceXML (xmlStreamProvider, contractCustomerRepo);


    }


    //WORKING
    @Test
    void whenGetCustomersShouldMapCorrectly() throws IOException {

       //VÄLJER VILKEN XML FIL SOM SKA TESTAS
        when(xmlStreamProvider.getDataStream()).thenReturn(getClass().getClassLoader().getResourceAsStream("contractcustomers.xml"));

        //HÄMTAR CUSTOMERLISTAN
        List<ContractCustomer> result = sut.getContractCustomers();

       //KÖR TESTERNA NEDAN
        assertEquals(3, result.size() );
        assertEquals("60843", result.get(0).getPostalCode() );
        assertEquals("Sverige", result.get(0).getCountry());
        assertEquals("Kramland", result.get(0).getCity() );

    }

}



