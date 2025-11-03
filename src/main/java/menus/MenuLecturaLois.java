package menus;


import servicelayer.ClueService;
import servicelayer.ItemService;
import servicelayer.RoomService;

public class MenuLecturaLois {
    RoomService roomService = new RoomService();
    ClueService clueService = new ClueService();
    ItemService itemService = new ItemService();

    public MenuLecturaLois() {}

    public void logicaMenuLectura() {


       // readRooms(); //Nos da tanto los datos de la room como sus pistas y objetos asociados.
        //Read clues
       // readClue();
        //Read items.
        readItems();
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

    public void readItems() {
        //Conexión a service para leer todos los Items
        try {
            itemService.getItems();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Conexión a service para leer todas las pistas.
    }
}
