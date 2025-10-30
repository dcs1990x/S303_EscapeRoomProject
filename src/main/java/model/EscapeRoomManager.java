package model;

import java.util.ArrayList;

public class EscapeRoomManager {

    private static EscapeRoomManager instance;
    private Menu menu;
    private RoomBuilder roomBuilder;
    private ArrayList<EscapeRoom> escapeRooms;
    private Inventory inventory;
    private SubscribersManager subscribersManager;

    private EscapeRoomManager(){
        this.escapeRooms = new ArrayList<>();
    }

    public static EscapeRoomManager getInstance(){
        if (instance == null){
            instance = new EscapeRoomManager();
        }
        return instance;
    }

    public ArrayList<EscapeRoom> getEscapeRooms(){
        return escapeRooms;
    }

    public void getInventory(){
        System.out.println(inventory);
    }
}