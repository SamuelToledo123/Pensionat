package com.mindre.pensionat.Dtos;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedBookedRoomDto {



    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private LocalDate checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private LocalDate checkOut;
    @Min(0)
    private int amountPersons;

    private CustomerDto customer;
    private RoomDto room;
    public CustomerDto getCustomerDto() {
        return this.customer;
    }
    public Long getRoomId() {
        return this.room.getId();
    }

}
