package com.mindre.pensionat.Services.impl;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @RequestMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    @PostMapping("/saveCustomers")
    public String saveCustomer(@RequestBody Customer customer) {
        customerRepo.save(customer);
        return "Customer was saved successfully";
    }

    @PutMapping("updateCustomer/{id}")
    public String updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) {

        Customer updateCustomer = customerRepo.findById(id).get();
        updateCustomer.setFirstName(customerDto.getFirstName());
        updateCustomer.setLastName(customerDto.getLastName());
        updateCustomer.setEmail(customerDto.getEmail());
        updateCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        customerRepo.save(updateCustomer);

        return "Customer was updated successfully";
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        Customer deleteCustomer = customerRepo.findById(id).get();
        customerRepo.delete(deleteCustomer);
        return "Customer with the id: " + id + " was deleted successfully";

    }

}




