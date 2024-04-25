package com.mindre.pensionat.Controllers;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Services.Impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pensionat")
@RequiredArgsConstructor
public class CostumerController {

    @Autowired
    private final CustomerServiceImpl customerServiceImpl;

    @RequestMapping("/detailedcustomers")
    public List<DetailedCustomerDto> getDetailedCustomers(){
        return customerServiceImpl.getAllDetailedCustomers();
    }
    @RequestMapping("/customers")
    public List<Customer> getCustomers() {
        return customerServiceImpl.getCustomers();
    }

    @PostMapping("/saveCustomers")
    public String saveCustomer(@RequestBody Customer customer) {
        return customerServiceImpl.saveCustomer(customer);
    }

    @PutMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            return "Validation Error";
        }
        return customerServiceImpl.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerServiceImpl.deleteCustomer(id);

    }
}
