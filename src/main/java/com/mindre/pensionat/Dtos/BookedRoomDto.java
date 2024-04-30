package com.mindre.pensionat.Dtos;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookedRoomDto {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOut;
    @Min(0)
    private int amountPersons;

    //konto
}
