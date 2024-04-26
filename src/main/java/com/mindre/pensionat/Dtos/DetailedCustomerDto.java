package com.mindre.pensionat.Dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedCustomerDto {
    private Long id;
    @NotEmpty(message = "the Firstname is required")
    @Size(min = 3)
    @Size(max = 15)
    private String firstName;
    @NotEmpty(message = "the Lastname is required")
    @Size(min = 3)
    @Size(max = 15)
    private String lastName;
    @Email
    private String email;
    @NotNull(message = "Phone number is required")
    @Size(min = 10)
    @Size(max = 10)
    @Pattern(regexp = "[0-9]+", message = "Only numbers")
    private String  phoneNumber;

    private BookedRoomDto bookedRoomDto;
    private List<BookedRoomDto> konton;
}
