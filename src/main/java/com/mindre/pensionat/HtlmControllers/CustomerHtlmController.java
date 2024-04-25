package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mindre.pensionat.Services.Impl.CustomerServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerHtlmController {

    @Autowired
    private final CustomerServiceImpl customerServiceImpl;


    @GetMapping({"", "/"})
    public String getInfoCustomers(Model model) {
        return customerServiceImpl.getInfoCustomers(model);
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        return customerServiceImpl.getCreatePage(model);

    }
    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute CustomerDto customerDto, BindingResult result) {
        return customerServiceImpl.createCustomer(customerDto, result);
    }

    @GetMapping("/edit")
    public String getEditPage(Model model, @RequestParam Long id) {
        return customerServiceImpl.getEditPage(model, id);

    }

    @PostMapping("/edit")
    public String editCustomer(Model model, @RequestParam Long id, @Valid @ModelAttribute CustomerDto customerDto,BindingResult result) {
        return customerServiceImpl.editCustomer(model, id, customerDto, result);

    }

    @GetMapping("/delete")
    public String deleteFromPageCustomer(@RequestParam Long id) {
        return customerServiceImpl.deleteFromPageCustomer(id);
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<DetailedCustomerDto> customers = customerServiceImpl.getAllDetailedCustomers();
        model.addAttribute("customers", customers);
        return "customers/index";
    }
}



