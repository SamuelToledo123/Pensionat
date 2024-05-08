package com.mindre.pensionat.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;


@JacksonXmlRootElement(localName = "allcustomers")
public class AllContractCustomers {
    public List<ContractCustomer> customers;
}
