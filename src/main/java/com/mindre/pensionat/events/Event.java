package com.mindre.pensionat.events;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mindre.pensionat.Models.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Event {

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = RoomClosed.class, name = "RoomClosed"),
            @JsonSubTypes.Type(value = RoomCleaned.class, name = "RoomCleaningFinished"),
            @JsonSubTypes.Type(value = RoomOpened.class, name = "RoomOpened")
    })
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String employee;

        public LocalDateTime date;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "room_id")
        private Room room;

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    }














    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String employee;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    private String openedDoor;
    private String closedDoor;
    private String cleanStart;
    private String cleanEnd;
    private LocalDateTime localDateTime;

}
*/
