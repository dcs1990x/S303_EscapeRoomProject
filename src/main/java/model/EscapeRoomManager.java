package model;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class EscapeRoomManager {

    private static EscapeRoomManager instance;
    private Menu menu;
    private RoomBuilder roomBuilder;
    private ArrayList<EscapeRoom> escapeRooms = new ArrayList<>();
    private Inventory inventory;
    private SubscribersManager subscribersManager;
    private Scanner scanner = new Scanner(System.in);

    private EscapeRoomManager(){}

    public static synchronized EscapeRoomManager getInstance(){
        if (instance == null){
            instance = new EscapeRoomManager();
        }
        return instance;
    }

    public EscapeRoom createEscapeRoom(){
        System.out.println("Please write the escape room's name: ");
        String name = scanner.nextLine();
        return new EscapeRoom(name);
    }

    public Optional<EscapeRoom> getEscapeRoomByConsole() throws NoSuchObjectException {
        System.out.println("These are the existing escape rooms: ");
        System.out.println("\n" + instance.getEscapeRoomsList() + "\n");
        System.out.println("Please type the escape room's name or type \"Return\" to go back. ");
        String name = scanner.nextLine().trim();

        if (name.equalsIgnoreCase("return")) {
            return Optional.empty();
        }

        for (EscapeRoom escapeRoom : escapeRooms) {
            if (name.equalsIgnoreCase(escapeRoom.getEscapeRoomName())){
                return Optional.of(escapeRoom);
            }
        }
        System.out.println("The name entered does not correspond to an existing escape room.");
        return Optional.empty();
    }

    public void manageEscapeRoom() throws NoSuchObjectException{
        checkEmptyEscapeRoomList();
        System.out.println("What do you want to do?");
        System.out.println("1. CREATE A ROOM" + System.lineSeparator() +
                "2. MODIFY ROOM" + System.lineSeparator() +
                "3. DELETE ROOM");
        byte option = scanner.nextByte();
        scanner.nextLine();


    }

    public void deleteEscapeRoom(Optional<EscapeRoom> escapeRoomToDelete) throws NoSuchObjectException{
        checkEmptyEscapeRoomList();
        escapeRooms.remove(escapeRoomToDelete);
    }

    public ArrayList<EscapeRoom> getEscapeRoomsList() throws NoSuchObjectException {
        checkEmptyEscapeRoomList();
        return escapeRooms;
    }

    public void checkEmptyEscapeRoomList() throws NoSuchObjectException{
        if (getEscapeRoomsList() == null || getEscapeRoomsList().isEmpty()){
            throw new NoSuchObjectException("There are no previously created escape rooms. ");
        }
    }

    public void getInventory(){
        System.out.println(inventory);
    }
}