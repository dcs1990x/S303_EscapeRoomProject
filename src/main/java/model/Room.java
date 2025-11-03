package model;

import java.util.ArrayList;
import java.util.List;

public class Room implements RoomSolvedCallback{

    private String name;
    private double price;
    private Theme theme;
    private Difficulty difficulty;
    private boolean isSolved;
    private List<Decoration> decorations = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Clue> clues = new ArrayList<>();
    private int idRoom;

    public Room(){}

    public Room(String name, Difficulty difficulty, double price){
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
    }
    public String getName(){
        return this.name;
    }

    public Theme getTheme(){
        return this.theme;
    }

    public double getPrice(){
        return this.price;
    }

    public boolean getSolvedStatus(){
        return this.isSolved;
    }

    public Difficulty getDifficulty(){
        return this.difficulty;
    }

    public List<Item> getItems(){
        for (Item item : items){
            System.out.println(item);
        }
        return items;

    }

    public List<Clue> getClues(){
        for (Clue clue : clues){
            System.out.println(clue);
        }
        return clues;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIdRoom(int idRoom){
        this.idRoom = idRoom;
    }

    public void setTheme(Theme theme){
        this.theme = theme;
    }

    public void setSolved(){
        if (!isSolved){
            this.isSolved = true;
        } else {
            this.isSolved = false;
        }
    }

    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    public void addDecoration(Decoration decoration){
        decorations.add(decoration);
    }

    public void addClue(Clue clue){
        this.clues.add(clue);
    }

    public void roomWasSolved(Player player){
        setSolved();
        player.setScore(this);
        callback(player);
    }

    @Override
    public void callback(Player player) {
        System.out.println("Attention, players. Room " + this.getName() + " has been solved by " + player.getName() + "! ");
    }

    @Override
    public String toString(){
        return "Room " + getName() + System.lineSeparator() +
                "Theme:  " + getTheme() + System.lineSeparator() +
                "Difficulty: " + getDifficulty() + System.lineSeparator() +
                "Solution status: " + getSolvedStatus() + System.lineSeparator();
    }
}