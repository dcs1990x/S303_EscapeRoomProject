package menus;

import model.Clue;
import model.UserInput;
import servicelayer.ClueService;
import servicelayer.ItemService;
import servicelayer.RoomService;
import views.ClueView;
import views.DecorationView;
import views.ItemView;
import views.RoomView;

public class InventoryMenu extends CreateManageDeleteERMenu{
    ClueService clueService = new ClueService();
    ItemService itemService = new ItemService();
    RoomService roomService = new RoomService();
    MenuLecturaLois lectura = new MenuLecturaLois();
    ClueView clueView = new ClueView();
    ItemView itemView = new ItemView();
    RoomView roomView = new RoomView();
    DecorationView decorationView = new DecorationView();

    public void optionSelector(){
        int opcion = -1;

        do {
            System.out.println("1. Show Inventory");
            System.out.println("2. Delete object from Inventory");
            System.out.println("3. Show total assets price");
            System.out.println("4. Go the previous menu");
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
                case 4:

                case 0:
                    System.out.println("Getting out of the menu...");
                    break;
                default:
                    System.out.println("Incorrect Option. Please, select a number from one option(0 to 4).");
            }
        } while(opcion != 0);
    }
    public void showInventory(){
        try{
            roomView.showRooms();
            clueView.showClues();
            itemView.showItems();
            decorationView.showDecorations();

        } catch (Exception e) {
            System.out.println("Cannot be retrieved clues/items/rooms from the DB. Something wrong has happened. Please contact support");
            showMainMenu();
            executeMainMenuOption();
        }

    }
    public void showAssetsValue(){}
    public void deleteObject(){

    }

}
