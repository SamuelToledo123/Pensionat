package com.mindre.pensionat.Dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    @NotEmpty(message = "the Firstname is required")
    @Size(min = 3, max = 15)
    private String firstName;

    @NotEmpty(message = "the Lastname is required")
    @Size(min = 3)
    @Size(max = 15)
    private String lastName;

    @Email(message = "email is required")
    private String email;

    //kund
    @NotNull(message = "phone number is required")
    @Size(max = 10, min = 10 , message = "Must contain 10 numbers")
    @Pattern(regexp = "[0-9]+", message = "Only numbers")
    private String  phoneNumber;


}
