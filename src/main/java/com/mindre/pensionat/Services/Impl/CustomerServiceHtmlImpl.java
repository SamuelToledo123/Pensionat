package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("/costumers")
public class CustomerServiceHtmlImpl {

    @Autowired
    private final CustomerRepo customerRepo;


    @GetMapping({"","/"})
    public String getInfoCustomers(Model model) {
        List<Customer> customers = customerRepo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("customers",customers);
        return "customers/index";
    }
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customerDto", customerDto);

        return "customers/CreateCustomer";
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute CustomerDto customerDto, BindingResult result) {

        if (result.hasErrors()) {
            return "customers/CreateCustomer";
        }

        try {

            Customer customer = new Customer();
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setEmail(customerDto.getEmail());
            customer.setPhoneNumber(customerDto.getPhoneNumber());

            customerRepo.save(customer);


        } catch (Exception e) {
            System.out.println("Error occurred while creating the customer!" + e.getMessage());
        }
        return "redirect:/customers";
    }

    @GetMapping("/edit")
    public String getEditPage(Model model, @RequestParam Long id) {


        Customer customer = customerRepo.findById(id).get();
        model.addAttribute("customer",customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());

        model.addAttribute("customerDto", customerDto);

        return "customers/EditCustomer";

    }

    @PostMapping("/edit")
    public String editCustomer(Model model, @RequestParam Long id, @Valid @ModelAttribute CustomerDto customerDto,BindingResult result) {

        if (result.hasErrors()) {
            return "customers/EditCustomer";
        }

        try {

            Customer customer = customerRepo.findById(id).get();
            model.addAttribute("customer",customer);

            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setEmail(customerDto.getEmail());
            customer.setPhoneNumber(customerDto.getPhoneNumber());

            customerRepo.save(customer);

        } catch (Exception e) {
            System.out.println("Error while editing customer!" + e.getMessage());
        }
        return "redirect:/customers";

    }

    @GetMapping("/delete")
    public String deleteFromPageCustomer(@RequestParam Long id) {

        try {

            Customer customer = customerRepo.findById(id).get();
            customerRepo.delete(customer);


        } catch (Exception e) {
            System.out.println("Error While deleting customer!" + e.getMessage());
        }
        return "redirect:/customers";
    }

}




