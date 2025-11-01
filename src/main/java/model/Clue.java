package model;

public class Clue {

    private int idClue;
    private String name, description;
    private Theme theme;
    private int difficultyPoints;
    private boolean isImportant;
    private boolean isSolved;

    public Clue(String name, String description, Theme theme, int difficultyPoints, boolean isImportant, boolean isSolved){
        this.name = name;
        this.description = description;
        this.theme = theme;
        this.difficultyPoints = difficultyPoints;
        this.isImportant = isImportant;
        this.isSolved = isSolved;
    }

    public int getIdClue(){return this.idClue;}
    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public Theme getTheme(){return this.theme;}
    public int getDifficultyPoints(){return this.difficultyPoints;}
    public boolean getIsImportant(){return this.isImportant;}
    public boolean getIsSolved(){return this.isSolved;}

    public void setIdClue(int id){this.idClue = id;}
    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}
    public void setTheme(Theme theme){this.theme = theme;}
    public void setDifficultyPoints(int difficultyPoints){this.difficultyPoints = difficultyPoints;}
    public void setIsImportant(boolean isImportant){this.isImportant = isImportant;}
    public void setIsSolved(){
        if (!isSolved){
            this.isSolved = true;
        } else {
            this.isSolved = false;
        }
    }
}