package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Models.AllContractCustomers;
import com.mindre.pensionat.Models.ContractCustomer;
import com.mindre.pensionat.Repo.ContractCustomerRepo;
import com.mindre.pensionat.Services.XmlStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.catalog.Catalog;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;


@Service
public class ContractCustomerServiceXML {
    public XmlStreamProvider xmlStreamProvider;
    public ContractCustomerRepo contractCustomerRepo;

    @Autowired
    public ContractCustomerServiceXML(XmlStreamProvider xmlStreamProvider, ContractCustomerRepo contractCustomerRepo) {
        this.xmlStreamProvider = xmlStreamProvider;
        this.contractCustomerRepo = contractCustomerRepo;
    }

    public List<ContractCustomer> getContractCustomers() throws IOException {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        InputStream stream = xmlStreamProvider.getDataStream();
        AllContractCustomers theContractCustomers = xmlMapper.readValue(stream, AllContractCustomers.class);

        return theContractCustomers.customers;
       }

    public void FetchAndSaveContractCustomers() throws IOException {
        for(ContractCustomer c : getContractCustomers()){
            Optional<ContractCustomer> theCustomer = contractCustomerRepo.findById(c.getId());
            if(theCustomer.isEmpty()){
                theCustomer = Optional.of(new ContractCustomer());
            }

            theCustomer.get().setId(c.getId());
            theCustomer.get().setCountry(c.getCountry());
            theCustomer.get().setFax(c.getFax());
            theCustomer.get().setCity(c.getCity());
            theCustomer.get().setPhone(c.getPhone());
            theCustomer.get().setContactName(c.getContactName());
            theCustomer.get().setContactTitle(c.getContactTitle());
            theCustomer.get().setStreetAddress(c.getStreetAddress());
            theCustomer.get().setPostalCode(c.getPostalCode());
            contractCustomerRepo.save(theCustomer.get());
        }

    }





}
