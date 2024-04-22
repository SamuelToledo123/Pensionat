package com.mindre.pensionat.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    @NotEmpty(message = "roomType is required")
    private String roomType;
    @Min(0)
    private int amountBeds;
    @NotNull(message = "room size is required")
    private int roomSize;


}
