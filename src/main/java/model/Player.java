package model;

import java.util.ArrayList;

public class Player implements NotifiedPlayer{

    private String name;
    private boolean madeReservation;
    private ArrayList<?> playerInventory;
    private Clue hint;

    public Player(String name, boolean madeReservation, ArrayList<?> playerInventory){
        this.name = name;
        this.madeReservation = madeReservation;
        this.playerInventory = new ArrayList<>();
    }

    public String getName(){return this.name;}
    public boolean hasMadeReservation(){return madeReservation;}
    public void getPlayerInventory(){
        for (var item : playerInventory){
            System.out.println(item);
        }
    }
    public void setName(String name){this.name = name;}
    public void setMadeReservation(boolean madeReservation){this.madeReservation = madeReservation;}

    public void solveHint(Clue hint){}

    @Override
    public void readNotification(Notification notification){}

    @Override
    public void subscribe(){}

    @Override
    public void unsubscribe(){}

    @Override
    public String toString(){
        return "Player " + System.lineSeparator() +
                "Name: " + getName() + System.lineSeparator() +
                "Made reservation? " + hasMadeReservation() + System.lineSeparator();
    }
}