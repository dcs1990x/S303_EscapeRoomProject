package model;

public interface RoomBuilderInterface {

    RoomBuilderInterface setRoomTheme(Theme theme);
    RoomBuilderInterface addRoomDecoration(Decoration decoration);
    RoomBuilderInterface addRoomClue(Clue clue);
    RoomBuilderInterface setRoomDifficulty(Difficulty difficulty);
    Room createRoom();
}