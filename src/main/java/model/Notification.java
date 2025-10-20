package model;

public class Notification {

    private String name, text;

    public Notification(String name){
        this.name = name;
        this.text = "";
    }

    public String getName(){return this.name = name;}
    public String getText(){return this.text = text;}

    public void setName(String name){this.name = name;}
    public void setText(String text){this.text = text;}
}