package model;

import daomodel.DaoEscapeRoom;

import java.util.ArrayList;

public class SubscribersManager implements SubscribersManageable {


    private DaoEscapeRoom escapeRoomManager = new DaoEscapeRoom();
    private static ArrayList<Player> subscribersList;

    public SubscribersManager(){
        subscribersList = new ArrayList<>();

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

}