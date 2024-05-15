package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Models.ContractCustomer;
import com.mindre.pensionat.Services.Impl.BookedRoomServiceHtml;
import com.mindre.pensionat.Services.Impl.CustomerServiceHtmlImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerHtlmController {


    private final CustomerServiceHtmlImpl customerServiceHtmlImpl;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceHtmlImpl.class);

    @GetMapping("approvedCreatedCustomer")
    public String getApproved() {
        return "customers/customerCreated";
    }
    @GetMapping("approvedUpdatedCustomer")
    public String getDenied() {
        return "customers/customerUpdated";
    }
    @GetMapping("alreadyBooked")
    public String getalreadyBooked() {
        return "customers/alreadyBooked";
    }



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




