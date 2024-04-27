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
import com.mindre.pensionat.Services.Impl.CustomerServiceHtmlImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerHtlmController {

    @Autowired
    private final CustomerServiceHtmlImpl customerServiceHtmlImpl;


    @GetMapping({"", "/"})
    public String getInfoCustomers(Model model) {
        return customerServiceHtmlImpl.getInfoCustomers(model);
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        return customerServiceHtmlImpl.getCreatePage(model);

    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute CustomerDto customerDto, BindingResult result) {
        return customerServiceHtmlImpl.createCustomer(customerDto, result);
    }

    @GetMapping("/edit")
    public String getEditPage(Model model, @RequestParam Long id) {
        return customerServiceHtmlImpl.getEditPage(model, id);

    }

    @PostMapping("/edit")
    public String editCustomer(Model model, @RequestParam Long id, @Valid @ModelAttribute CustomerDto customerDto, BindingResult result) {
        return customerServiceHtmlImpl.editCustomer(model, id, customerDto, result);

    }

    @GetMapping("/delete")
    public String deleteFromPageCustomer(@RequestParam Long id) {
        return customerServiceHtmlImpl.deleteFromPageCustomer(id);
    }
}

  /*  @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<DetailedCustomerDto> customers = customerServiceHtmlImpl.getAllDetailedCustomers();
        model.addAttribute("customers", customers);
        return "customers/index";
    }
}

   */



