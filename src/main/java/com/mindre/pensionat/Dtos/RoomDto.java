package com.mindre.pensionat.Dtos;

import com.mindre.pensionat.Models.Event;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<EventDto> events;


}
