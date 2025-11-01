package model;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EscapeRoom {

    private String name;
    private List<Room> rooms;
    private RoomBuilderInterface roomBuilder;
    private Scanner scanner = new Scanner(System.in);

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

    public Room createRoom(RoomBuilderInterface roomBuilder){
        String RoomName = UserInput.readLine("Please enter the name of the room: ");
        roomBuilder.setRoomName(RoomName);

        System.out.println("These are the themes for the rooms: \n");
        Theme.getThemesList();
        String roomTheme = UserInput.readLine("\nPlease choose a theme from the list for the new room: ");
        Theme theme = Theme.fromString(roomTheme);
        roomBuilder.setRoomTheme(Theme.valueOf(theme));

        System.out.println("These are the difficulty levels for the rooms: \n");
        Difficulty.getDifficultiesList();
        String roomDifficulty = UserInput.readLine("\nPlease choose a difficulty from the list for the new room: ");
        Difficulty difficulty = Difficulty.fromString(roomDifficulty);
        roomBuilder.setRoomDifficulty(Difficulty.valueOf(difficulty));

        byte addDecoOrGoBackOption = 1;
        while (addDecoOrGoBackOption == 1) {
            String decoration = UserInput.readLine("Type a piece of decoration to add to the room: ");
            roomBuilder.addRoomDecoration(decoration);
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

    @Override
    public String toString(){
        return this.getEscapeRoomName();
    }
}
