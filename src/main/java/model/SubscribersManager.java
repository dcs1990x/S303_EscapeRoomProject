package model;

import java.util.ArrayList;

public class SubscribersManager implements SubscribersManageable {

    private EscapeRoomManager escapeRoomManager;
    private static ArrayList<Player> subscribersList;

    public SubscribersManager(EscapeRoomManager escapeRoomManager){
        subscribersList = new ArrayList<>();
        this.escapeRoomManager = escapeRoomManager;
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
    public void emailSubscribers() {}
}