package model;

public interface IRoomBuilder {

    void setRoomTheme(Theme theme);
    void addRoomDecoration(Decoration decoration);
    void addRoomClue(Clue clue);
    void setRoomDifficulty(Difficulty difficulty);
    Room createRoom();
}