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

    @NotEmpty(message = "the Firstname is required")
    @Size(min = 3, max = 15)
    private String firstName;
    @NotEmpty(message = "the Lastname is required")
    @Size(min = 3, max = 15)
    private String lastName;
    @NotEmpty(message = "Email is required")
    @Email
    private String email;
    @NotEmpty(message = "Phone number is required")
    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]+", message = "Only numbers")
    private String  phoneNumber;



}
