package com.mindre.pensionat.Controllers;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.CustomerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pensionat")
public class CostumerController {

    @Autowired
    private final CustomerRepo customerRepo;
    public CostumerController(CustomerRepo customerRepo) {
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
    public String updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto, BindingResult result) {

        try {


            Customer updateCustomer = customerRepo.findById(id).get();
            updateCustomer.setFirstName(customerDto.getFirstName());
            updateCustomer.setLastName(customerDto.getLastName());
            updateCustomer.setEmail(customerDto.getEmail());
            updateCustomer.setPhoneNumber(customerDto.getPhoneNumber());
            customerRepo.save(updateCustomer);

        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());

        }
        return "Customer was updated successfully";
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        Customer deleteCustomer = customerRepo.findById(id).get();
        customerRepo.delete(deleteCustomer);
        return "Customer with the id: " + id + " was deleted successfully";

    }
}
