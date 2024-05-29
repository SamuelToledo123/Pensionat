package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findAll(Sort sort);


}
