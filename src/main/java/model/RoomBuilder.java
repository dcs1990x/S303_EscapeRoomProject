package model;

public class RoomBuilder implements IRoomBuilder {

    private Room room;

    @Override
    public void setTheme(Theme theme) {

    }

    @Override
    public void addDecorations(Decoration decoration) {

    }

    @Override
    public void addClues(Clue clue) {

    }

    @Override
    public Room createRoom() {
        return Room;
    }
}