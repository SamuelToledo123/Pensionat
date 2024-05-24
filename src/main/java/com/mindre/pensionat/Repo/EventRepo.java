package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
}
