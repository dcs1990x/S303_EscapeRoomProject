package model;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import static model.Difficulty.*;

public class Player implements NotifiedPlayer{

    private String name;
    private boolean madeReservation;
    private ArrayList<?> playerInventory;
    private int score;

    public Player(String name, boolean madeReservation, ArrayList<?> playerInventory){
        this.name = name;
        this.madeReservation = madeReservation;
        this.playerInventory = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public boolean hasMadeReservation(){
        return madeReservation;
    }

    public void getPlayerInventory(){
        for (var item : playerInventory){
            System.out.println(item);
        }
    }

    public int getScore(){
        return this.score;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMadeReservation(boolean madeReservation){
        this.madeReservation = madeReservation;
    }

    public void setScore(Room room){
        try{
            if (room.getDifficulty() == VERY_EASY){
                this.score += 2;
            } else if (room.getDifficulty() == EASY){
                this.score += 4;
            } else if (room.getDifficulty() == NORMAL){
                this.score += 6;
            } else if (room.getDifficulty() == HARD){
                this.score += 8;
            } else if (room.getDifficulty() == VERY_HARD){
                this.score += 10;
            } else {
                throw new NoSuchObjectException("The specified difficulty level does not exist. ");
            }
        } catch (NoSuchObjectException nsoe){
            System.out.println(nsoe.getMessage());
        }
    }

    public void useItem(Item item){
        System.out.printf("The player %s has used the %s. ", this.getName(), item.getName());
    }

    public void solveClue(Clue clue){
        if (!clue.getIsSolved()){
            clue.setIsSolved();
            this.score += clue.getDifficultyPoints();
        }
    }

    @Override
    public void readNotification(Notification notification){}

    @Override
    public void subscribe(SubscribersManager subscribersManager){
        subscribersManager.registerPlayer(this);
    }

    @Override
    public void unsubscribe(SubscribersManager subscribersManager){
        subscribersManager.removePlayer(this);
    }

    @Override
    public String toString(){
        return "Player " + System.lineSeparator() +
                "Name: " + getName() + System.lineSeparator() +
                "Made reservation? " + hasMadeReservation() + System.lineSeparator();
    }
}