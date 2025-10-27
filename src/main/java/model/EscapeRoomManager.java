package model;

import java.util.ArrayList;

public class EscapeRoomManager implements Notifier {

    private static EscapeRoomManager instance;
    private String escapeRoomName;
    private RoomBuilder roomBuilder;
    private ArrayList<Room> rooms;
    private Inventory inventory;
    private ArrayList<Player> subscribersList;

    private EscapeRoomManager(){
        this.rooms = new ArrayList<>();
        this.subscribersList = new ArrayList<>();
    }

    public static EscapeRoomManager getInstance(){
        if (instance == null){
            instance = new EscapeRoomManager();
        }
        return instance;
    }

    public String getEscapeRoomName(){return this.escapeRoomName;}
    public void getRooms(){
        for (Room room : rooms){
            System.out.println(room);
        }
    }
    public void getInventory(){
        System.out.println(inventory);
    }

    public void getSubscribersList(){
        for (Player player : subscribersList){
            System.out.println(player);
        }
    }

    @Override
    public void registerPlayer(Player player) {
        subscribersList.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        subscribersList.remove(player);
    }

    @Override
    public void notifySubscribers() {

    }
}