package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Models.AllContractCustomers;
import com.mindre.pensionat.Models.ContractCustomer;
import com.mindre.pensionat.Services.XmlStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.catalog.Catalog;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class ContractCustomerServiceXML {
    XmlStreamProvider xmlStreamProvider;

    @Autowired
    public ContractCustomerServiceXML(XmlStreamProvider xmlStreamProvider) {
        this.xmlStreamProvider = xmlStreamProvider;
    }

    public List<ContractCustomer> getContractCustomers() throws IOException {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        InputStream stream = xmlStreamProvider.getDataStream();
        AllContractCustomers theContractCustomers = xmlMapper.readValue(stream, AllContractCustomers.class);

        return theContractCustomers.customers;
       }





}
