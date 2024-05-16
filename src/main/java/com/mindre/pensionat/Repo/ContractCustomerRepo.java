package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.ContractCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ContractCustomerRepo extends JpaRepository<ContractCustomer, Long> {



}
