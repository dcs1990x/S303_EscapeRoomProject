package model;

public class RoomBuilder implements RoomBuilderInterface {

    private Room room;

    public RoomBuilder(){
        this.room = new Room();
    }

    @Override
    public RoomBuilderInterface setRoomName(String name){
        room.setName(name);
        return this;
    }

    @Override
    public RoomBuilderInterface setRoomTheme(Theme theme) {
        room.setTheme(theme);
        return this;
    }

    @Override
    public RoomBuilderInterface setRoomDifficulty(Difficulty difficulty) {
        room.setDifficulty(difficulty);
        return this;
    }

    @Override
    public RoomBuilderInterface addRoomDecoration(Decoration decoration) {
        room.addDecoration(decoration);
        return this;
    }

    @Override
    public RoomBuilderInterface addRoomClue(Clue clue) {
        room.addClue(clue);
        return this;
    }

    @Override
    public Room createRoom() {
        return room;
    }
}