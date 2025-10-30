package model;

public class RoomBuilder implements IRoomBuilder {

    private Room room;

    public RoomBuilder(){
        this.room = new Room();
    }

    @Override
    public IRoomBuilder setRoomTheme(Theme theme) {
        room.setTheme(theme);
        return this;
    }

    @Override
    public IRoomBuilder setRoomDifficulty(Difficulty difficulty) {
        room.setDifficulty(difficulty);
        return this;
    }

    @Override
    public IRoomBuilder addRoomDecoration(Decoration decoration) {
        room.addDecoration(decoration);
        return this;
    }

    @Override
    public IRoomBuilder addRoomClue(Clue clue) {
        room.addClue(clue);
        return this;
    }

    @Override
    public Room createRoom() {
        return room;
    }
}