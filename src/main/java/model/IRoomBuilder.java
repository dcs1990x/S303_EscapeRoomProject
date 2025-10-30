package model;

public interface IRoomBuilder {

    IRoomBuilder setRoomTheme(Theme theme);
    IRoomBuilder addRoomDecoration(Decoration decoration);
    IRoomBuilder addRoomClue(Clue clue);
    IRoomBuilder setRoomDifficulty(Difficulty difficulty);
    Room createRoom();
}