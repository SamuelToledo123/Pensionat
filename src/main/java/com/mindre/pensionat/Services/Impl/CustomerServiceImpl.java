package com.mindre.pensionat.Services.Impl;

import com.mindre.pensionat.Dtos.CustomerDto;
import com.mindre.pensionat.Dtos.DetailedCustomerDto;
import com.mindre.pensionat.Models.Customer;
import com.mindre.pensionat.Repo.CustomerRepo;
import com.mindre.pensionat.Services.BookedRoomService;
import com.mindre.pensionat.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    //kund
    @Autowired
    CustomerRepo customerRepo;
    private final BookedRoomService bookedRoomService;

    public CustomerServiceImpl(BookedRoomService bookedRoomService) {
        this.bookedRoomService = bookedRoomService;
    }


    @Override
    public List<DetailedCustomerDto> getAllKunder() {
        return customerRepo.findAll().stream().map(k -> kundToDetailedKundDto(k)).toList();
    }

    @Override
    public CustomerDto kundToKundDto(Customer c) {
        return CustomerDto.builder().id(c.getId())
                        .firstName(c.getFirstName())
                .lastName(c.getLastName()).build();
    }

    @Override
    public DetailedCustomerDto kundToDetailedKundDto(Customer c) {
        return DetailedCustomerDto.builder().id(c.getId()).firstName(c.getFirstName()).lastName(c.getLastName()).email(c.getEmail()).phoneNumber(c.getPhoneNumber())
                .reservations(c.getBookedRooms().stream()
                        .map(konto -> bookedRoomService.kontoToKontoDto(konto)).toList()).build();
    }

    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @RequestMapping("/customers")
    public List<DetailedCustomerDto> getAllCustomers() {
        return customerRepo.findAll().stream().map(k -> kundToDetailedKundDto(k)).toList();
    }

    @PostMapping("/saveCustomers")
    public String saveCustomer( @RequestBody Customer customer) {
        customerRepo.save(customer);
        return "Customer was saved successfully";
    }

    @PutMapping("updateCustomer/{id}")
    public String updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) {

            Customer updateCustomer = customerRepo.findById(id).get();
            updateCustomer.setFirstName(customerDto.getFirstName());
            updateCustomer.setLastName(customerDto.getLastName());
            updateCustomer.setEmail(customerDto.getEmail());
            updateCustomer.setPhoneNumber(customerDto.getPhoneNumber());
            customerRepo.save(updateCustomer);

            return "Customer was updated successfully";
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        Customer deleteCustomer = customerRepo.findById(id).get();
        customerRepo.delete(deleteCustomer);
        return "Customer with the id: " + id + " was deleted successfully";

    }
}




