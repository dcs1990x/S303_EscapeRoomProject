package model;

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
       // createItem();

    }
    //Acciones con pistas
    //Create (genera la instancia de la pista)
    //Try-catch
    private void createClue() {
        Clue clue = new Clue("Pista_1", "Pista sobre alienígena", Theme.SPACE, 5, true, false);
        clueService.addClue(clue);
        //pasar al DAO
    }

    //Read

     //Modificar
     //Eliminar

    //Acciones con Objetos
    //Crear
    private void createItem() {
        Item item = new Item("Item_1", "A key", Theme.SPACE, 5, true);
        itemService.addItem(item);

    }
    //Read
    //Update
    //Delete
}
