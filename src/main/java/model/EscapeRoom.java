package model;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class EscapeRoom {

    private String name;
    private List<Room> rooms;
    private RoomBuilderInterface roomBuilder = new RoomBuilder();
    private Scanner scanner = new Scanner(System.in);

    public EscapeRoom(){
        this.rooms = new ArrayList<>();
    }

    public EscapeRoom(String name){
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public String getEscapeRoomName(){
        return this.name;
    }

    public List<Room> getRoomsList(){
        return List.copyOf(rooms);
    }

    public Room createRoom(){
        String RoomName = UserInput.readLine("Please enter the name of the room: ");
        roomBuilder.setRoomName(RoomName);

        System.out.println("These are the themes for the rooms: \n");
        Theme.getThemesList();
        String roomTheme = UserInput.readLine("\nPlease choose a theme from the list for the new room: ");
        Optional<Theme> theme = Theme.fromString(roomTheme);
        roomBuilder.setRoomTheme(Theme.valueOf(String.valueOf(theme)));

        System.out.println("These are the difficulty levels for the rooms: \n");
        Difficulty.getDifficultiesList();
        String roomDifficultyString = UserInput.readLine("\nPlease choose a difficulty from the list for the new room: ");
        Optional<Difficulty> difficulty = Difficulty.fromString(roomDifficultyString);
        roomBuilder.setRoomDifficulty(Difficulty.valueOf(String.valueOf(difficulty)));

        byte addDecoOrGoBackOption = 1;
        while (addDecoOrGoBackOption == 1) {
            System.out.println("These are the available decorations for the rooms: \n");
            Decoration.getDecorationsList();
            String decorationString = UserInput.readLine("Type a piece of decoration to add to the room: ");
            Optional<Decoration> decoration = Decoration.fromString(decorationString);
            roomBuilder.addRoomDecoration(Decoration.valueOf(String.valueOf(decoration)));
            addDecoOrGoBackOption = UserInput.readByte("Would you like to add another piece of decoration? Press 1 to add another decoration, press any other key to go back: ");
        }

        byte addItemOrGoBackOption = 1;
        while (addItemOrGoBackOption == 1) {
            String item = UserInput.readLine("Type an item to add to the room: ");
            addItemOrGoBackOption = UserInput.readByte("Would you like to add another item? Press 1 to add another item, press any other key to go back: ");
        }

        Room roomCreated = roomBuilder.createRoom();
        rooms.add(roomCreated);
        return roomCreated;
    }

    public Optional<Room> getRoomByConsole() throws NoSuchObjectException {
        System.out.println("\nThese are the existing rooms: \n");
        System.out.println("\n" + this.getRoomsList() + "\n");
        System.out.println("\nPlease type the room's name or type \"Return\" to go back: ");
        String name = scanner.nextLine().trim();

        if (name.equalsIgnoreCase("return")) {
            return null;
        }

        for (Room room : this.getRoomsList()) {
            if (name.equalsIgnoreCase(room.getName())){
                return Optional.of(room);
            }
        }
        System.out.println("The name entered does not correspond to an existing room. ");
        return Optional.empty();
    }

    public void deleteEscapeRoom(Room room) throws NoSuchObjectException{
        checkEmptyRoomList();
        this.rooms.remove(room);
    }

    public void checkEmptyRoomList() throws NoSuchObjectException{
        if (this.rooms == null || this.rooms.isEmpty()){
            throw new NoSuchObjectException("\nThere are no previously created rooms. \n");
        }
    }

    public void setRoomBuilder(RoomBuilderInterface roomBuilder) {
        this.roomBuilder = roomBuilder;
    }

    @Override
    public String toString(){
        return this.getEscapeRoomName();
    }
}