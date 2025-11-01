package model;

import servicelayer.ClueService;
import servicelayer.ItemService;
import servicelayer.RoomService;

import java.util.List;

public class MenuLecturaLois {
    RoomService roomService = new RoomService();
    ClueService clueService = new ClueService();
    ItemService itemService = new ItemService();

    public MenuLecturaLois() {}

    public void logicaMenuLectura() {


       // readRooms(); //Nos da tanto los datos de la room como sus pistas y objetos asociados.
        //Read clues
        readClue();
        //Read items.
    }

    public void readRooms() {
        try {
            roomService.readAllRooms();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void readClue() {
        try {
            clueService.getClues();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Conexión a service para leer todas las pistas.
    }

    private void readItems() {
        //Conexión a service para leer todos los Items
    }
}
