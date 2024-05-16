package com.mindre.pensionat.Services;

import org.springframework.ui.Model;
import com.mindre.pensionat.Dtos.RoomDto;
import com.mindre.pensionat.Models.Room;

import java.util.List;

public interface RoomService {

    public RoomDto findRoomById(Long id);

    public List<RoomDto> findAllRooms();

    public RoomDto roomToRoomDto (Room room);
}
