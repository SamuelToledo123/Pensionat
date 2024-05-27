package com.mindre.pensionat.Dtos;

import com.mindre.pensionat.events.Event;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private Long id;
    @NotEmpty(message = "roomType is required")
    private String roomType;
    @Min(0)
    private int amountOfBeds;
    @NotNull(message = "room size is required")
    private int roomSize;

    private Event event;


}
