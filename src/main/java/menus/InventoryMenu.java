package menus;

import model.UserInput;
import servicelayer.ClueService;
import servicelayer.ItemService;
import servicelayer.RoomService;

public class InventoryMenu {
    ClueService clueService = new ClueService();
    ItemService itemService = new ItemService();
    RoomService roomService = new RoomService();
    MenuLecturaLois lectura = new MenuLecturaLois();

    public void optionSelector(){
        int opcion = -1;

        do {
            System.out.println("1. Show Inventory");
            System.out.println("2. Delete object from Inventory");
            System.out.println("3. Show total assets price");
            System.out.println("0. Exit");
            opcion = UserInput.readInt("Choose a option: ");

            switch(opcion) {
                case 1:
                    showInventory();
                    break;
                case 2:

                    deleteObject();
                    break;
                case 3:
                    showAssetsValue();
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Incorrect Option. Please, select a number from one option(0 to 4).");
            }
        } while(opcion != 0);
    }
    public void showInventory(){

    }
    public void showAssetsValue(){}
    public void deleteObject(){

    }

}
