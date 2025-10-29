package model;

import java.util.ArrayList;

public class EscapeRoomManager {

    private static EscapeRoomManager instance;
    private String escapeRoomName;
    private Menu menu;
    private RoomBuilder roomBuilder;
    private ArrayList<Room> rooms = new ArrayList<>();;
    private Inventory inventory;
    private SubscribersManager subscribersManager;

    private EscapeRoomManager(){}

    public static EscapeRoomManager getInstance(){
        if (instance == null){
            instance = new EscapeRoomManager();
        }
        return instance;
    }

    public String getEscapeRoomName(){
        return this.escapeRoomName;
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public void getInventory(){
        System.out.println(inventory);
    }
}