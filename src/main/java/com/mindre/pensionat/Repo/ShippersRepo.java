package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.BookedRoom;
import com.mindre.pensionat.Models.Shippers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippersRepo extends JpaRepository<Shippers, Long> {
}
