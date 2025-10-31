package model;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class EscapeRoomManager {

    private static EscapeRoomManager instance;
    private CreateManageDeleteERMenu menu;
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
        System.out.print("\nPlease write the escape room's name: ");
        String name = scanner.nextLine();
        EscapeRoom escapeRoom = new EscapeRoom(name);
        escapeRooms.add(escapeRoom);
        return escapeRoom;
    }

    public Optional<EscapeRoom> getEscapeRoomByConsole() throws NoSuchObjectException {
        System.out.println("\nThese are the existing escape rooms: \n");
        System.out.println("\n" + instance.getEscapeRoomsList() + "\n");
        System.out.println("\nPlease type the escape room's name or type \"Return\" to go back: ");
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

    public void deleteEscapeRoom(Optional<EscapeRoom> escapeRoomToDelete) throws NoSuchObjectException{
        checkEmptyEscapeRoomList();
        escapeRooms.remove(escapeRoomToDelete);
    }

    public ArrayList<EscapeRoom> getEscapeRoomsList() {
        return escapeRooms;
    }

    public void checkEmptyEscapeRoomList() throws NoSuchObjectException{
        if (escapeRooms == null || escapeRooms.isEmpty()){
            throw new NoSuchObjectException("\nThere are no previously created escape rooms. \n");
        }
    }

    public void setScanner(Scanner scanner){
        this.scanner = scanner;
    }

    public void getInventory(){
        System.out.println(inventory);
    }
}