package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {


}
