package menus;

import model.Theme;
import servicelayer.ClueService;
import model.Clue;
import model.Item;
import servicelayer.ItemService;

public class MenuModificacionLois {
    MenuLecturaLois lectura = new MenuLecturaLois();
    ClueService clueService = new ClueService();
    ItemService itemService = new ItemService();
    public MenuModificacionLois() {}

    public void logicaMenuModificacionLois() {
        //modificar sala.
        lectura.readRooms();// vemos que habitaciones tenemos para saber que queremos modificar
       // modifyClue();
        modifyItem();
    }

    public void Modify(){

    }

    private void modifyRoom() {

    }

    private void modifyClue() {
        int id = 4;
        //Le pedimos al usuario que introduzaca el id de la pista a actualizar
        Clue clue = new Clue("Pista_nueva", "Pista nueva para test", Theme.SPACE, 6543, true, false);
        clueService.updateClue(clue, id);
    }

    private void modifyItem() {
        lectura.readRooms();// vemos que habitaciones tenemos para saber que queremos modificar

        int id = 4;
        Item item = new Item("los", "sdfsdf", Theme.SPACE, 123, true);
        itemService.updateItem(item,id);
    }


}
