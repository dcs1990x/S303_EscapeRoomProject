package menus;

import model.Clue;
import model.Item;
import model.Theme;
import servicelayer.ClueService;
import servicelayer.ItemService;

public class MenuCreacionLois {
    ClueService clueService = new ClueService();
    ItemService itemService = new ItemService();


    public MenuCreacionLois(){
    }

    public void logicaMenuCreacion(){
        //Scanner com switch para seleccionar elemento que quiero crear.
        createClue();
        createItem();

    }

    private void createClue() {

        Clue clue = new Clue("Pista_1", "Pista sobre alienígena", Theme.SPACE, 5, true, false);
        //Falta añadir la selección de sala por parte del user y la selección de los elementos.
        clueService.addClue(clue);
        //pasar al DAO
    }


    private void createItem() {
        Item item = new Item("Item_1", "A key", Theme.SPACE, 23, true);
        //Falta añadir la selección de sala por parte del user.
        itemService.addItem(item);


    }
    //Read
    //Update
    //Delete
}
