package menus;

import servicelayer.ClueService;
import servicelayer.ItemService;

public class MenuEliminacionLois {
    ClueService clueService = new ClueService();
    ItemService itemService = new ItemService();
    MenuLecturaLois menuLectura = new MenuLecturaLois();
    public MenuEliminacionLois(){}

    public void logicaMenuEliminacion(){
        deleteItem();
    }
            //ChooseRoom elegir que borrar de ella
    private void deleteClue()  {
        //Mostramos todas las clues
        menuLectura.readClue();

        int id = 1; //aquí iría el scanner pidiendo que se elija la clave que quiere borrar.
        try {
            clueService.deleteClueById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        menuLectura.readClue();

    }

    private void deleteRoom(){

    }


    private void deleteItem(){
        //Mostramos todas los
        menuLectura.readItems();

        int id = 1; //aquí iría el scanner pidiendo que se elija la clave que quiere borrar.
        try {
            itemService.deleteItemById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        menuLectura.readItems();

    }

}
