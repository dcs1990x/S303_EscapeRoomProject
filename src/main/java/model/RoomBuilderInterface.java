package model;

import dtomodel.DecorationDTO;

import java.util.Optional;

public interface RoomBuilderInterface {

    RoomBuilderInterface setRoomName(String name);
    RoomBuilderInterface setRoomTheme(Theme theme);
    RoomBuilderInterface addRoomDecoration(Optional<DecorationDTO> decoration);
    RoomBuilderInterface addRoomClue(Clue clue);
    RoomBuilderInterface setRoomDifficulty(Difficulty difficulty);
    Room createRoom();
}