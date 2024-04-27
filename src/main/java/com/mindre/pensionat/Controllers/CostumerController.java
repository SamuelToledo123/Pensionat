package com.mindre.pensionat.Controllers;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Services.CustomerService;
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
    private final CustomerService customerService;

    @RequestMapping("kunder")
    public List<DetailedCustomerDto> getAllKunder(){
        return customerService.getAllKunder();
    }


    @RequestMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/saveCustomers")
    public String saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            return "Validation Error";
        }
        return customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);

    }
}
