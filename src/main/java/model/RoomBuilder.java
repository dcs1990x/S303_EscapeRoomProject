package model;

public class RoomBuilder implements IRoomBuilder {

    private Room room;

    public RoomBuilder(){
        this.room = new Room();
    }

    @Override
    public void setRoomTheme(Theme theme) {
        room.setTheme(theme);
    }

    @Override
    public void setRoomDifficulty(Difficulty difficulty) {
        room.setDifficulty(difficulty);
    }

    @Override
    public void addRoomDecoration(Decoration decoration) {
        room.addDecoration(decoration);
    }

    @Override
    public void addRoomClue(Clue clue) {
        room.addClue(clue);
    }

    @Override
    public Room createRoom() {
        return room;
    }
}