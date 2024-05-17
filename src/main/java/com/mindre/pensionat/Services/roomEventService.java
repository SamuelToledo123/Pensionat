package com.mindre.pensionat.Services;


import com.mindre.pensionat.Repo.RoomEventRepo;
import com.mindre.pensionat.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.Serial;

@Service
public class roomEventService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private RoomEventRepo eventRepo;



}
