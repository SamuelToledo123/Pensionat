package com.mindre.pensionat.Services;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import com.mindre.pensionat.Models.Customer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//kund
public interface CustomerService {

    public List<DetailedCustomerDto> getAllKunder();

    public CustomerDto kundToKundDto(Customer c);
    public DetailedCustomerDto kundToDetailedKundDto(Customer c);

    public List<Customer> getCustomers();

    public String saveCustomer(@RequestBody Customer customer);

    public String updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto);

    public String deleteCustomer(@PathVariable Long id);
}
