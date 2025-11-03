package model;

import java.util.Arrays;
import java.util.Optional;

import static model.Theme.*;

public class Decoration {

    private int idDecoration;
    private String name;
    private double price;
    private Theme theme;
    private int idRoom;


    public Decoration(String name, double price, Theme theme,int id_room){
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.idRoom = id_room;
    }
    public Decoration(){}
    public Decoration(int idDecoration, String name, double price, Theme theme, int id_room){
        this.idDecoration = idDecoration;
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.idRoom = id_room;
    }

    public String getName(){return this.name;}
    public double getPrice(){return this.price;}
    public Theme getTheme(){return this.theme;}
    public int getIdRoom() {
        return idRoom;
    }
    public int getIdDecoration() {
        return idDecoration;
    }
    public void setName(String name){this.name = name;}
    public void setPrice(double price){this.price = price;}
    public void setTheme(Theme theme){this.theme = theme;}
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
    public void setIdDecoration(int idDecoration) {
        this.idDecoration = idDecoration;
    }
    public boolean isEmpty(){
        return name == null && price == 0.0 && theme == null && idRoom == 0 ;
    }


}

