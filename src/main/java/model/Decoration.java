package model;

public class Decoration {

    private String name;
    private Theme theme;

    public Decoration(String name, Theme theme){
        this.name = name;
        this.theme = theme;
    }

    public String getName(){return this.name;}
    public Theme getTheme(){return this.theme;}

    public void setName(String name){this.name = name;}
    public void setTheme(Theme theme){this.theme = theme;}
}