package com.mindre.pensionat.HtlmControllers;


import com.mindre.pensionat.Services.Impl.ContractCustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contract")
public class ContractCustomerController {

    private final ContractCustomerServiceImpl service;
    @GetMapping("/customers")
    public String getContractCustomers(Model model) {
        return service.getContractCustomers(model);
    }

}
