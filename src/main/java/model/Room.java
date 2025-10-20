package model;

import java.util.ArrayList;
import java.util.List;

public class Room implements RoomSolvedCallback{

    private String name, theme;
    private char difficulty;
    private boolean isSolved;
    private List<Item> items;

    public Room(String name, String theme, char difficulty){
        this.name = name;
        this.theme = theme;
        this.isSolved = false;
        this.difficulty = difficulty;
        this.items = new ArrayList<>();
    }

    public String getName(){return this.name;}
    public String getTheme(){return this.theme;}
    public boolean getSolvedStatus(){return this.isSolved;}
    public char getDifficulty(){return this.difficulty;}
    public void getItems(){
        for (Item item : items){
            System.out.println(item);
        }
    }

    public void setName(String name){this.name = name;}
    public void setTheme(String theme){this.theme = theme;}
    public void setSolved(){
        if (!isSolved){
            this.isSolved = true;
        } else {
            this.isSolved = false;
        }
    }
    public void setDifficulty(char difficulty){this.difficulty = difficulty;}

    public void roomWasSolved(){
        setSolved();
        callback();
    }

    @Override
    public void callback() {
        System.out.println("Attention, players. Room " + getName() + " has been solved. ");
    }

    @Override
    public String toString(){
        return "Room " + getName() + System.lineSeparator() +
                "Theme:  " + getTheme() + System.lineSeparator() +
                "Solution status: " + getSolvedStatus() + System.lineSeparator() +
                "Difficulty: " + getDifficulty() + System.lineSeparator();
    }
}