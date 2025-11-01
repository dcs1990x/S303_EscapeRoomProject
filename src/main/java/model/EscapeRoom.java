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

    public static Room createRoom(RoomBuilderInterface roomBuilder){
        return roomBuilder.createRoom();
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
