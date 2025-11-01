package model;

import menus.CreateManageDeleteERMenu;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class EscapeRoomManager {

    private static EscapeRoomManager instance;
    private CreateManageDeleteERMenu menu;
    private ArrayList<EscapeRoom> escapeRooms = new ArrayList<>();
    private Inventory inventory;
    private SubscribersManager subscribersManager;

    private EscapeRoomManager(){}

    public static synchronized EscapeRoomManager getInstance(){
        if (instance == null){
            instance = new EscapeRoomManager();
        }
        return instance;
    }

    public EscapeRoom createEscapeRoom(){
        String name = UserInput.readLine("\nPlease write the escape room's name: ");
        EscapeRoom escapeRoom = new EscapeRoom(name);
        escapeRooms.add(escapeRoom);
        return escapeRoom;
    }

    public Optional<EscapeRoom> getEscapeRoomByConsole() throws NoSuchElementException {
        checkEmptyEscapeRoomList();
        System.out.println("These are the existing escape rooms: \n");
        System.out.println(getEscapeRoomsList());
        String name = UserInput.readLine("\nPlease type the escape room's name or type \"Return\" to go back: ");

        for (EscapeRoom escapeRoom : escapeRooms) {
            if (name.equalsIgnoreCase("return")) {
                return Optional.empty();
            }
            if (name.equalsIgnoreCase(escapeRoom.getEscapeRoomName())){
                return Optional.of(escapeRoom);
            }
        }
        throw new NoSuchElementException("No escape room found with the name \"" + name + "\".");
    }

    public void deleteEscapeRoom(EscapeRoom escapeRoomToDelete) throws NoSuchElementException {
        checkEmptyEscapeRoomList();
        escapeRooms.remove(escapeRoomToDelete);
    }

    public ArrayList<EscapeRoom> getEscapeRoomsList() {
        return escapeRooms;
    }

    public void checkEmptyEscapeRoomList() throws NoSuchElementException {
        if (escapeRooms == null || escapeRooms.isEmpty()){
            throw new NoSuchElementException("There are no previously created escape rooms. ");
        }
    }

    public void getInventory(){
        System.out.println(inventory);
    }
}