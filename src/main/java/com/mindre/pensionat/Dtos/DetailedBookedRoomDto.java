package com.mindre.pensionat.Dtos;

import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DetailedBookedRoomDto {


    @DateTimeFormat
    private Date checkIn;
    @DateTimeFormat
    private Date checkOut;
    @Min(0)
    private int amountPersons;

    private CustomerDto customer;
}
