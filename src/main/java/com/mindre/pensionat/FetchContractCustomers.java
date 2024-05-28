package com.mindre.pensionat;


import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mindre.pensionat.Models.AllContractCustomers;
import com.mindre.pensionat.Models.ContractCustomer;
import com.mindre.pensionat.Repo.ContractCustomerRepo;
import com.mindre.pensionat.configuration.IntegrationProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
@RequiredArgsConstructor
public class FetchContractCustomers implements CommandLineRunner {
    @Autowired
    private ContractCustomerRepo repo;
    private static final Logger logger = LoggerFactory.getLogger(FetchContractCustomers.class);
    private final IntegrationProperties properties;
    private final String CONTRACT_URL;
    @Autowired
    public FetchContractCustomers(IntegrationProperties properties) {
        this.properties = properties;
        this.CONTRACT_URL = properties.getContractUrl();
    }

    @Override
    public void run(String... args) throws Exception {

        try {

            System.out.println("Fetching Contract Customers");
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(module);
            AllContractCustomers allContractCustomers = xmlMapper.readValue(new URL(CONTRACT_URL),
                    AllContractCustomers.class);

            for (ContractCustomer c : allContractCustomers.customers) {

                ContractCustomer contractCustomer = new ContractCustomer();
                contractCustomer.setId(c.getId());
                contractCustomer.setCompanyName(c.getCompanyName());
                contractCustomer.setContactName(c.getContactName());
                contractCustomer.setContactTitle(c.getContactTitle());
                contractCustomer.setStreetAddress(c.getStreetAddress());
                contractCustomer.setCity(c.getCity());
                contractCustomer.setPostalCode(c.getPostalCode());
                contractCustomer.setCountry(c.getCountry());
                contractCustomer.setPhone(c.getPhone());
                contractCustomer.setFax(c.getFax());

                repo.save(contractCustomer);


            }
            logger.info("Fetch Contract-customer successful!");
        } catch (Exception e) {
            logger.error("Error while Fetching ContractCustomer" + e);
        }
    }
}
