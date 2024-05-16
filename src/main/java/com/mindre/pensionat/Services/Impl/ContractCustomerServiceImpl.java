package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Repo.ContractCustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractCustomerServiceImpl {

    private final ContractCustomerRepo repo;

    public String getContractCustomers(Model model) {
        List<com.mindre.pensionat.Models.ContractCustomer> contractCustomers = repo.findAll();
        model.addAttribute("contractCustomers", contractCustomers);
        return "contract/ContractCustomers";
    }
}
