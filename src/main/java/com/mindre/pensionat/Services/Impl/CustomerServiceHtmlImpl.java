package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.BookedRoomRepo;
import com.mindre.pensionat.Repo.ContractCustomerRepo;
import com.mindre.pensionat.Repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceHtmlImpl {

    @Autowired
    private final CustomerRepo customerRepo;
    private final BookedRoomRepo bookedRoomRepo;
    private final ContractCustomerRepo contractCustomerRepo;
    private static final Logger logger = LoggerFactory.getLogger(BookedRoomServiceHtml.class);


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
            logger.error("Validation errors: {}", result.getAllErrors());
            return "customers/CreateCustomer";
        }

        try {

            Customer customer = new Customer();
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setEmail(customerDto.getEmail());
            customer.setPhoneNumber(customerDto.getPhoneNumber());

            customerRepo.save(customer);
            logger.info("Saved customer with ID: {}", customer.getId());


        } catch (Exception e) {
            logger.error("Error occurred while creating the customer: {}", e.getMessage());

            System.out.println("Error occurred while creating the customer!" + e.getMessage());
        }
        return "redirect:/customers";
    }

    @GetMapping("/edit")
    public String getEditPage(Model model, @RequestParam Long id) {

        try {
        Customer customer = customerRepo.findById(id).get();
        model.addAttribute("customer", customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());

        model.addAttribute("customerDto", customerDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/customers";
        }

        return "customers/EditCustomer";

    }

    @PostMapping("/edit")
    public String editCustomer(Model model, @RequestParam Long id, @Valid @ModelAttribute CustomerDto customerDto,BindingResult result) {

        try {

            Customer customer = customerRepo.findById(id).get();
            model.addAttribute("customer",customer);

        if (result.hasErrors()) {
            return "customers/EditCustomer";
        }

            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setEmail(customerDto.getEmail());
            customer.setPhoneNumber(customerDto.getPhoneNumber());

            model.addAttribute("customerDto" , customerDto);

            customerRepo.save(customer);
            logger.info("Updated customer with ID: {}" , customer.getId());

        } catch (Exception e) {
            System.out.println("Error while editing customer!" + e.getMessage());
        }
        return "redirect:/customers";

    }

    @GetMapping("/delete")
    public String deleteFromPageCustomer(@RequestParam Long id) {

        try {

            Customer customer = customerRepo.findById(id).get();

            if(bookedRoomRepo.existsByCustomerId(customer.getId())){
                System.out.println("Can't delete customer, existing booking");
                return "redirect:/customers";
            }
            customerRepo.delete(customer);
            logger.info("Deleted customer with ID: {}" , customer.getId());


        } catch (Exception e) {
            System.out.println("Error While deleting customer!" + e.getMessage());
        }
        return "redirect:/customers";
    }

}




