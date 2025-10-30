package model;

import java.util.ArrayList;
import java.util.List;

public class Room implements RoomSolvedCallback{

    private String name;
    private Theme theme;
    private Difficulty difficulty;
    private boolean isSolved;
    private List<Decoration> decorations = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Clue> clues = new ArrayList<>();

    public String getName(){
        return this.name;
    }
    public Theme getTheme(){
        return this.theme;
    }

    public boolean getSolvedStatus(){
        return this.isSolved;
    }

    public Difficulty getDifficulty(){
        return this.difficulty;
    }

    public void getItems(){
        for (Item item : items){
            System.out.println(item);
        }
    }

    public void getClues(){
        for (Clue clue : clues){
            System.out.println(clue);
        }
    }

    public void setName(String name){
        this.name = name;
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