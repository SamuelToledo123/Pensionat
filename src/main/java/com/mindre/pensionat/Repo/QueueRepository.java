package com.mindre.pensionat.Repo;

import com.mindre.pensionat.Models.Queue;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QueueRepository extends CrudRepository<Queue, UUID> {

}
