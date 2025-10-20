package model;

public class Hint {

    private String name, description;
    private double price;
    private int difficultyPoints;
    private boolean isImportant;

    public Hint(String name, double price, int difficultyPoints, boolean isImportant){
        this.name = name;
        this.description = "";
        this.price = price;
        this.difficultyPoints = difficultyPoints;
        this.isImportant = isImportant;
    }

    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public double getPrice(){return this.price;}
    public int getDifficultyPoints(){return this.difficultyPoints;}
    public boolean getIsImportant(){return this.isImportant;}

    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}
    public void setPrice(double price){this.price = price;}
    public void setDifficultyPoints(int difficultyPoints){this.difficultyPoints = difficultyPoints;}
    public void setIsImportant(boolean isImportant){this.isImportant = isImportant;}
}