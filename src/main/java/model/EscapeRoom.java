package model;

import java.util.ArrayList;
import java.util.List;

public class EscapeRoom {

    private String name;
    private List<Room> rooms;

    public EscapeRoom(String name){
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public String getEscapeRoomName(){
        return this.name;
    }

    public List<Room> getRoomsList(){
        return List.copyOf(rooms);
    }
}
