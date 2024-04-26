package com.mindre.pensionat.Dtos;

import com.mindre.pensionat.Models.Customer;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedBookedRoomDto {

        Long id;
        @DateTimeFormat
        private Date checkIn;
        @DateTimeFormat
        private Date checkOut;
        @Min(0)
        private int amountPersons;

        private List<Customer> customers;
        private CustomerDto customerDto;

}
